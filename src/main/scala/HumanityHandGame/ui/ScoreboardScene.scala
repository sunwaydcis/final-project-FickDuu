package HumanityHandGame.ui

import javafx.fxml.FXMLLoader
import scalafx.scene.Scene

object ScoreboardScene{
  def apply(): Scene ={
    val fxmlLoader = new FXMLLoader(getClass.getResource("/fxml/ScoreboardScene.fxml"))
    new Scene(fxmlLoader.load())
  }
}