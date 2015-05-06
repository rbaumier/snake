package models;

import java.util.ArrayList;

public class Snake {
  public ArrayList<Cell> body = new ArrayList<Cell>();
  public Direction direction;
  public enum Direction {
    U, // Up -> y+1
    R, // Right -> x+1
    D, // Down -> y-1
    L // Left -> x-1, default
  }

  public Snake(ArrayList<Cell> body) {
    this.body = body; // size 3 at the center
    this.direction = Direction.L;
    this.spawn();
  }

  public void spawn() {

  }

  public void move(int x, int y) {

  }

  public void eat() {
    grow();
  }

  public void grow() {

  }
}
