package models;

public class Cell {
  public int x; // honrizontal
  public int y; // vertical
  public State state;
  public enum State {
    empty,
    fruit,
    head,
    tail
  }

  public Cell(int x, int y) {
    this.x = x;
    this.y = y;
    this.state = State.empty;
  }

  public boolean isEmpty() {
    return this.state == State.empty;
  }
}
