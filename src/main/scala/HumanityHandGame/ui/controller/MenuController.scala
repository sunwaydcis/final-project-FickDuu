package HumanityHandGame.ui.controller

import scalafx.event.ActionEvent
import javafx.fxml.FXML
import HumanityHandGame.ui.{CardListScene, ScoreboardScene, LoginScene}
import HumanityHandGame.services.{LoginManager, Scoreboard}
import scalafx.application.JFXApp3
import HumanityHandGame.services.Game
import HumanityHandGame.models.Player

class MenuController{
  @FXML
  def onPlay(event: ActionEvent): Unit = {
    val player = new Player(LoginManager.currentUser.getOrElse("Guest"))
    val game = new Game(player)
    game.start()
  }

  @FXML
  def onViewCards(event: ActionEvent): Unit = {
    JFXApp3.PrimaryStage().scene = CardListScene()
  }

  @FXML
  def onViewScores(event: ActionEvent): Unit = {
    JFXApp3.PrimaryStage().scene = ScoreboardScene()
  }

  @FXML
  def onLogout(event: ActionEvent): Unit = {
    Scoreboard.saveToFile()
    LoginManager.logout()
    JFXApp3.PrimaryStage().scene = LoginScene()
  }
}