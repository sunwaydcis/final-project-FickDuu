package HumanityHandGame.ch.makery.address

import javafx.fxml.FXMLLoader
import scalafx.scene.Scene

object CardListScene{
  def apply(): Scene ={
    val fxmlLoader = new FXMLLoader(getClass.getResource("/ch/makery/address/view/CardListScene.fxml"))
    new Scene(fxmlLoader.load())
  }
}