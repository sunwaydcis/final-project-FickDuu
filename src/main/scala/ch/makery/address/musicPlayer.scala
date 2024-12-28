package ch.makery.address

import javafx.fxml.FXMLLoader
import javafx.stage.Stage
import javafx.stage.StageStyle
import javafx.scene.Parent
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._

object musicPlayer extends JFXApp3{
  
  override def start(): Unit = {
    stage = new PrimaryStage():
      title = "Music Player"

    val root = new FXMLLoader(getClass.getResource("view/musicPlayer.fxml"))
    
    stage.scene = new Stage(root)
    
    stage.show()
  }
}