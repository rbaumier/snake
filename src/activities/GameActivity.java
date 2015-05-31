package activities;

import android.app.Activity;
import android.os.Bundle;
import controllers.GameController;
import models.Player;
import models.Snake;
import models.World;
import net.epsi.YoloSnake.R;
import timer.Timer;
import views.GameView;

public class GameActivity extends Activity {
  GameView gameView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.game);
    gameView =  (GameView) findViewById(R.id.gameview);
    World world = new World(20, 30);
    gameView.initWorld(world);

    Player player = new Player();
    Snake snake = new Snake(world);

    GameController gc = new GameController(world);
    Timer timer = new Timer(1000, gc);
    new Thread(timer).start();
  }
}
