package net.epsi.YoloSnake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import terminal.Terminal;

public class MyActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    final Activity self = this;

    // To show console version of the snake;
//    Terminal.main();

    Button newGame = (Button) findViewById(R.id.newGameButton);
    newGame.setOnClickListener(new Button.OnClickListener() {
      public void onClick(View v) {
        self.startActivity(new Intent(self, GameActivity.class));
      }
    });

    Button parameters = (Button) findViewById(R.id.parametersButton);
    parameters.setOnClickListener(new Button.OnClickListener() {
      public void onClick(View v) {
        self.startActivity(new Intent(self, ParametersActivity.class));
      }
    });

    Button scores = (Button) findViewById(R.id.scoresButton);
    scores.setOnClickListener(new Button.OnClickListener() {
      public void onClick(View v) {
        self.startActivity(new Intent(self, ScoresActivity.class));
      }
    });
  }
}
