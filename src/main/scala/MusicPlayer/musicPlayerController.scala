package MusicPlayer

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.layout.Pane
import javafx.scene.media.{Media, MediaPlayer}
import javafx.util.Duration
import javafx.animation.{KeyFrame, Timeline}
import javafx.scene.text.Text
//import scalafx.animation.KeyFrame

import java.io.File
import scala.collection.mutable.ArrayBuffer

class musicPlayerController {

  @FXML private var body: Pane = _
  @FXML private var songName: Text = _
  @FXML private var timeRemaining: Label = _
  @FXML private var playButton, pauseButton, resetButton, prevButton, nextButton: Button = _
  @FXML private var speedBox: ComboBox[String] = _
  @FXML private var volumeSlider: Slider = _
  @FXML private var songProgBar: ProgressBar = _

  private var songs = ArrayBuffer.empty[File]
  private var songNumber = 0
  private var mediaPlayer: MediaPlayer = _
  private var media: Media = _
  private var scrollTimeline: Timeline = _
  
  def initialize(): Unit = {
    val musicDirURL = getClass.getResource("/music")
    if (musicDirURL != null){
      val directory = new File(musicDirURL.toURI)
      val files = Option(directory.listFiles()).getOrElse(Array.empty[File])
      files.filter(_.isFile).foreach(songs.append)
    } else{
      println("Music folder not found in resources")
    }

    if (songs.nonEmpty){
      media = new Media(songs.head.toURI.toString)
      mediaPlayer = new MediaPlayer(media)
      songName.setText(songs.head.getName)

      mediaPlayer.setVolume(0.5)
      volumeSlider.setValue(50)


      volumeSlider.valueProperty().addListener((_, _, newValue) => {
        mediaPlayer.setVolume(newValue.doubleValue() / 100)
      })

    } else{
      songName.setText("No songs available")
    }

    speedBox.setItems(FXCollections.observableArrayList(
      "25%", "50%", "75%", "100%", "125%", "150%", "175%", "200%"
    ))
    speedBox.setPromptText("Speed")
    speedBox.setOnAction(_ => {
      changeSpeed()
      speedBox.setPromptText(null)
    })

    speedBox.setCellFactory(_ => {
      val cell = new ListCell[String]() {
        override def updateItem(item: String, empty: Boolean): Unit = {
          super.updateItem(item, empty)
          if (empty || item == null) {
            setText(null)
          } else {
            setText(item) // Display the item's text in the dropdown list
          }
        }
      }
      cell
    })

    speedBox.setButtonCell(new ListCell[String]() {
      override def updateItem(item: String, empty: Boolean): Unit = {
        super.updateItem(item, empty)
        // Always show "Speed" as the displayed value
        setText("Speed")
      }
    })

    if (mediaPlayer != null) {
      bindMediaPlayerProperties()
      songProgBar.setProgress(0.0)
    }
  }

  def playMedia(): Unit = {
    if (mediaPlayer != null) mediaPlayer.play()
  }

  def pauseMedia(): Unit = {
    if (mediaPlayer != null) mediaPlayer.pause()
  }

  def resetMedia(): Unit = {
    if (mediaPlayer != null) mediaPlayer.seek(Duration.ZERO)
  }

  def prevMedia(): Unit = {
    if (songs.nonEmpty) {
      songNumber = (songNumber - 1 + songs.size) % songs.size
      playCurrentSong()
    }
  }

  def nextMedia(): Unit = {
    if (songs.nonEmpty) {
      songNumber = (songNumber + 1) % songs.size
      playCurrentSong()

    }
  }

  def seekMedia(eventX: Double): Unit = {
    if(mediaPlayer != null && mediaPlayer.getTotalDuration != null){
      val progress = eventX / songProgBar.getWidth
      mediaPlayer.seek(mediaPlayer.getTotalDuration.multiply(progress))
    }
  }

  def bindMediaPlayerProperties(): Unit = {
    if(mediaPlayer != null) {
      volumeSlider.setValue(mediaPlayer.getVolume * 100)

      mediaPlayer.currentTimeProperty().addListener((_, _, newValue) => {
        val progress = if (mediaPlayer.getTotalDuration != null)
          newValue.toSeconds / mediaPlayer.getTotalDuration.toSeconds
        else 0.0
        songProgBar.setProgress(progress)

        val remainingTime = mediaPlayer.getTotalDuration.subtract(newValue)
        val minutes = remainingTime.toMinutes.toInt
        val seconds = (remainingTime.toSeconds % 60).toInt
        timeRemaining.setText(f"$minutes%02d:$seconds%02d")
      })

      songProgBar.setOnMousePressed(event => seekMedia(event.getX))
      songProgBar.setOnMouseDragged(event => seekMedia(event.getX))
    }
  }

  def autoplayNextSong(): Unit = {
    mediaPlayer.setOnEndOfMedia(() => nextMedia())
  }

  def changeSpeed(): Unit = {
    if (mediaPlayer != null && speedBox.getValue != null) {
      val speed = speedBox.getValue.replace("%", "").toInt / 100.0
      mediaPlayer.setRate(speed)
    }
  }

  def playCurrentSong(): Unit = {
    if (mediaPlayer != null)
      mediaPlayer.dispose()

    if (songs.nonEmpty) {
      val currentVolume = if(mediaPlayer != null)
        mediaPlayer.getVolume
      else 0.5

      // Save the current playback speed (default to 1.0 if mediaPlayer is null)
      val currentSpeed = if (mediaPlayer != null)
        mediaPlayer.getRate
      else 1.0

      // Create a new Media instance and MediaPlayer for the selected song
      media = new Media(songs(songNumber).toURI.toString)
      mediaPlayer = new MediaPlayer(media)

      // Set the song name in the UI
      songName.setText(songs(songNumber).getName)

      mediaPlayer.setVolume(currentVolume)
      volumeSlider.setValue(currentVolume * 100)

      speedBox.setPromptText("Speed")
      speedBox.setValue(null)

      // Restore the previous playback speed
      mediaPlayer.setRate(currentSpeed)

      // Sync the speedBox with the current speed
      speedBox.setValue((currentSpeed * 100).toInt + "%")

      bindMediaPlayerProperties()
      playMedia()
      autoplayNextSong()
    } else {
      // Handle case where no songs are available
      songName.setText("No songs available")
    }
  }
}


