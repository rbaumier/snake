package activities;

import android.app.Activity;
import android.os.Bundle;
import net.epsi.YoloSnake.R;

public class GameActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.game);
  }
}
