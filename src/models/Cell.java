package models;

public class Cell {
  private int x; // honrizontal
  private int y; // vertical
  private State state = State.empty;
  private enum State {
    empty,
    fruit,
    head,
    tail
  }

  private boolean isEmpty() {

  }
}
