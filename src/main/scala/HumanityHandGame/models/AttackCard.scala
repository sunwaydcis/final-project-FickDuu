package HumanityHandGame.models

class AttackCard(name: String, description: String, val damage: Int) extends Card(name, description){
  def cardType: String = "Attack Card"
}