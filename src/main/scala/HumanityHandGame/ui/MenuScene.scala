package HumanityHandGame.ui

import javafx.fxml.FXMLLoader
import scalafx.scene.Scene

object MenuScene{
  def apply():Scene = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("/fxml/MenuScene.fxml"))
    new Scene(fxmlLoader.load())
  }
}