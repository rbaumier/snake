package activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import DAO.database;
import net.epsi.YoloSnake.R;

public class ParametersActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.parameters);
    SharedPreferences pref = getApplicationContext().getSharedPreferences("SnakePreferences", MODE_PRIVATE);
    InitializeButtons(pref);
  }

  private void InitializeButtons(SharedPreferences pref) {
    initializeMusicButton(pref);
    initializeResetButton(pref);
    pref.edit().apply();
  }

  private void initializeMusicButton(SharedPreferences pref) {
    Button musicButton = (Button) findViewById(R.id.music);
    Boolean musicValue = pref.getBoolean("music", true);
    musicButton.setText(musicButtonText(musicValue));
    musicButton.setOnClickListener(new Button.OnClickListener() {
      public void onClick(View v) {
        musicButton.setText(musicButtonText(
          database.switchMusicValue()
        ));
      }
    });
  }

  private String musicButtonText(Boolean musicValue) {
    return "Music " + (musicValue ? "ON" : "OFF");
  }

  private void initializeResetButton(SharedPreferences pref) {
    final Activity self = this;
    Button resetScores = (Button) findViewById(R.id.resetScores);
    resetScores.setOnClickListener(new Button.OnClickListener() {
      public void onClick(View v) {
        new AlertDialog.Builder(self)
          .setIcon(android.R.drawable.ic_dialog_alert)
          .setTitle("Reset scores")
          .setMessage("Are you sure you want to reset scores?")
          .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
              database.clearPlayers();
            }
          })
          .setNegativeButton("No", null)
          .show();
      }
    });
  }

}
