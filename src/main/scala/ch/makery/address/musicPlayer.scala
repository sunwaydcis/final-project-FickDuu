package ch.makery.address

import javafx.application.Application
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.stage.Stage
import javafx.scene.Parent
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.stage.WindowEvent

object musicPlayer extends JFXApp3{
  stage = new PrimaryStage{
    title = "Music Player"
    scene = new Scene(FXMLLoader.load(getClass.getResource("musicPlayer.fxml")))
    onCloseRequest = _ => {
      System.exit(0)
    }
  }
}