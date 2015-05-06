package models;

import java.util.ArrayList;

public class World {
  private int width;
  private int height;
  private Snake snake; // necessary?
  private Fruit fruit;
  private ArrayList<Cell> cells;
  private Cell[][] board;

  public World(int width, int height, Snake snake, Fruit fruit) {
    this.width = width;
    this.height = height;
    this.snake = snake;
    this.fruit = fruit;
    this.cells = cells;
    board = fill(new Cell[height][width]);
  }

  // fill the world of cells
  private Cell[][] fill(Cell[][] board) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        board[i][j] = new Cell();
      }
    }
    return board;
  }
}
