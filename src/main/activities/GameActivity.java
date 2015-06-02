package main.activities;

import android.widget.EditText;
import main.dao.database;
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
import main.models.Direction;
import main.models.Snake;
import main.models.World;
import net.epsi.YoloSnake.R;
import main.timer.Timer;
import main.views.GameView;


public class GameActivity extends Activity {
  private static Handler handler;
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
    final TextView scores = (TextView) findViewById(R.id.gamescores);

    handler = new Handler() {
      @Override
      public void handleMessage(Message message) {
        if (world.isGameOver()) {
          gameOver();
        } else {
          world.moveSnakeSameDirection();
          updateView(scores);
        }
      }
    };

    initialize();
  }

  private void initialize() {
    world = new World(20, 30);
    gameView.initWorld(world);
    snake = new Snake(Direction.L);
    startTimer();
    playMusicIfActivated();
  }

  private void gameOver() {
    final Activity self = this;
    stopTimer();

    final EditText input = new EditText(this);
    alertDialog(this, "Game Over", "What is your name?")
      .setView(input)
      .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
          world.setPlayerName(String.valueOf(input.getText()));
          database.addScore(world.getPlayer());
          stopMusic();
          self.startActivity(new Intent(self, MainActivity.class));
        }
      })
      .show();
  }

  private void startTimer() {
    timer = new Timer(100);
    timer.handler = handler;
    new Thread(timer).start();
    world.spawnFruit();
  }

  private void updateView(TextView scores) {
    gameView.invalidate();
    scores.setText(
      String.valueOf(world.getPlayerScore())
    );
  }

  @Override
  public void onBackPressed() {
    final Activity self = this;
    stopTimer();

    alertDialog(this, "Pause", "What do you want?")
      .setNeutralButton("Resume", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
          startTimer();
        }
      })
      .setNegativeButton("Leave", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
          stopMusic();
          self.startActivity(new Intent(self, MainActivity.class));
        }
      })
      .show();
  }

  private AlertDialog.Builder alertDialog(GameActivity context, String title, String message) {
    return new AlertDialog.Builder(this)
      .setIcon(android.R.drawable.ic_dialog_alert)
      .setTitle(title)
      .setMessage(message);
  }

  private void stopMusic() {
    if (database.musicActivated())
      player.stop();
  }

  private void stopTimer() {
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
