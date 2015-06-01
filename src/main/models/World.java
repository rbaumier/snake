package main.models;

import java.util.LinkedList;

public class World {
  private int width;
  private int height;
  private Snake snake;
  private Cell[][] board;
  private Player player;

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

  public void spawnFruit() {
    Cell randomCell = getRandomCell();
    if (randomCell.isEmpty()) randomCell.setFruit();
    else spawnFruit();
  }

  public Integer[] getFruit() {
    int x = (int) (Math.random() * width);
    int y = (int) (Math.random() * height);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (board[i][j].isFruit()) {
          y = i;
          x = j;
        }
      }
    }
    Integer fruit[] = {y, x};
    return fruit;
  }

  public void refreshWorldState() {
    Integer[] fruit = getFruit();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        board[i][j].setEmpty();
      }
    }

    board[fruit[0]][fruit[1]].setFruit();
    LinkedList<Integer[]> serpent = snake.getCells();
    for (int k = 0; k < serpent.size(); k++) {
      if (k == 0) {
        Integer[] current = serpent.get(k);
        board[current[0]][current[1]].setHead();
      } else {
        Integer[] current = serpent.get(k);
        board[current[0]][current[1]].setTail();
      }
    }
  }

  private int random(int max) {
    return (int) (Math.random() * max);
  }

  public void spawnSnake() {
    int x = height / 2;
    int y = width / 2;

    board[x][y].setHead();
    board[x + 1][y].setTail();
    board[x + 2][y].setLast();

    snake.addToBody(makeCoords(x, y)); // head
    snake.addToBody(makeCoords(x, y + 1)); // tail
    snake.addToBody(makeCoords(x, y + 2)); // last
  }

  public Integer[] makeCoords(int x, int y) {
    return new Integer[]{x, y};
  }

  public boolean isNotBusyCell(int x, int y) {
    return height > y && y >= 0 &&
      width > x && x >= 0 &&
      board[y][x].isEmptyOrFruit();
  }

  public Cell getRandomCell() {
    return board[random(height)][random(width)];
  }
}
