import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.control.Button
import scalafx.scene.layout.VBox

object HumanityHand extends JFXApp3:
  val player = new Player("Hero", 100, new Deck())
  val enemy = new Enemy("Goblin", 100)
  val game = new Game(player, enemy)
  
  stage = new JFXApp3.PrimaryStage{
    title.value = "Humanity's Hand"
    scene = new Scene{
      root = new VBox{
        children = Seq(
          new Button("Start Game"){
            onAction = _ => {
              game.playRound(
                println(s"Player HP: ${player.hp}, Enemy HP: ${enemy.hp}")
              )
            }
          }
        )
      }
    }
  }
end HumanityHand

