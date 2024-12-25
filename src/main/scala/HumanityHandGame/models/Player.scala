package HumanityHandGame.models

import scala.util.Random
import scala.collection.mutable

class Player(val name: String) extends GameEntity{
  var hp: Int = 50
  private val deck: mutable.ListBuffer[Card] = mutable.ListBuffer()
  private val hand: mutable.ListBuffer[Card] = mutable.ListBuffer()
  
  def initializeDeck(initialCards: Seq[Card]): Unit = {
    deck.clear()
    deck ++= initialCards
  }
  
  def drawCards(count: Int): Unit ={
    val cardsToDraw = math.min(count, deck.size)
    hand ++= Rnadom.shuffle(deck).take(cardsToDraw)
    deck --= hand
    println(s"$name drew $cardsToDraw cards. Hand: ${hand.map(_.name).mkString(", ")}")
  }
  
  def playCard(cardIndex: Int, target: GameEntity): Unit = {
    if(cardIndex >= 0 && cardIndex < hand.size){
      val card  = hand.remove(cardIndex)
      card.use(this, target)
    }
    else{
      println("Invalid card selection")
    }
  }
  
  def viewHand(): Unit ={
    println(s"$name's hand: ${hand.zipWithIndex.map{ case (card, idx) => s"[$idx] $card"}.mkString("\n")}")
  }
}