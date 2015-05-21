package models;

import java.util.ArrayList;

public class World {
  public int width;
  public int height;
  public Snake snake; // necessary?
  public ArrayList<Cell> cells;
  public Cell[][] board;

  public World(int width, int height, Snake snake) {
    this.width = width;
    this.height = height;
    this.snake = snake;
    this.board = fill(new Cell[height][width]);
  }

  // fill the board of cells
  public Cell[][] fill(Cell[][] board) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        board[i][j] = new Cell();
      }
    }
    return board;
  }
}
