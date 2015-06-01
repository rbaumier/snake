package models;

import java.util.LinkedList;

public class Snake {
  private final World world;
  public Direction direction;
  private LinkedList<Integer[]> cells = new LinkedList<>();
  public boolean gameOver = false;

  public enum Direction {
    U, // Up
    R, // Right
    D, // Down
    L // Left (default)
  }

  public Snake(World w) {
    world = w;
    world.setSnake(this);
    direction = Direction.L;
    spawn();
  }

  public boolean isDirectionUp() { return direction == Direction.U; }
  public boolean isDirectionDown() { return direction == Direction.D; }
  public boolean isDirectionLeft() { return direction == Direction.L; }
  public boolean isDirectionRight() { return direction == Direction.R; }

  public void setDirectionUp() { direction = Direction.U; }
  public void setDirectionDown() { direction = Direction.D; }
  public void setDirectionLeft() { direction = Direction.L; }
  public void setDirectionRight() { direction = Direction.R; }

  public void spawn() {
    int height = world.height / 2;
    int start = world.width / 2;

    world.board[height][start].setHead();
    world.board[height + 1][start].setTail();
    world.board[height + 2][start].setLast();
    Integer head[] = {height, start};
    Integer tail[] = {height, start +1};
    Integer last[] = {height, start +2};
    cells.add(head);
    cells.add(tail);
    cells.add(last);
  }

  public LinkedList<Integer[]> getCells(){
    return cells;
  }

  public void move(Direction direction){

    if(direction == Direction.U && direction != Direction.D){
      Integer[] destination = cells.getFirst();
      setDirectionUp();
      if(isValidCell(destination[0]-1, destination[1])){
        Integer[] newHead = {destination[0]-1, destination[1] };
        if(world.board[destination[0]-1][destination[1]].isFruit()){
          eat();
          cells.addFirst(newHead);
        }
        else if(world.board[destination[0]-1][destination[1]].isEmpty()){
          cells.addFirst(newHead);
          cells.removeLast();
        }
        world.refreshWorldState();
      } else {
        gameOver = true;
      }
    } else if(direction == Direction.D && direction != Direction.U) {
      Integer[] destination = cells.getFirst();
      setDirectionDown();
      if(isValidCell(destination[0]+1, destination[1])){
        Integer[] newHead = {destination[0]+1, destination[1] };
        if(world.board[destination[0]+1][destination[1]].isFruit()){
          eat();
          cells.addFirst(newHead);
        }
        else if(world.board[destination[0]+1][destination[1]].isEmpty()){
          cells.addFirst(newHead);
          cells.removeLast();
        }
        world.refreshWorldState();
      } else {
        gameOver = true;
      }
    } else if(direction == Direction.L && direction != Direction.R) {
      Integer[] destination = cells.getFirst();
      setDirectionLeft();
      if(isValidCell(destination[0], destination[1]-1)){
        Integer[] newHead = {destination[0], destination[1]-1 };
        if(world.board[destination[0]][destination[1]-1].isFruit()){
          eat();
          cells.addFirst(newHead);
        }
        else if(world.board[destination[0]][destination[1]-1].isEmpty()){
          cells.addFirst(newHead);
          cells.removeLast();
        }
        world.refreshWorldState();
      } else {
        gameOver = true;
      }

    } else if(direction == Direction.R && direction != Direction.L){
      Integer[] destination = cells.getFirst();
      setDirectionRight();
      if(isValidCell(destination[0], destination[1]+1)){
        Integer[] newHead = {destination[0], destination[1]+1 };
        if(world.board[destination[0]][destination[1]+1].isFruit()){
          eat();
          cells.addFirst(newHead);
        }
        else if(world.board[destination[0]][destination[1]+1].isEmpty()){
          cells.addFirst(newHead);
          cells.removeLast();
        }
        world.refreshWorldState();
      } else {
        gameOver = true;
      }

    }
  }

  public boolean isValidCell(int height, int width){
    return world.height > height && height >= 0 && (world.board[height][width].isEmpty() || world.board[height][width].isFruit());
  }

  public void eat() {
    world.spawnFruit();
    //SCORE +
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
