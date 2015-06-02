package main.models;

import java.util.LinkedList;

public class Snake {
  private Direction direction;
  private LinkedList<Integer[]> body = new LinkedList<>();
  private SnakeControl control;

  public Snake(Direction d) {
    direction = d;
  }

  public LinkedList<Integer[]> getCells(){
    return body;
  }

  public void addToBody(Integer[] cellCoords) {
    body.add(cellCoords);
  }

  public boolean isGoingUp() { return direction == Direction.U; }
  public boolean isGoingDown() { return direction == Direction.D; }
  public boolean isGoingLeft() { return direction == Direction.L; }
  public boolean isGoingRight() { return direction == Direction.R; }

  public void goUp() { direction = Direction.U; }
  public void goDown() { direction = Direction.D; }
  public void goLeft() { direction = Direction.L; }
  public void goRight() { direction = Direction.R; }

  public void addNewHead(Integer[] newHead) { body.addFirst(newHead); }
  public void removeLast() { body.removeLast(); }
  public Integer[] getHead() { return body.getFirst(); }

  public Direction getCurrentDirection() {
    return direction;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }
}
