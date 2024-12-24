class Player(var name: String, var hp: Int, var deck: Deck){
  def takeTurn(): Option[Card] = {
    deck.drawCard()
  }
}