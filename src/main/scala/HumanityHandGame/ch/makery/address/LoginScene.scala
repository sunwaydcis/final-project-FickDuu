package HumanityHandGame.ch.makery.address

import javafx.fxml.FXMLLoader
import scalafx.scene.Scene

object LoginScene{
  def apply(): Scene = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("/ch/makery/address/view/LoginScene.fxml"))
    new Scene(fxmlLoader.load()) 
  }
}