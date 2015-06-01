package main.models;

import java.util.LinkedList;

public class Snake {
  public Direction direction;
  private LinkedList<Integer[]> body = new LinkedList<>();
  public boolean gameOver = false;

  public Snake() {
    direction = Direction.L;
    spawn();
  }

  public enum Direction {
    U, // Up
    R, // Right
    D, // Down
    L // Left (default)
  }

  public boolean isDirectionUp() { return direction == Direction.U; }
  public boolean isDirectionDown() { return direction == Direction.D; }
  public boolean isDirectionLeft() { return direction == Direction.L; }
  public boolean isDirectionRight() { return direction == Direction.R; }

  public void setDirectionUp() { direction = Direction.U; }
  public void setDirectionDown() { direction = Direction.D; }
  public void setDirectionLeft() { direction = Direction.L; }
  public void setDirectionRight() { direction = Direction.R; }

  public LinkedList<Integer[]> getCells(){
    return body;
  }

  public void addToBody(Integer[] cellCoords) {
    body.add(cellCoords);
  }

  public void move(Direction direction){
    if(direction == Direction.U && direction != Direction.D){
      Integer[] destination = body.getFirst();
      setDirectionUp();
      if(isNotBusy(destination[1], destination[0]-1)){
        Integer[] newHead = {destination[0]-1, destination[1] };
        if(world.board[destination[0]-1][destination[1]].isFruit()){
          eat();
          body.addFirst(newHead);
        }
        else if(world.board[destination[0]-1][destination[1]].isEmpty()){
          body.addFirst(newHead);
          body.removeLast();
        }
        world.refreshWorldState();
      } else {
        gameOver = true;
      }
    } else if(direction == Direction.D && direction != Direction.U) {
      Integer[] destination = body.getFirst();
      setDirectionDown();
      if(isNotBusy(destination[1], destination[0]+1)){
        Integer[] newHead = {destination[0]+1, destination[1] };
        if(world.board[destination[0]+1][destination[1]].isFruit()){
          eat();
          body.addFirst(newHead);
        }
        else if(world.board[destination[0]+1][destination[1]].isEmpty()){
          body.addFirst(newHead);
          body.removeLast();
        }
        world.refreshWorldState();
      } else {
        gameOver = true;
      }
    } else if(direction == Direction.L && direction != Direction.R) {
      Integer[] destination = body.getFirst();
      setDirectionLeft();
      if(isNotBusy(destination[1]-1, destination[0])){
        Integer[] newHead = {destination[0], destination[1]-1 };
        if(world.board[destination[0]][destination[1]-1].isFruit()){
          eat();
          body.addFirst(newHead);
        }
        else if(world.board[destination[0]][destination[1]-1].isEmpty()){
          body.addFirst(newHead);
          body.removeLast();
        }
        world.refreshWorldState();
      } else {
        gameOver = true;
      }

    } else if(direction == Direction.R && direction != Direction.L){
      Integer[] destination = body.getFirst();
      setDirectionRight();
      if(isNotBusy(destination[1]+1, destination[0])){
        Integer[] newHead = {destination[0], destination[1]+1 };
        if(world.board[destination[0]][destination[1]+1].isFruit()){
          eat();
          body.addFirst(newHead);
        }
        else if(world.board[destination[0]][destination[1]+1].isEmpty()){
          body.addFirst(newHead);
          body.removeLast();
        }
        world.refreshWorldState();
      } else {
        gameOver = true;
      }

    }
  }

  public void eat() {
    world.spawnFruit();
    world.player.score++;
  }

  public void rotate() {
    if (isDirectionUp()) {
      move(Direction.R);
    } else if (isDirectionRight()) {
      move(Direction.D);
    } else if (isDirectionDown()) {
      move(Direction.L);
    } else {
      move(Direction.U);
    }
  }

}
