package models;

public class Cell {
  public State state;
  public enum State {
    empty,
    fruit,
    head,
    tail
  }

  public Cell() {
    this.state = State.empty;
  }

  public boolean isEmpty() {
    return this.state == State.empty;
  }
}
