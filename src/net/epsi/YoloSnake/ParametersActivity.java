package net.epsi.YoloSnake;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Collections;
import java.util.Set;

public class ParametersActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.parameters);
    final Activity self = this;

    SharedPreferences pref = getApplicationContext().getSharedPreferences("SnakePreferences", MODE_PRIVATE);
    SharedPreferences.Editor editor = pref.edit();

    Button musicButton = (Button) findViewById(R.id.music);
    Boolean musicValue = pref.getBoolean("music", true);
    musicButton.setText(musicButtonText(musicValue));
    musicButton.setOnClickListener(new Button.OnClickListener() {
      public void onClick(View v) {
        Boolean newMusicValue = !pref.getBoolean("music", false);
        editor.putBoolean("music", newMusicValue);
        editor.apply();
        musicButton.setText(musicButtonText(newMusicValue));
      }
    });

    Button resetScores = (Button) findViewById(R.id.resetScores);
    resetScores.setOnClickListener(new Button.OnClickListener() {
      public void onClick(View v) {
        new AlertDialog.Builder(self)
          .setIcon(android.R.drawable.ic_dialog_alert)
          .setTitle("Reset scores")
          .setMessage("Are you sure you want to reset scores?")
          .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
              editor.putStringSet("scores", Collections.emptySet());
            }
          })
          .setNegativeButton("No", null)
          .show();
      }
    });
  }

  private String musicButtonText(Boolean musicValue) {
    return "Music " + (musicValue ? "ON" : "OFF");
  }
}
