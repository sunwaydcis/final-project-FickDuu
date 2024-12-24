package HumanityHandGame

import HumanityHandGame.ui.LoginScene
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.stage.Stage

object HumanityHand extends JFXApp3{
  override def start(): Unit = {
    this.stage = new PrimaryStage{
      title = "Humanity's Hand"
      scene  = LoginScene()
    }
  }
}