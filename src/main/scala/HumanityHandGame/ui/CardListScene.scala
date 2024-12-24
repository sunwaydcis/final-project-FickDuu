package HumanityHandGame.ui

import HumanityHandGame.models.{AttackCard, DefenseCard, SpecialCard}
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.geometry.Insets
import scalafx.collections.ObservableBuffer
import HumanityHandGame.services.CardCollection
import HumanityHandGame.ui.MenuScene

object CardListScene {
  private val cardCollection = new CardCollection()

  cardCollection.addCard(new AttackCard("Sword Slash", "A powerful slash", 20))
  cardCollection.addCard(new DefenseCard("Shield Block", "Block incoming damage", 10))
  cardCollection.addCard(new SpecialCard("Fireball", "Burn your enemy", "Burn"))

  def apply(): Scene = new Scene {
    val messageLabel = new Label()
    val listView: ListView[String] = new ListView[String] {
      items = ObservableBuffer.from(cardCollection.listCards.map(_.toString))
    }

    if (cardCollection.listCards.isEmpty) {
      messageLabel.text = "No cards in your collection."
    } else {
      messageLabel.text = ""
    }

    root = new VBox {
      spacing = 10
      padding = Insets(20)
      children = Seq(
        new Label("Your Cards:"),
        messageLabel,
        listView,
        new Button("Back to Menu") {
          onAction = _ => scalafx.application.JFXApp3.PrimaryStage().scene = MenuScene()
        }
      )
    }
  }
}