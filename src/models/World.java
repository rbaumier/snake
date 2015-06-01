package models;

import java.util.ArrayList;

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
    if (board[randomWidth][randomHeight].isEmpty())
      board[randomWidth][randomHeight].setFruit();
    else
      spawnFruit();
  }

  public void setSnake(Snake snake) {
    this.snake = snake;
  }
}
