sealed trait Card{
  def name: String
  def effect(player: Player): Unit
}

case class AttackCard(name: Sting, damage: Int) extends Card {
  def effect(player: Player): Unit = {
    player.hp -= damage
  }
}

case class HealCard(name: String, healing: Int) extends Card {
  def effect(player: Player): Unit = {
    player.hp += healing
  }
}