package main.views;
import main.models.*;
import main.timer.Timer;

public class Terminal {
  public static void main() {
    World world = new World(20, 20);
    Snake snake = new Snake(world);

    Timer timer = new Timer(1000);
    new Thread(timer).start();
  }
}
