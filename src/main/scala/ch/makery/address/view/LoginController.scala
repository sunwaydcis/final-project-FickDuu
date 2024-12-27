package ch.makery.address.view

import services.LoginManager
import ch.makery.address.MenuScene
import javafx.fxml.FXML
import scalafx.application.JFXApp3
import scalafx.event.ActionEvent
import scalafx.scene.control.{Button, Label, TextField}
import services.LoginManager

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
