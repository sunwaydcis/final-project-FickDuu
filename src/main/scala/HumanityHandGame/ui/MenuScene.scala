package HumanityHandGame.ui

import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.geometry.Insets
import HumanityHandGame.services.{LoginManager, Game, Scoreboard}
import HumanityHandGame.models.Player

object MenuScene{
  def apply(): Scene = new Scene{
    val currentUser: String = LoginManager.currentUser.getOrElse("Not logged in")
    val player = new Player(currentUser)

    root = new VBox{
      spacing = 10
      padding = Insets(20)
      children = Seq(
        new Label(s"Welcome, $currentUser"),
        new Button("Play"){
          onAction = _ => {
            val game = new Game(player)
            game.start()
          }
        },
        new Button("View Cards"){
          onAction =_ => scalafx.application.JFXApp3.PrimaryStage().scene = CardListScene()
        },
        new Button("View Scores"){
          onAction =_=> {
            Scoreboard.displayScores()
          }
        },
        new Button("Logout"){
          onAction = _ => {
            Scoreboard.saveToFile()
            LoginManager.logout()
            scalafx.application.JFXApp3.PrimaryStage().scene = LoginScene()
          }
        }
      )
    }
  }
}