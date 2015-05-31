package activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import models.Player;
import models.Snake;
import models.World;
import net.epsi.YoloSnake.R;
import timer.Timer;
import views.GameView;


public class GameActivity extends Activity {
  private Handler handler;
  private GameView gameView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.game);

    gameView = (GameView) findViewById(R.id.gameview);
    this.handler = new Handler() {
      @Override
      public void handleMessage(Message message) {
        gameView.invalidate();
      }
    };

    World world = new World(20, 30);
    gameView.initWorld(world);

    Player player = new Player();
    Snake snake = new Snake(world);

    Timer timer = new Timer(1000);
    timer.handler = handler;
    new Thread(timer).start();
    world.spawnFruit();
    snake.move(Snake.Direction.U);
    snake.move(Snake.Direction.R);
    snake.move(Snake.Direction.D);
  }
}
