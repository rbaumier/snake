package terminal;
import controllers.Controller;
import models.*;
import timer.Timer;

public class Terminal {
  public static void main() {
    World world = new World(20, 20);
    Snake snake = new Snake(world);

    Controller controller = new Controller(world);

    Timer timer = new Timer(1000, controller);
    new Thread(timer).start();
  }
}
