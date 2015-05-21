package net.epsi.YoloSnake;

import android.app.Activity;
import android.os.Bundle;
import terminal.Terminal;

public class MyActivity extends Activity {
  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    // To show console version of the snake;
    Terminal.main();
  }
}
