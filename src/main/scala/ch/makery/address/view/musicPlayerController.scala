package ch.makery.address.view

import scalafx.event.ActionEvent
import scalafx.scene.control.{Button, ComboBox, Label, ProgressBar, Slider}
import scalafx.scene.layout.Pane
import scalafx.beans.property.DoubleProperty
import scalafx.beans.binding.Bindings
import scalafx.scene.media.MediaPlayer

import java.io.File
import scala.collection.mutable.ArrayBuffer

class musicPlayerController{

  @FXML private var body: Pane = _
  @FXML private var songName: Label = _
  @FXML private var playButton, pauseButton, resetButton, prevButton, nextButton: Button = _
  @FXML private val speedBox: ComboBox[String] = _
  @FXML private val volumeSlider: Slider = _
  @FXML private val songProgBar: ProgressBar = _

  private val songs = ArrayBuffer.empty[File]
  private var songNumber = 0
  private var mediaPlayer: MediaPlayer = _
  private var media: Media = _

  def initialize(): Unit = {
    val directory = new File("music")
    directory.listFiles().foreach(songs.append)

    media = new Media(songs.head.toURI.toString)
    mediaPlayer = new MediaPlayer(media)
    songName.text = songs.head.getName

    speedBox.items ++= Seq("25%", "50%", "75%", "100%", "125%", "150%", "175%", "200%")
    speedBox.onAction = _ => changeSpeed()
    volumeSlider.value <==> mediaPlayer.volume

    songProgBar.progress <== Bindings.createDoubleBinding(
      () => mediaPlayer.currentTime.toSeconds / media.duration.toSeconds,
      mediaPlayer.currentTimeProperty()
    )
  }

  def playMedia(): Unit = {
    mediaPlayer.play()
  }

  def pauseMedia(): Unit = {
    mediaPlayer.pause()
  }

  def resetMedia(): Unit = {
    mediaPlayer.seek(Duration.ZERO)
  }

  def prevMedia(): Unit = {
    songNumber = (songNumber - 1 + songs.size) % songs.size
    playCurrentSong()
  }

  def nextMedia(): Unit = {
    songNumber = (songNumber + 1) % songs.size
    playCurrentSong()
  }

  private def changeSpeed(): Unit = {
    val speed = speedBox.value.value.dropRight(1).toInt / 100.0
    mediaPlayer.setRate(speed)
  }

  private def playCurrentSong(): Unit = {
    mediaPlayer.stop()
    media = new Media(songs(songNumber).toURI.toString)
    mediaPlayer = new MediaPlayer(media)
    songName.text = songs(songNumber).getName
    playMedia()
  }
}