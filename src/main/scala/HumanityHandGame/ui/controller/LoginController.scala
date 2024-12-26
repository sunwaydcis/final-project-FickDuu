package HumanityHandGame.ui.controller

import scalafx.scene.control.{Label, Button, TextField}
import scalafx.event.ActionEvent
import javafx.fxml.FXML
import HumanityHandGame.services.LoginManager
import scalafx.application.JFXApp3
import HumanityHandGame.ui.MenuScene

class LoginController{
  @FXML private var usernameField: TextField = _
  @FXML private var messageLabel: TextField = _

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
