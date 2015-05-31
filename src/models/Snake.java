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
      if(isValidCell(head[0]-1, head[1])&& world.board[head[0]-1][head[1]].isFruit()){
        eat();
        world.spawnFruit();
        world.board[head[0]][head[1]].setTail();
        world.board[head[0]-1][head[1]].setHead();
        Integer[] newHead = {head[0]-1, head[1]};
        cells.addFirst(newHead);
      } else if(isValidCell(head[0]-1, head[1])&& world.board[head[0]-1][head[1]].isEmpty()) {
        Integer[] newHead = {head[0]-1, head[1]};
        cells.removeLast();
        Integer[] newLast = cells.get(cells.size() - 1);
        cells.addFirst(newHead);
        world.board[newLast[0]][newLast[1]].setEmpty();
        world.board[head[0]][head[1]].setTail();
        world.board[head[0]-1][head[1]].setHead();
        world.board[last[0]][last[1]].setEmpty();
      } else {
        //GAME OVER
      }
    } else if(direction == Direction.D && direction != Direction.U) {
      Integer[] head = cells.getFirst();
      Integer[] last = cells.getLast();
      setDirectionDown();
      if(isValidCell(head[0]+1, head[1])&& world.board[head[0]+1][head[1]].isFruit()){
        eat();
        world.spawnFruit();
        world.board[head[0]][head[1]].setTail();
        world.board[head[0]+1][head[1]].setHead();
        Integer[] newHead = {head[0]+1, head[1]};
        cells.addFirst(newHead);
      } else if(isValidCell(head[0]+1, head[1])&& world.board[head[0]+1][head[1]].isEmpty()) {
        Integer[] newHead = {head[0]+1, head[1]};
        cells.removeLast();
        Integer[] newLast = cells.get(cells.size()-1);
        cells.addFirst(newHead);
        world.board[newLast[0]][newLast[1]].setEmpty();
        world.board[head[0]][head[1]].setTail();
        world.board[head[0]+1][head[1]].setHead();
        world.board[last[0]][last[1]].setEmpty();
      } else {
        //GAME OVER
      }
    } else if(direction == Direction.L && direction != Direction.R) {
      Integer[] head = cells.getFirst();
      Integer[] last = cells.getLast();
      setDirectionLeft();
      if(isValidCell(head[0], head[1]-1)&& world.board[head[0]-1][head[1]].isFruit()){
        eat();
        world.spawnFruit();
        world.board[head[0]][head[1]].setTail();
        world.board[head[0]][head[1]-1].setHead();
        Integer[] newHead = {head[0], head[1]-1};
        cells.addFirst(newHead);
      } else if(isValidCell(head[0], head[1]-1)&& world.board[head[0]][head[1]-1].isEmpty()) {
        Integer[] newHead = {head[0], head[1]-1};
        cells.removeLast();
        Integer[] newLast = cells.get(cells.size()-1);
        cells.addFirst(newHead);
        world.board[newLast[0]][newLast[1]].setEmpty();
        world.board[head[0]][head[1]].setTail();
        world.board[head[0]][head[1]-1].setHead();
        world.board[last[0]][last[1]].setEmpty();
      } else {
        //GAME OVER
      }

    } else if(direction == Direction.R && direction != Direction.L){
      Integer[] head = cells.getFirst();
      Integer[] last = cells.getLast();
      setDirectionRight();
      if(isValidCell(head[0], head[1]+1)&& world.board[head[0]+1][head[1]].isFruit()){
        eat();
        world.spawnFruit();
        world.board[head[0]][head[1]].setTail();
        world.board[head[0]][head[1]+1].setHead();
        Integer[] newHead = {head[0], head[1]+1};
        cells.addFirst(newHead);
      } else if(isValidCell(head[0], head[1]+1)&& world.board[head[0]+1][head[1]].isEmpty()) {
        Integer[] newHead = {head[0], head[1]+1};
        cells.removeLast();
        Integer[] newLast = cells.get(cells.size()-1);
        cells.addFirst(newHead);
        world.board[newLast[0]][newLast[1]].setEmpty();
        world.board[head[0]][head[1]].setTail();
        world.board[head[0]][head[1]+1].setHead();
        world.board[last[0]][last[1]].setEmpty();
      } else {
        //GAME OVER
      }
    }
  }

  public boolean isValidCell(int height, int width){
    if(world.height > height && height >= 0 && (world.board[height][width].isEmpty() || world.board[height][width].isFruit())){
      return true;
    }
    else{
      return false;
    }
  }

  public void eat() {
    //SCORE +
  }

}
