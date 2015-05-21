package models;

import java.util.ArrayList;

public class Snake {
  private final World world;
  public Direction direction;

  public enum Direction {
    U, // Up -> y+1
    R, // Right -> x+1
    D, // Down -> y-1
    L // Left -> x-1, default
  }

  public Snake(World world) {
    this.world = world;
    this.direction = Direction.L;
    this.spawn(world);
  }

  public void spawn(World world) {
    int height = world.height / 2;
    int start = world.width / 2;

    world.board[start][height].state = Cell.State.head;
    world.board[start + 1][height].state = Cell.State.tail;
    world.board[start + 2][height].state = Cell.State.tail;
  }

  public void move(int x, int y) {
    this.world.board[x][y].state = Cell.State.head;
  }

  public void eat() {
    grow();
  }

  public void grow() {

  }
}
