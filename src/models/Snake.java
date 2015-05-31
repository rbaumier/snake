package models;

import java.util.ArrayList;
import java.util.LinkedList;

public class Snake {
  private final World world;
  private Direction direction;
  private LinkedList<Integer[]> snake;

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

    world.board[start][height].setHead();
    world.board[start + 1][height].setTail();
    world.board[start + 2][height].setLast();
    Integer head[] = {height, start};
    Integer tail[] = {height, start +1};
    Integer last[] = {height, start +2};
    snake.add(head);
    snake.add(tail);
    snake.add(last);
  }

  public void move(Direction direction){
    Integer[] head = snake.getFirst();
    Integer[] last = snake.getLast();
    if(direction == Direction.U && direction != Direction.D){
      setDirectionUp();
      if(isValidCell(head[0]-1, head[1])&& world.board[head[0]-1][head[1]].isFruit()){
        eat();
        world.board[head[0]][head[1]].setTail();
        world.board[head[0]-1][head[1]].setHead();
        Integer[] newHead = {head[0]-1, head[1]};
        snake.addFirst(newHead);
      } else if(isValidCell(head[0]-1, head[1])&& world.board[head[0]-1][head[1]].isEmpty()) {
        world.board[head[0]][head[1]].setTail();
        world.board[head[0]-1][head[1]].setHead();
        world.board[last[0]][last[1]].setEmpty();
        Integer[] newHead = {head[0]-1, head[1]};
        Integer[] newLast = snake.get(snake.size()-1);
        world.board[newLast[0]][newLast[1]].setLast();
        snake.addFirst(newHead);
        snake.removeLast();
      } else {
        //GAME OVER
      }
    } else if(direction == Direction.D && direction != Direction.U) {
      setDirectionDown();
      if(isValidCell(head[0]+1, head[1])&& world.board[head[0]+1][head[1]].isFruit()){
        eat();
        world.board[head[0]][head[1]].setTail();
        world.board[head[0]+1][head[1]].setHead();
        Integer[] newHead = {head[0]+1, head[1]};
        snake.addFirst(newHead);
      } else if(isValidCell(head[0]+1, head[1])&& world.board[head[0]+1][head[1]].isEmpty()) {
        world.board[head[0]][head[1]].setTail();
        world.board[head[0]+1][head[1]].setHead();
        world.board[last[0]][last[1]].setEmpty();
        Integer[] newHead = {head[0]+1, head[1]};
        Integer[] newLast = snake.get(snake.size()-1);
        world.board[newLast[0]][newLast[1]].setLast();
        snake.addFirst(newHead);
        snake.removeLast();
      } else {
        //GAME OVER
      }
    } else if(direction == Direction.L && direction != Direction.R) {
      setDirectionLeft();
      if(isValidCell(head[0], head[1]-1)&& world.board[head[0]-1][head[1]].isFruit()){
        eat();
        world.board[head[0]][head[1]].setTail();
        world.board[head[0]][head[1]-1].setHead();
        Integer[] newHead = {head[0], head[1]-1};
        snake.addFirst(newHead);
      } else if(isValidCell(head[0], head[1]-1)&& world.board[head[0]][head[1]-1].isEmpty()) {
        world.board[head[0]][head[1]].setTail();
        world.board[head[0]][head[1]-1].setHead();
        world.board[last[0]][last[1]].setEmpty();
        Integer[] newHead = {head[0], head[1]-1};
        Integer[] newLast = snake.get(snake.size()-1);
        world.board[newLast[0]][newLast[1]].setLast();
        snake.addFirst(newHead);
        snake.removeLast();
      } else {
        //GAME OVER
      }

    } else if(direction == Direction.R && direction != Direction.L){
      setDirectionRight();
      if(isValidCell(head[0], head[1]+1)&& world.board[head[0]+1][head[1]].isFruit()){
        eat();
        world.board[head[0]][head[1]].setTail();
        world.board[head[0]][head[1]+1].setHead();
        Integer[] newHead = {head[0], head[1]+1};
        snake.addFirst(newHead);
      } else if(isValidCell(head[0], head[1]+1)&& world.board[head[0]+1][head[1]].isEmpty()) {
        world.board[head[0]][head[1]].setTail();
        world.board[head[0]+1][head[1]].setHead();
        world.board[last[0]][last[1]].setEmpty();
        Integer[] newHead = {head[0], head[1]+1};
        Integer[] newLast = snake.get(snake.size()-1);
        world.board[newLast[0]][newLast[1]].setLast();
        snake.addFirst(newHead);
        snake.removeLast();
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
