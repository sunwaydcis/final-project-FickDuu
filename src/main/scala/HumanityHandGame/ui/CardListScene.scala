package HumanityHandGame.ui

import javafx.fxml.FXMLLoader
import scalafx.scene.Scene

object CardListScene{
  def apply(): Scene ={
    val fxmlLoader = new FXMLLoader(getClass.getResource("/fxml/CardListScene.fxml"))
    new Scene(fxmlLoader.load())
  }
}