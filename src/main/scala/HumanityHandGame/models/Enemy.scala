package HumanityHandGame.models

import scala.util.Random
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class Enemy(var hp: Int) extends GameEntity with DeckHandler{
  val name: String = "Enemy"
  val deck: ListBuffer[Card] = ListBuffer()
  val hand: ListBuffer[Card] = ListBuffer()

  def initializeDeck(initialCards: Seq[Card]): Unit = {
    deck.clear()
    deck ++= initialCards
  }

  def playTurn(target: GameEntity): Unit = {
    //prioritize attack then def then special
    hand.find(_.isInstanceOf[AttackCard])
      .orElse(hand.find(_.isInstanceOf[DefenseCard]))
      .orElse(hand.find(_.isInstanceOf[SpecialCard]))
    match{
      case Some(card)=>
        hand-=card
        card.use(this, target)
      case None=>
        println(s"$name has no valid cards to play")
    }
  }
}