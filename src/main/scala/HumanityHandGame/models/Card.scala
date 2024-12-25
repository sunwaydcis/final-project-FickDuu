package HumanityHandGame.models

abstract class Card(val name: String, val description: String){
  def cardType: String
  def use(user: GameEntity, target: GameEntity): Unit
  override def toString: String = s"$cardType: $name - $description"
}