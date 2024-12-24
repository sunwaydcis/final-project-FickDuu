package HumanityHandGame.ui

import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.Includes._
import scalafx.geometry.Insets
import scalafx.event.ActionEvent
import HumanityHandGame.services.LoginManager
import HumanityHandGame.ui.MenuScene


object LoginScene{
  def apply(): Scene = new Scene{
    val usernameField = new TextField()
    val messageLabel = new Label()

      root = new VBox {
        spacing = 10
        padding = Insets(20)
        children = Seq(
          new Label("Enter your username:"),
          usernameField,
          messageLabel,
          new Button("Login") {
            onAction = _ => {
              val username = usernameField.text.value
              if (LoginManager.login(username)) {
                messageLabel.text = ""
                scalafx.application.JFXApp3.PrimaryStage().scene = MenuScene()
              }
              else{
                messageLabel.text = "Invalid username.Try again"
                messageLabel.style = "-fx-text-fill: red;"
              }
            }
          }

        )
      }

  }
}