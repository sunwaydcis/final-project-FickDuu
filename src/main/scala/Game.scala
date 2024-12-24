class Game(player: Player, enemy: Enemy){
  def playRound(): Unit = {
    //player's turn
    val playerCard = player.takeTurn()
    playerCard.foreach(card => card.effect(player))
    
    //enemy's turn
    enemy.takeTurn(player)
  }
}