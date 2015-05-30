package models;

import java.util.ArrayList;

public class Snake {
  private final World world;
  private Direction direction;

  public enum Direction {
    U, // Up -> y+1
    R, // Right -> x+1
    D, // Down -> y-1
    L // Left -> x-1, default
  }

  public Snake(World w) {
    world = w;
    direction = Direction.L;
    spawn();
  }

  public void spawn() {
    int height = world.height / 2;
    int start = world.width / 2;

    world.board[start][height].setHead();
    world.board[start + 1][height].setTail();
    world.board[start + 2][height].setTail();
  }

  public void move(int x, int y) {
    world.board[x][y].setHead();
  }

  public void eat() {
    grow();
  }

  public void grow() {

  }
}
