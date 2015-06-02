package main.models;

public interface SnakeControl {
  void spawnSnake();
  void moveSnake(Direction d);
  void makeSnakeEat();
  void rotateSnake();
}
