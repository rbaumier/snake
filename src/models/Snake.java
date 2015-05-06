package models;

import java.util.ArrayList;

public class Snake {
  private ArrayList<Cell> body = new ArrayList<Cell>();
  private Direction direction = Direction.L;
  private enum Direction {
    U, // Up -> y+1
    R, // Right -> x+1
    D, // Down -> y-1
    L // Left -> x-1, default
  }

  public Snake(ArrayList<Cell> body) {
    this.body = body; // size 3 at the center
    this.spawn();
  }

  private void spawn() {

  }

  private void move(int x, int y) {

  }

  private void eat() {

  }

  private void grow() {

  }
}
