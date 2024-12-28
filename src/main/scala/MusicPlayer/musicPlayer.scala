package MusicPlayer

import scalafx.application.JFXApp3
import scalafx.scene.Scene
import javafx.fxml.FXMLLoader
import javafx.scene.layout.AnchorPane
import java.io.IOException

object musicPlayer extends JFXApp3 {

  // Override the start method from JFXApp3
  override def start(): Unit = {
    try {
      // Load the FXML file using JavaFX's FXMLLoader
      val loader = new FXMLLoader(getClass.getResource("/musicPlayer.fxml"))
      val root = loader.load[AnchorPane]() // Load JavaFX AnchorPane

      // Wrap the JavaFX AnchorPane in a ScalaFX wrapper
      val scalafxRoot = new scalafx.scene.layout.AnchorPane(root)

      // Set up the controller from the FXML file
      val controller = loader.getController[musicPlayerController]

      // Create a new ScalaFX Scene from the wrapped AnchorPane
      val scene = new Scene(scalafxRoot)

      // Set up the primary stage
      stage = new JFXApp3.PrimaryStage {
        title = "Music Player"
        scene = scene
      }

    } catch {
      case e: IOException =>
        e.printStackTrace()
    }
  }
}