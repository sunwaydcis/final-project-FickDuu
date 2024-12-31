package MusicPlayer

import scalafx.application.JFXApp3
import scalafx.scene.Scene
import javafx.fxml.FXMLLoader
import javafx.scene.layout.AnchorPane
import java.io.IOException

object Main extends JFXApp3 {

  override def start(): Unit = {
    try {
      // Load FXML file
      val loader = new FXMLLoader(getClass.getResource("/musicPlayer.fxml"))
      val root = loader.load[AnchorPane]()

      // Wrap JavaFX AnchorPane in ScalaFX AnchorPane
      val scalafxRoot = new scalafx.scene.layout.AnchorPane(root)

      // Set up the primary stage
      stage = new JFXApp3.PrimaryStage {
        title = "Music Player"
        scene = new Scene(scalafxRoot)
      }
    } catch {
      case e: IOException =>
        e.printStackTrace()
    }
  }
}