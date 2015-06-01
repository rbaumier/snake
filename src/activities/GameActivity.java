package activities;

import DAO.database;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
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
  private World world;
  private Timer timer;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.game);

    gameView = (GameView) findViewById(R.id.gameview);
    TextView scores = (TextView) findViewById(R.id.gamescores);

    this.handler = new Handler() {
      @Override
      public void handleMessage(Message message) {
        updateView(scores);
      }
    };

    world = new World(20, 30);
    gameView.initWorld(world);

    Snake snake = new Snake(world);

    startTimer();
    playMusicIfActivated();
  }

  private void startTimer() {
    timer = new Timer(1000);
    timer.handler = handler;
    new Thread(timer).start();
  }

  private void updateView(TextView scores) {
    gameView.invalidate();
    scores.setText(
      String.valueOf(world.player.score++) // OUBLIE PAS DE VIRER LE ++ HEIN
    );
  }

  @Override
  public void onBackPressed(){
    final Activity self = this;
    pause();
    if(database.musicActivated())
      player.stop();

    new AlertDialog.Builder(this)
      .setIcon(android.R.drawable.ic_dialog_alert)
      .setTitle("Leave")
      .setMessage("Do you really want to leave ? :(")
      .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
          self.startActivity(new Intent(self, MainActivity.class));
        }
      })
      .setNegativeButton("No", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
          startTimer();
        }
      })
      .show();
  }

  private void pause() {
    timer.stop();
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
