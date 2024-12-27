package ch.makery.address

import javafx.fxml.FXMLLoader
import scalafx.scene.Scene

object MenuScene{
  def apply():Scene = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("/ch/makery/address/view/MenuScene.fxml"))
    new Scene(fxmlLoader.load())
  }
}