package HumanityHandGame.models

class AttackCard(name: String, description: String, val minDamage: Int, val maxDamage: Int)
  extends Card(name, description){
  require(minDamage > 0 && maxDamage >= minDamage, "Damage values must be positive and valid range")

  def cardType: String = "Attack Card"

  override def use(user: GameEntity, target: GameEntity): Unit = {
    val damage = scala.util.Random.between(minDamage, maxDamage + 1)
    target.takeDamage(damage)
    println(s"${user.name} user $name! It dealt $damage damage to ${target.name}.")
  }

  override def toString: String = super.toString + s" (Damage: $minDamage - $maxDamage)"
}