package ch.makery.address.view

import javafx.fxml.Initializable
import scalafx.event.ActionEvent
import scalafx.scene.control.{ComboBox, ProgressBar}
import scalafx.scene.layout.Pane
import scalafx.scene.media.MediaPlayer

import java.util.ResourceBundle
import scala.collection.mutable.ArrayBuffer
import scala.sys.process.processInternal.File;

class musicPlayerController{

  @FXML private var body: Pane = _
  @FXML private val songName: Label = null
  @FXML private var playButton, pauseButton, resetButton, prevButton, nextButton: Button = null
  @FXML private val speedBox: ComboBox[String] = null
  @FXML private val volumeSlider: Slider = null
  @FXML private val songProgBar: ProgressBar = null

  private val songs = ArrayBuffer.[File]
  private var songNumber = 0
  private var mediaPlayer: MediaPlayer = _
  private var media: Media = null

  def initialize(): Unit = {
    val directory = new File("music")
    directory.listFiles().foreach(songs.append)

    media = new Media(songs.head.toURI.toString)
    mediaPlayer = new MediaPlayer(media)
    songName.text = songs.head.getName

    speedBox.items ++= Seq("25%", "50%", "75%", "100%", "125%", "150%", "175%", "200%")
    speedBox.onAction = _ => changeSpeed()
    volumeSlider.value <==> mediaPlayer.volume
  }

  private Media media;
  private MediaPlayer mediaPlayer;

  private File directory;
  private File[] files;

  private ArrayList<File> songs;
  private int songNumber;

  private int[] speeds = {25, 50, 75, 100, 125, 150, 175, 200};
  private Timer timer;
  private TimerTask task;
  private boolean running;

  @Override
  public void initialize(url arg0, ResourceBundle arg1){
    songs = new ArrayList<File>();

    directory = new File("music");

    files = directory.listFiles();

    if(files != null){
      for(File file : files) {
        songs.add(file);
        System.out.println(file);
      }
    }

    media = new Media(songs.get(songNumber).toURI().toString());
    mediaPlayer = new MediaPlayer(media);

    songName.setText(songs.get(songNumber).getName());

    for(int i = 0; i < speeds.length; i++){
      speedBox.getItems().add(Integer.toString(speeds[i]) + "%");
    }

    speedBox.setOnAction(this::changeSpeed);
    volumeSlider.valueProperty().addListener(new ChangeListener<Number>(){

      @Override
      public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2){
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
      }
    });

    songProgBar.setStyle("-fx-accent: #f23d67;")
  }

  public void playMedia(){

    beginTimer();
    changeSpeed(null);
    mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
    mediaPlayer.play();
  }

  public void pauseMedia() {
    cancelTimer();
    mediaPlayer.pause();
  }

  public void resetMedia(){

    songProgBar.setProgress(0);
    mediaPlayer.seek(Duration.seconds(0));
  }

  public void prevMedia() {
    if (songNumber > 0) {
      songNumber --;

      mediaPlayer.stop();

      if(running){
        cancelTimer();
      }

      media = new Media(songs.get(songNumber).toURI().toString());
      mediaPlayer = new MediaPlayer(media);

      songName.setText(songs.get(songNumber).getName());

      playMedia();
    }
    else {
      songNumber = songs.size() - 1;

      mediaPlayer.stop();
      if (running) {
        cancelTimer();
      }

      media = new Media(songs.get(songNumber).toURI().toString());
      mediaPlayer = new MediaPlayer(media);

      songName.setText(songs.get(songNumber).getName());

      playMedia()
    }
  }

  public void nextMedia() {
    if(songNumber < songs.size() - 1){
      songNumber++;

      mediaPlayer.stop();
      if (running) {
        cancelTimer();
      }

      media = new Media(songs.get(songNumber).toURI().toString());
      mediaPlayer = new MediaPlayer(media);

      songName.setText(songs.get(songNumber).getName());

      playMedia();
    }
    else{
      songNumber = 0;

      mediaPlayer.stop();
      if (running) {
        cancelTimer();
      }

      media = new Media(songs.get(songNumber).toURI().toString());
      mediaPlayer = new MediaPlayer(media);

      songName.setText(songs.get(songNumber).getName());

      playMedia()
    }
  }

  public void changeSpeed(ActionEvent event){
    if(speedBox.getValue() == null){
      mediaPlayer.setRate(1);
    }
    else{
//      mediaPlayer.setRate(Integer.parseInt(speedBox.getValue()) * 0.01);
      mediaPlayer.setRate(Integer.parseInt(speedBox.getValue().substring(beginIndex(0, speedBox.getValue().length() - 1))) * 0.01);
    }
  }

  public void beginTimer(){
    timer = new Timer();
    task = new TimerTask(){
        public void run(){
          running = true;
          double current = mediaPlayer.getCurrentTime().toSeconds();
          double end = media.getDuration().toSeconds();
          System.out.println(current/end);
          songProgBar.setProgress(current/end);

          if(current/end == 1){
            cancelTimer();
          }
        }
    };
    timer.scheduleAtFixedRate(task, 0,1000);
  }

  public void cancelTimer() {
    running = false;
    timer.cancel();

  }
}