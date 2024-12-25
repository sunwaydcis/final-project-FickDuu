package HumanityHandGame.services

import HumanityHandGame.models.{Player, Enemy, AttackCard, DefenseCard, SpecialCard}

class Game(player: Player){
  private var enemyHp = 20
  private var matchesPlayed = 0
  
  def start(): Unit = {
    println("Starting a new game!")
    gameLoop()
  }
  
  private def gameLoop(): Unit ={ 
    var gameOver = false
    while(!gameOver){
      val enemy = new Enemy(enemyHp)
      initializeEnemyDeck(enemy)
      println(s"Enemy appeared! $enemyHp HP")
      gameOver = playMatch(enemy)
      if(!gameOver){
        matchesPlayed += 1
        if(matchesPlayed % 2 == 0) grantRewards()
        enemyHp += 10
      }
    }
    displayScore()
  }
  
  private def initializeEnemyDeck(enemy:Enemy): Unit ={
    val cardPool = List(
      new AttackCard("Bite", "Enemy attack", 2, 4),
      new DefenseCard("Thick Skin", "Enemy defense", 3, 5),
      new SpecialCard("Roar", "Stuns for 2 rounds", "Stun")
    )
    enemy.initializeDeck(List.fill(5)(cardPool(scala.util.Random.nextInt(cardPool.size))))
  }
  
  private def playMatch(enemy: Enemy): Boolean ={
    player.hp = 50
    enemy.hp = enemyHp
    player.drawCards(5)
    enemy.drawCards(5)
    
    while (player.hp > 0 && enemy.hp > 0){
      playerTurn(enemy)
      if (enemy.hp<= 0) return false
      enemyTurn(enemy)
      if(player.hp<=0) return true
      player.drawCards(2)
      enemy.drawCards(2)
    }
    false
  }
  
  private def playerTurn(enemy: Enemy): Unit ={
    println("\nYour turn")
    player.viewHand()
    println("Select a card to play [0-${player.handSize - 1}], or -1 to skip:")
    val choice = scala.io.StdIn.readInt()
    if(choice >= 0 && choice < player.handSize){
      player.playCard(choice, enemy)
    }
    else{
      println("Turn Skipped")
    }
  }
  
  private def enemyTurn(enemy:Enemy): Unit ={
    println("\nEnemy's turn!")
    enemy.playTurn(player)
  }
  
  private def grantRewards(): Unit = {
    println("You earned a reward!")
    println("Choose a card type: [1] Attack, [2] Defense, [3] Special")
    val choice = scala.io.StdIn.readInt()
    val newCard = choice match{
      case 1 => new AttackCard("Power Slash", "Stronger attack", 3, 6)
      case 2 => new DefenseCard("Fortify", "Stronger defense", 4, 7)
      case 3 => new SpecialCard("Freeze", "Freezes enemy for 2 rounds", "Freeze")
      case _ =>
        println("Invalid choice, choose again")
        new AttackCard("Quick Strike", "Default attack card", 1, 3) //change this
    }
    player.addCardToDeck(newCard)
    println(s"You received: $newCard")
    player.hp += 10
    println(s"Your HP increased by 10. Current HP: ${player.hp}")
  }
  
  private def displayScore(): Unit = {
    val totalScore = matchesPlayed * 10 + player.deck.size * 10
    println(s"Game Over! \nYour score: $totalScore")
    Scoreboard.addScore(player.name, totalScore)
    Scoreboard.saveToFile()
  }
}