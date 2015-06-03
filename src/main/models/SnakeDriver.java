package main.models;

public interface SnakeDriver {
  void spawnSnake();
  void moveSnake(Direction d);
  void makeSnakeEat();
  void rotateSnake();
}
