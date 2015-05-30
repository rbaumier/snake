package models;

public class Cell {
  private State state;
  private enum State {
    empty,
    fruit,
    head,
    tail
  }

  public Cell() {
    state = State.empty;
  }

  public boolean isEmpty() { return state == State.empty; }
  public boolean isFruit() { return state == State.fruit; }
  public boolean isHead() { return state == State.head; }
  public boolean isTail() { return state == State.tail; }

  public void setFruit() { state = State.fruit; }
  public void setHead() { state = State.head; }
  public void setTail() { state = State.tail;}
}
