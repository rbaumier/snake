package models;

import java.util.LinkedList;

public class World {
  public int width;
  public int height;
  public Snake snake;
  public Cell[][] board;
  public Player player;

  public World(int w, int h) {
    width = w;
    height = h;
    board = fillBoard(new Cell[height][width]);
    player = new Player();
  }

  private Cell[][] fillBoard(Cell[][] b) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        b[i][j] = new Cell();
      }
    }
    return b;
  }

  // for console version
  public void displayWorld() {
    for (int x = 0; x < width; x++) {
      String line = "";
      for (int y = 0; y < height; y++) {
        if (board[x][y].isFruit()) {
          line += " " + "::" + " ";
        } else if (board[x][y].isHead()) {
          line += " " + "C" + " ";
        } else if (board[x][y].isTail()) {
          line += " " + "O" + " ";
        } else if (board[x][y].isEmpty()) {
          line += " " + " " + " ";
        } else {
          line += " " + "||" + " ";
        }
      }
      System.out.println("line " + line);
    }
  }

  public void spawnFruit() {
    int randomHeight = (int) (Math.random() * height);
    int randomWidth = (int) (Math.random() * width);
    if (board[randomHeight][randomWidth].isEmpty())
      board[randomHeight][randomWidth].setFruit();
    else
      spawnFruit();
  }

  public Integer[] getFruit(){
    Integer y = null;
    Integer x = null;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if(board[i][j].isFruit())
          y = i;
          x = j;
      }
    }
    Integer fruit[] = {y,x};
    return fruit;
  }

  public void refreshWorldState() {
    Integer[] fruit = getFruit();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        board[i][j].setEmpty();
      }
    }

      //System.out.println(fruit[0] + " " + fruit[1]);
      board[fruit[0]][fruit[1]].setFruit();

      LinkedList<Integer[]> serpent = snake.getCells();
      for(int k=0; k < serpent.size(); k++){
        if(k==0){
          Integer[] current = serpent.get(k);
          board[current[0]][current[1]].setHead();
        }
        else {
          Integer[] current = serpent.get(k);
          board[current[0]][current[1]].setTail();
        }
      }
  }

  public void setSnake(Snake snake) {
    this.snake = snake;
  }
}
