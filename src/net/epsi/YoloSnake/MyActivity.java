package net.epsi.YoloSnake;

import android.app.Activity;
import android.content.Intent;
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
    final Activity self = this;

    Map<Button, Class> buttons = new HashMap<Button, Class>() {{
      put((Button) findViewById(R.id.newGameButton), GameActivity.class);
      put((Button) findViewById(R.id.parametersButton), ParametersActivity.class);
      put((Button) findViewById(R.id.scoresButton), ScoresActivity.class);
    }};

    for (Map.Entry<Button, Class> button : buttons.entrySet()) {
      button.getKey().setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v) {
          self.startActivity(new Intent(self, button.getValue()));
        }
      });
    }

    // To show console version of the snake;
    Terminal.main();
  }
}
