package HumanityHandGame.models

class DefenseCard(name: String, description: String, val defense: Int) extends Card(name, description){
  def cardType: String = "Defense Card"
}