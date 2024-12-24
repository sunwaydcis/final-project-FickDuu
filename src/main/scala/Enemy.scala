class Enemy(car name: String, var hp: Int){
  def takeTurn(player: Player): Unit ={
    player.hp -= 5
  }
}