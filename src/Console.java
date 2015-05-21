import models.*;

public class Console {
  public static void main(String[] args) {
    Snake snake = new Snake();
    World world = new World(20, 20, snake);
  }
}
