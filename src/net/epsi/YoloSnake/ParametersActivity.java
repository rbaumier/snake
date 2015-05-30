package net.epsi.YoloSnake;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ParametersActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.parameters);

    SharedPreferences pref = getApplicationContext().getSharedPreferences("SnakePreferences", MODE_PRIVATE);
    SharedPreferences.Editor editor = pref.edit();

    Button musicButton = (Button) findViewById(R.id.music);
    Boolean musicValue = pref.getBoolean("music", true);
    musicButton.setText(musicButtonText(musicValue));

    Button resetScores = (Button) findViewById(R.id.resetScores);

    musicButton.setOnClickListener(new Button.OnClickListener() {
      public void onClick(View v) {
        Boolean newMusicValue = !pref.getBoolean("music", false);
        editor.putBoolean("music", newMusicValue);
        editor.apply();
        musicButton.setText(musicButtonText(newMusicValue));
      }
    });
  }

  private String musicButtonText(Boolean musicValue) {
    return "Music " + (musicValue ? "ON" : "OFF");
  }
}
