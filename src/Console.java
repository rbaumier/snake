import models.*;
import timer.Timer;

public class Console {
  public static void main(String[] args) {
    World world = new World(20, 20);
    Snake snake = new Snake(world);

    Timer timer = new Timer(1000);
    timer.run();
  }
}
