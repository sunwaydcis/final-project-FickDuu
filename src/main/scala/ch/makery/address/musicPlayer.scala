package ch.makery.address

import javafx.fxml.FXMLLoader
import javafx.stage.Stage
import javafx.stage.StageStyle
import javafx.scene.Parent
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._

object musicPlayer extends JFXApp3 {

  override def start(): Unit = {
    val loader = new FXMLLoader(getClass.getResource("/ch/makery/address/view/musicPlayer.fxml"))
    val root = loader.load().asInstanceOf[scalafx.scene.Parent]

    stage = new PrimaryStage()
    stage.title = "Music Player"
    stage.scene = new Scene(root)
    stage.show()
  }
}