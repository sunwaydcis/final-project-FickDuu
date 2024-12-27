package ch.makery.address.view

import ch.makery.address.ScoreboardScene
import models.Player
import services.{Game, LoginManager, Scoreboard}
import ch.makery.address.{CardListScene, LoginScene, ScoreboardScene}
import javafx.fxml.FXML
import models.Player
import scalafx.application.JFXApp3
import scalafx.event.ActionEvent
import services.{Game, LoginManager, Scoreboard}

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