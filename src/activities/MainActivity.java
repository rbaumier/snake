package activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import DAO.database;
import models.Player;
import net.epsi.YoloSnake.R;

import java.util.*;

public class MainActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    // To show console version of the snake;
    //    Terminal.main();

    database.init(getApplicationContext());

    HashMap<Button, Class> buttons = defineButtons();
    defineActions(buttons);
    initializePreferences();

    // to remove once we have a game which creates scores
    database.putPlayers(new ArrayList<Player>() {{
      add(new Player("Jean", 180));
      add(new Player("Bobby", 200));
    }});
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
    boolean music = pref.getBoolean("music", true); // set music to true if doesn't exist
    editor.putBoolean("music", music);
    editor.apply();
  }
}
