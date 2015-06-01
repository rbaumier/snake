package activities;

import DAO.database;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import models.Player;
import models.Snake;
import models.World;
import net.epsi.YoloSnake.R;
import timer.Timer;
import views.GameView;


public class GameActivity extends Activity {
  private Handler handler;
  private GameView gameView;
  private MediaPlayer player;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.game);


    gameView = (GameView) findViewById(R.id.gameview);
    this.handler = new Handler() {
      @Override
      public void handleMessage(Message message) {
        gameView.invalidate();
      }
    };

    World world = new World(20, 30);
    gameView.initWorld(world);

    Player player = new Player();
    Snake snake = new Snake(world);

    Timer timer = new Timer(1000);
    timer.handler = handler;
    new Thread(timer).start();

    playMusicIfActivated();
  }

  @Override
  public void onBackPressed(){
    player.stop();
    this.startActivity(new Intent(this, MainActivity.class));
  }

  private void playMusicIfActivated() {
    if (database.musicActivated()) {
      setVolumeControlStream(AudioManager.STREAM_MUSIC);
      player = MediaPlayer.create(this, R.raw.music);
      player.setLooping(true);
      player.start();
    }
  }
}
