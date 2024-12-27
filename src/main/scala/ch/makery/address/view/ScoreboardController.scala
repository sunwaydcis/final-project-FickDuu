package ch.makery.address.view

import services.Scoreboard
import ch.makery.address.MenuScene
import javafx.collections.FXCollections
import javafx.fxml.FXML
import scalafx.application.JFXApp3
import scalafx.collections.ObservableBuffer
import scalafx.event.ActionEvent
import scalafx.scene.control.ListView
import services.Scoreboard

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