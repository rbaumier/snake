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
  private Snake snake;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.game);

    gameView = (GameView) findViewById(R.id.gameview);
    TextView scores = (TextView) findViewById(R.id.gamescores);

    this.handler = new Handler() {
      @Override
      public void handleMessage(Message message) {
        if (world.snake.gameOver) {
          gameOver();
          world.snake.gameOver = false;
        }
        snake.move(snake.direction);
        updateView(scores);
      }
    };

    initialize();
  }

  private void initialize() {
    world = new World(20, 30);
    gameView.initWorld(world);
    snake = new Snake(world);
    startTimer();
    playMusicIfActivated();
  }

  private void gameOver() {
    final Activity self = this;
    pause();
    new AlertDialog.Builder(this)
      .setIcon(android.R.drawable.ic_dialog_alert)
      .setTitle("Game Over, your score is " + world.player.score)
      .setMessage("What do you want ?")
      .setNeutralButton("Restart", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
          initialize();
        }
      })
      .setNegativeButton("Leave", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
          self.startActivity(new Intent(self, MainActivity.class));
        }
      })
      .show();
  }


  private void startTimer() {
    timer = new Timer(500);
    timer.handler = handler;
    new Thread(timer).start();
    world.spawnFruit();
  }

  private void updateView(TextView scores) {
    gameView.invalidate();
    scores.setText(
      String.valueOf(world.player.score)
    );
  }

  @Override
  public void onBackPressed() {
    final Activity self = this;
    pause();
    if (database.musicActivated())
      player.stop();

    new AlertDialog.Builder(this)
      .setIcon(android.R.drawable.ic_dialog_alert)
      .setTitle("Pause")
      .setMessage("What do you want ?")
      .setNeutralButton("Resume", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
          startTimer();
        }
      })
      .setNegativeButton("Leave", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
          self.startActivity(new Intent(self, MainActivity.class));
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
