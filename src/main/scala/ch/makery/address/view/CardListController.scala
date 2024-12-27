package ch.makery.address.view

import ch.makery.address.MenuScene
import javafx.collections.FXCollections
import javafx.fxml.FXML
import scalafx.application.JFXApp3
import scalafx.collections.ObservableBuffer
import scalafx.event.ActionEvent
import scalafx.scene.control.{ListView, TextField}
import services.CardCollection

class CardListController{
  private val cardCollection = new CardCollection()

  @FXML private var searchField: TextField = _
  @FXML private var listView: ListView[String] = _

  @FXML
  def initialize(): Unit = {
    listView.setItems(FXCollections.observableArrayList(cardCollection.listCards.map(_.toString): _*))
  }

  @FXML  
  def onSearch(event: ActionEvent): Unit = {
    val query = searchField.getText.toLowerCase
    val filteredCards = cardCollection.listCards.filter(_.name.toLowerCase.contains(query))
    listView.setItems(FXCollections.observableArrayList(filteredCards.map(_.toString): _*))
  }
  
  @FXML
  def onBackToMenu(event: ActionEvent): Unit = {
    JFXApp3.PrimaryStage().scene = MenuScene()
  }
}