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

  public boolean isDirectionUp() { return direction == Direction.U; }
  public boolean isDirectionDown() { return direction == Direction.D; }
  public boolean isDirectionLeft() { return direction == Direction.L; }
  public boolean isDirectionRight() { return direction == Direction.R; }

  public void setDirectionUp() { direction = Direction.U; }
  public void setDirectionDown() { direction = Direction.D; }
  public void setDirectionLeft() { direction = Direction.L; }
  public void setDirectionRight() { direction = Direction.R; }

}
