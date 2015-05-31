package activities;

import android.app.Activity;
import android.os.Bundle;
import controllers.GameController;
import models.Player;
import models.Snake;
import models.World;
import net.epsi.YoloSnake.R;
import timer.Timer;

public class GameActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.game);

    World world = new World(20, 20);
    Player player = new Player();
    Snake snake = new Snake(world);
    GameController gc = new GameController(world);
    Timer timer = new Timer(1000, gc);
    new Thread(timer).start();
  }
}
