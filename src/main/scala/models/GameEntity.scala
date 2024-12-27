package models

trait GameEntity{
  val name: String
  var hp: Int
  private var shield: Int = 0

  def takeDamage(damage: Int): Unit = {
    val effectiveDamage = Math.max(damage - shield, 0)
    shield = Math.max(shield - damage, 0)
    hp -= effectiveDamage
    println(s"$name took $effectiveDamage damage! HP:$hp, Shield: $shield")
  }
  
  def heal(amount: Int): Unit = {
    hp += amount
    println(s"$name healed for $amount HP. Current HP: $hp")
  }
  
  def addShield(amount: Int): Unit = {
    shield += amount
    println(s"$name gained $amount shield. Current Shield: $shield")
  }
  
  def applyEffect(effect: String, duration: Int): Unit ={
    println(s"$name is affected by $effect for $duration rounds")
  }
}