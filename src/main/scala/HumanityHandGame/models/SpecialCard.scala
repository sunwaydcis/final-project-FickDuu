package HumanityHandGame.models

class SpecialCard(name: String, description: String, val effect: String) extends Card(name, description){
  def cardType: String = "Special Card"
}