package HumanityHandGame.ui

import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.geometry.Insets
import HumanityHandGame.services.LoginManager
import HumanityHandGame.ui.CardListScene

object MenuScene{
  def apply(): Scene = new Scene{
    root = new VBox{
      spacing = 10
      padding = Insets(20)
      children = Seq(
        new Button("View Cards"){
          onAction = _ => scalafx.application.JFXApp3.PrimaryStage().scene = CardListScene()
        },
        new Button("Logout"){
          onAction = _ => {
            LoginManager.logout()
            scalafx.application.JFXApp3.PrimaryStage().scene = LoginScene()
          } 
        }
      )
    }
  }
}