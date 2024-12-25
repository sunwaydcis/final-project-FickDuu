package HumanityHandGame.models

class DefenseCard(name: String, description: String, val heal: Int, val shield: Int) 
  extends Card(name, description){
  
  require(heal >= 0 && shield >= 0, "Heal and Shield values must be non-negative")
  
  def cardType: String = "Defense Card"
  
  override def use(user: GameEntity, target: GameEntity): Unit = {
    user.heal(heal)
    user.addShield(shield)
    println(s"${user.name} used $name!")
  }
  
  override def toString: String = super.toString + s" (Heal:$heal, Shield: $shield)"
} 