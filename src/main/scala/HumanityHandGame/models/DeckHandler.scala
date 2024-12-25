package HumanityHandGame.models

import scala.util.Random
import scala.collection.mutable.ListBuffer

trait DeckHandler{
  val deck: ListBuffer[Card]
  val hand: ListBuffer[Card]
  
  def drawCards(count: Int): Unit ={
    val cardsToDraw = math.min(count, deck.size)
    val drawnCards = Random.shuffle(deck).take(cardsToDraw)
    hand ++= drawnCards
    deck --= drawnCards
    println(s"Cards drawn: ${drawnCards.map(_.name).mkString(", ")}")
  }
  
  def addCardToDeck(card: Card): Unit = {
    deck += card
    println(s"Card added to deck: ${card.name}")
  }
  
  def handSize: Int = hand.size
}