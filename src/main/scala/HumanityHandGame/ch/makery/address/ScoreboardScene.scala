package HumanityHandGame.ch.makery.address

import javafx.fxml.FXMLLoader
import scalafx.scene.Scene

object ScoreboardScene{
  def apply(): Scene ={
    val fxmlLoader = new FXMLLoader(getClass.getResource("/ch/makery/address/view/ScoreboardScene.fxml"))
    new Scene(fxmlLoader.load())
  }
}