package HumanityHandGame.ch.makery.address.view

import HumanityHandGame.ch.makery.address.MenuScene
import scalafx.scene.control.{Label, Button, TextField}
import scalafx.event.ActionEvent
import javafx.fxml.FXML
import HumanityHandGame.services.LoginManager
import scalafx.application.JFXApp3

class LoginController{
  @FXML private var usernameField: TextField = _
  @FXML private var messageLabel: Label = _
  @FXML private var beginButton: Button =_

  @FXML
  def onLogin(event: ActionEvent): Unit = {
    val username = usernameField.getText.trim
    if(username.isEmpty){
      messageLabel.setText("Username cannot be empty")
      messageLabel.setStyle("-fx-text-fill: red;")
    }
    else{
      LoginManager.login(username)
      JFXApp3.PrimaryStage().scene = MenuScene()
    }
  }
}
