package HumanityHandGame.services

import HumanityHandGame.models.Card

class CardCollection{
  private var cards: List[Card] = List()

  def addCard(card: Card): Unit = cards = card :: cards
  def searchCard(name:String): Option[Card] = cards.find(_.name.equalsIgnoreCase(name))
  def listCards: List[Card] = cards
}