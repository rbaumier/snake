package models;

public class World {
  public int width;
  public int height;
  public Snake snake;
  public Cell[][] board;

  public World(int w, int h) {
    width = w;
    height = h;
    fillBoard();
  }

  public void fillBoard() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        board[i][j] = new Cell();
      }
    }
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
}
