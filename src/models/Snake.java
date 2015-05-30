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

  public void setDirectionUp() { direction = Direction.U; }
  public void setDirectionDown() { direction = Direction.D; }
  public void setDirectionLeft() { direction = Direction.L; }
  public void setDirectionRight() { direction = Direction.R; }

  public void spawn() {
    int height = world.height / 2;
    int start = world.width / 2;

    world.board[start][height].setHead();
    world.board[start + 1][height].setTail();
    world.board[start + 2][height].setLast();
  }

  public void move(Direction direction){
    /*
    TODO : Pour chaque direction :
              Si case destination (vase de la tete + deplacement)
                 avancer la tete et avancer la last
              Sinon
                 juste avancer la tete
   */
    if(direction == Direction.U){
      setDirectionUp();
    } else if(direction == Direction.D) {
      setDirectionDown();
    } else if(direction == Direction.L) {
      setDirectionLeft();
    } else {
      setDirectionRight();
    }
  }

  public void eat() {
    grow();
  }

  public void grow() {

  }
}
