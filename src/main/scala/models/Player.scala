package models

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.Random

class Player(val name: String) extends GameEntity with DeckHandler {
  var hp: Int = 50
  val deck: ListBuffer[Card] = ListBuffer()
  val hand: ListBuffer[Card] = ListBuffer()
  
  def initializeDeck(initialCards: Seq[Card]): Unit = {
    deck.clear()
    deck ++= initialCards
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