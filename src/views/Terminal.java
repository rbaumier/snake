package views;
import controllers.GameController;
import models.*;
import timer.Timer;

public class Terminal {
  public static void main() {
    World world = new World(20, 20);
    Snake snake = new Snake(world);

    GameController controller = new GameController(world);

    Timer timer = new Timer(1000, controller);
    new Thread(timer).start();
  }
}
