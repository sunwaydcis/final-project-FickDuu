class Deck{
  private var cards: list[Card] = List(
    AttackCard("Slash", 10),
    HealCard("Heal", 5),
  )
  
  def shuffleDeck(): Unit = {
    cards = scala.util.Random.shuffle(cards)
  }
  
  def drawCard(): Option[Card] = {
    if(cards.nonEmpty) {
      val drawnCard = cards.head
      cards = cards.tail
      Some(drawnCard)
    }
    else{
      None
    }
  }
}