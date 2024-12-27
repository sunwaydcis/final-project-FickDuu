import ch.makery.address.LoginScene
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.stage.Stage
import services.Scoreboard

object HumanityHand extends JFXApp3{
  override def start(): Unit = {
    Scoreboard.loadFromFile()
    
    this.stage = new PrimaryStage{
      title = "Humanity's Hand"
      scene  = LoginScene()
    }
  }
}