package HumanityHandGame.models

class SpecialCard(name:String, description:String, val effect: String) 
  extends Card(name, description){
  
  def cardType: String = "Special Card"
  
  override def use(user:GameEntity, target: GameEntity): Unit ={
    effect match{
      case "stun" => target.applyEffect("stun", 2)
      case "blind" => target.applyEffect("blind", 2)
      case _ => println(s"Unknown effect: $effect")
    }
    
    println(s"${user.name} used $name! Effect applied: $effect")
  }
  
  override def toString: String = super.toString + s"(Effect:$effect)"
}