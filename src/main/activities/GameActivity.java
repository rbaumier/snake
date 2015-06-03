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
import main.models.Player;
import main.models.Snake;
import main.models.Game;
import net.epsi.YoloSnake.R;
import main.timer.Timer;
import main.views.GameView;

public class GameActivity extends Activity {
  private GameView gameView;
  private MediaPlayer player;
  private Game game;
  private Timer timer;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.game);
    gameView = (GameView) findViewById(R.id.gameview);
    init();
  }

  private void init() {
    game = new Game(20, 30, new Player(), new Snake(Direction.L));
    game.start();
    timer = new Timer(getNewHandler(), 100);
    new Thread(timer).start();
    playMusicIfActivated();
  }

  private void gameOver() {
    showGameOverDialog();
    timer.stop();
  }

  private void showGameOverDialog() {
    final EditText input = new EditText(this);
    final Activity self = this;
    alertDialog(this, "Game Over", "What is your name?")
      .setView(input)
      .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
          game.setPlayerName(String.valueOf(input.getText()));
          database.addScore(game.getPlayer());
          stopMusicIfActivated();
          self.startActivity(new Intent(self, MainActivity.class));
        }
      })
      .show();
  }

  private void updateView(TextView scores) {
    gameView.invalidate();
    scores.setText(String.valueOf(game.getPlayerScore()));
  }

  @Override
  public void onBackPressed() {
    final Activity self = this;
    timer.stop();
    alertDialog(this, "Pause", "What do you want?")
      .setNeutralButton("Resume", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
          timer.start();
        }
      })
      .setNegativeButton("Leave", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
          stopMusicIfActivated();
          self.startActivity(new Intent(self, MainActivity.class));
        }
      })
      .show();
  }

  private AlertDialog.Builder alertDialog(GameActivity context, String title, String message) {
    return new AlertDialog.Builder(this)
      .setTitle(title)
      .setMessage(message);
  }

  private void stopMusicIfActivated() {
    if (database.musicActivated())
      player.stop();
  }

  private void playMusicIfActivated() {
    if (database.musicActivated()) {
      setVolumeControlStream(AudioManager.STREAM_MUSIC);
      player = MediaPlayer.create(this, R.raw.music);
      player.setLooping(true);
      player.start();
    }
  }

  public Handler getNewHandler() {
    final TextView scores = (TextView) findViewById(R.id.gamescores);
    return new Handler(new Handler.Callback() {
      @Override
      public boolean handleMessage(Message message) {
        if (game.isGameOver()) {
          gameOver();
        } else {
          game.moveSnakeSameDirection();
          updateView(scores);
        }
        return true;
      }
    });
  }
}
