package HumanityHandGame.ui.controller

import scalafx.scene.control.ListView
import scalafx.collections.ObservableBuffer
import javafx.fxml.FXML
import HumanityHandGame.services.Scoreboard
import HumanityHandGame.ui.MenuScene
import javafx.collections.FXCollections
import scalafx.event.ActionEvent
import scalafx.application.JFXApp3

class ScoreboardController{
  @FXML private var listView: ListView[String] = _

  @FXML
  def initialize(): Unit = {
    val scores = Scoreboard.getScores.map{ case(name,score) => s"$name: $score"}
    listView.setItems(FXCollections.observableArrayList(scores: _*))
  }

  @FXML
  def onBackToMenu(event: ActionEvent): Unit = {
    JFXApp3.PrimaryStage().scene = MenuScene()
  }
}