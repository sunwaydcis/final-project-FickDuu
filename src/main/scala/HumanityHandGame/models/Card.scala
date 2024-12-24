package HumanityHandGame.models

abstract class Card(val name: String, val description: String){
  def cardType: String
  override def toString: String = s"$cardType: $name - $description"
}