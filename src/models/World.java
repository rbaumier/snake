package models;

import java.util.ArrayList;

public class World {
  public int width;
  public int height;
  public Snake snake;
  public Fruit fruit;
  public Cell[][] board;

  public World(int width, int height, Snake snake, Fruit fruit) {
    this.width = width;
    this.height = height;
    this.snake = snake;
    this.fruit = fruit;
    this.board = fill(new Cell[height][width]);
  }

  // fill the board of cells
  public Cell[][] fill(Cell[][] board) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        board[i][j]=new Cell();
      }
    }
    return board;
  }

    //display world for console version
    public void displayWorld() {
        for (int x = 0; x < width; x++) {
            String line = "";
            for (int y = 0; y < height; y++) {
                if (this.board[x][y].state == Cell.State.fruit) {
                    line += " " + "::" + " ";
                }
                else if(this.board[x][y].state == Cell.State.head){
                    line += " " + "C" + " ";
                }
                else if(this.board[x][y].state == Cell.State.tail){
                    line += " " + "O" + " ";
                }
                else if(this.board[x][y].state == Cell.State.empty){
                    line += " " + " " + " ";
                }
                else{
                    line += " " + "||" + " ";
                }

            }
            System.out.println(line);
        }
    }
}
