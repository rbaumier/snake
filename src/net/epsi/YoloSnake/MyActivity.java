package net.epsi.YoloSnake;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import terminal.Terminal;

import java.util.HashMap;
import java.util.Map;

public class MyActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    // To show console version of the snake;
    Terminal.main();

    HashMap<Button, Class> buttons = defineButtons();
    defineActions(buttons);
    initializePreferences();
  }

  private HashMap<Button, Class> defineButtons() {
    return new HashMap<Button, Class>() {{
      put((Button) findViewById(R.id.newGameButton), GameActivity.class);
      put((Button) findViewById(R.id.parametersButton), ParametersActivity.class);
      put((Button) findViewById(R.id.scoresButton), ScoresActivity.class);
    }};
  }

  private void defineActions(HashMap<Button, Class> buttons) {
    final Activity self = this;
    for (Map.Entry<Button, Class> button : buttons.entrySet()) {
      button.getKey().setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v) {
          self.startActivity(new Intent(self, button.getValue()));
        }
      });
    }
  }

  private void initializePreferences() {
    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
    SharedPreferences.Editor editor = pref.edit();

    // set music to true if doesn't exist
    boolean music = pref.getBoolean("music", true);
    editor.putBoolean("music", music);

    editor.apply();
  }
}
