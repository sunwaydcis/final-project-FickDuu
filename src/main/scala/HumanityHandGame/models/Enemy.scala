package HumanityHandGame.models

import scala.util.Random
import scala.collection.mutable

class Enemy(val hp: Int) extends GameEntity{
  val name: String = "Enemy"
  private val deck: mutable.ListBuffer[Card] = mutable.ListBuffer()
  private val hand: mutable.ListBuffer[Card] = mutable.ListBuffer()

  def initializeDeck(initialCards: Seq[Card]): Unit = {
    deck.clear()
    deck ++= initialCards
  }

  def drawCards(count: Int): Unit = {
    val cardsToDraw = math.min(count, deck.size)
    hand ++= Random.shuffle(deck).take(cardsToDraw)
    deck --= hand
    println(s"$name drew $cardsToDraw cards")
  }

  def playTurn(target: GameEntity): Unit = {
    //prioritize attack then def then special
    hand.find(_.isInstanceOf[AttackCard]).orElse(
      hand.find(_.isInstanceOf[DefenseCard])).orElse(
        hand.find(_.isInstanceOf[SpecialCard]))
    match{
      case Some(card)=>
        hand-=card
        card.use(this, target)

      case None=>
        println(s"$name has no valid cards to play")
    }
  }
}