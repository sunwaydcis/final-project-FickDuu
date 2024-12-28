package MusicPlayer

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.layout.Pane
import javafx.scene.media.{Media, MediaPlayer}
import javafx.util.Duration

import java.io.File
import scala.collection.mutable.ArrayBuffer

class musicPlayerController {

  @FXML private var body: Pane = _
  @FXML private var songName: Label = _
  @FXML private var playButton, pauseButton, resetButton, prevButton, nextButton: Button = _
  @FXML private var speedBox: ComboBox[String] = _
  @FXML private var volumeSlider: Slider = _
  @FXML private var songProgBar: ProgressBar = _

  private var songs = ArrayBuffer.empty[File]
  private var songNumber = 0
  private var mediaPlayer: MediaPlayer = _
  private var media: Media = _

  def changeSpeed(): Unit = {
    if (mediaPlayer != null && speedBox.getValue != null) {
      val speed = speedBox.getValue.replace("%", "").toInt / 100.0
      mediaPlayer.setRate(speed)
    }
  }

  def playCurrentSong(): Unit = {
    if (mediaPlayer != null) mediaPlayer.dispose()
    if (songs.nonEmpty) {
      media = new Media(songs(songNumber).toURI.toString)
      mediaPlayer = new MediaPlayer(media)
      songName.setText(songs(songNumber).getName)
      playMedia()
    }
  }
  
  def initialize(): Unit = {
    val directory = new File("music")
    val files = Option(directory.listFiles()).getOrElse(Array.empty[File])
    files.foreach(songs.append)

    if (songs.nonEmpty) {
      media = new Media(songs.head.toURI.toString)
      mediaPlayer = new MediaPlayer(media)
      songName.setText(songs.head.getName)
    }
    else {
      songName.setText("No songs available")
    }

    speedBox.setItems(FXCollections.observableArrayList(
      "25%", "50%", "75%", "100%", "125%", "150%", "175%", "200%"
    ))

    speedBox.setOnAction(_ => changeSpeed())

    if (mediaPlayer != null) {
      volumeSlider.valueProperty().bindBidirectional(mediaPlayer.volumeProperty())
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
}
