package HumanityHandGame.ui

import javafx.fxml.FXMLLoader
import scalafx.scene.Scene

object LoginScene{
  def apply(): Scene = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("/fxml/LoginScene.fxml"))
    new Scene(fxmlLoader.load()) 
  }
}