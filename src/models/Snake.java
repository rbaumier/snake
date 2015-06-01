package models;

import java.util.LinkedList;

public class Snake {
  private final World world;
  private Direction direction;
  private LinkedList<Integer[]> cells = new LinkedList<>();

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

  public void move(Direction direction){

    if(direction == Direction.U && direction != Direction.D){
      Integer[] head = cells.getFirst();
      Integer[] last = cells.getLast();
      setDirectionUp();
      if(isValidCell(head[0]-1, head[1])){
        if(world.board[head[0]-1][head[1]].isFruit()){
          eat();
          world.spawnFruit();
          world.board[head[0]][head[1]].setTail();
          world.board[head[0]-1][head[1]].setHead();
          Integer[] newHead = {head[0]-1, head[1]};
          cells.addFirst(newHead);
        }
        else if(world.board[head[0]-1][head[1]].isEmpty()){
          world.board[last[1]][last[0]].setEmpty();
          Integer[] newHead = {head[0]-1, head[1]};
          cells.removeLast();
          Integer[] newLast = cells.get(cells.size() - 1);
          cells.addFirst(newHead);
          world.board[newLast[1]][newLast[0]].setLast();
          world.board[head[0]][head[1]].setTail();
          world.board[head[0]-1][head[1]].setHead();

        }
      } else {
        //GAME OVER
      }
    } else if(direction == Direction.D && direction != Direction.U) {
      Integer[] head = cells.getFirst();
      Integer[] last = cells.getLast();
      setDirectionDown();
      if(isValidCell(head[0]+1, head[1])){
        if(world.board[head[0]+1][head[1]].isFruit()){
          eat();
          world.spawnFruit();
          world.board[head[0]][head[1]].setTail();
          world.board[head[0]-1][head[1]].setHead();
          Integer[] newHead = {head[0]+1, head[1]};
          cells.addFirst(newHead);
        }
        else if(world.board[head[0]+1][head[1]].isEmpty()){
          Integer[] newHead = {head[0]+1, head[1]};
          cells.removeLast();
          world.board[last[0]][last[1]].setEmpty();
          Integer[] newLast = cells.get(cells.size() - 1);
          cells.addFirst(newHead);
          world.board[newLast[0]][newLast[1]].setLast();
          world.board[head[0]][head[1]].setTail();
          world.board[head[0]+1][head[1]].setHead();

        }
      } else {
        //GAME OVER
      }
    } else if(direction == Direction.L && direction != Direction.R) {
      Integer[] head = cells.getFirst();
      Integer[] last = cells.getLast();
      setDirectionLeft();
      if(isValidCell(head[0], head[1]-1)){
        if(world.board[head[0]][head[1]-1].isFruit()){
          eat();
          world.spawnFruit();
          world.board[head[0]][head[1]].setTail();
          world.board[head[0]][head[1]-1].setHead();
          Integer[] newHead = {head[0], head[1]-1};
          cells.addFirst(newHead);
        }
        else if(world.board[head[0]][head[1]-1].isEmpty()){
          world.board[last[0]][last[1]].setEmpty();
          Integer[] newHead = {head[0], head[1]-1};
          cells.removeLast();
          Integer[] newLast = cells.getLast();
          cells.addFirst(newHead);
          world.board[newLast[1]][newLast[0]].setLast();
          world.board[head[0]][head[1]].setTail();
          world.board[head[0]][head[1]-1].setHead();
        }
      } else {
        //GAME OVER
      }

    } else if(direction == Direction.R && direction != Direction.L){
      Integer[] head = cells.getFirst();
      Integer[] last = cells.getLast();
      setDirectionRight();
      if(isValidCell(head[0], head[1]+1)){
        if(world.board[head[0]][head[1]+1].isFruit()){
          eat();
          world.spawnFruit();
          world.board[head[0]][head[1]].setTail();
          world.board[head[0]][head[1]+1].setHead();
          Integer[] newHead = {head[0], head[1]+1};
          cells.addFirst(newHead);
        }
        else if(world.board[head[0]][head[1]+1].isEmpty()){
          world.board[last[1]][last[0]].setEmpty();
          Integer[] newHead = {head[0], head[1]+1};
          cells.removeLast();
          Integer[] newLast = cells.getLast();
          cells.addFirst(newHead);
          world.board[newLast[1]-1][newLast[0]+1].setLast();
          world.board[head[0]][head[1]].setTail();
          world.board[head[0]][head[1]+1].setHead();

        }
      } else {
        //GAME OVER
      }
    }
  }

  public boolean isValidCell(int height, int width){
    return world.height > height && height >= 0 && (world.board[height][width].isEmpty() || world.board[height][width].isFruit());
  }

  public void eat() {
    //SCORE +
  }

  public void rotate() {
    if (isDirectionUp()) {
      setDirectionRight();
    } else if (isDirectionRight()) {
      setDirectionDown();
    } else if (isDirectionDown()) {
      setDirectionLeft();
    } else {
      setDirectionUp();
    }
  }

}
