package main.models;

import java.util.LinkedList;

public class World implements SnakeControl, GamePlayer {
  public int width;
  public int height;
  private Snake snake;

  private Cell[][] board;
  private Player player;
  private boolean gameOver;
  private char[] playerScore;

  public World(int width, int height) {
    this.width = width;
    this.height = height;
    board = fillBoard(new Cell[height][width]);
    player = new Player();
    snake = new Snake(Direction.L);
    spawnSnake();
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
    int x = random(width);
    int y = random(height);
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

    cleanAllCells();

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

  private void cleanAllCells() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        board[i][j].setEmpty();
      }
    }
  }

  private int random(int max) {
    return (int) (Math.random() * max);
  }

  @Override
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

  @Override
  public void makeSnakeEat() {

  }

  @Override
  public void rotateSnake() {
    if (snake.isGoingUp()) {
      moveSnake(Direction.R);
    } else if (snake.isGoingRight()) {
      moveSnake(Direction.D);
    } else if (snake.isGoingDown()) {
      moveSnake(Direction.L);
    } else {
      moveSnake(Direction.U);
    }
  }

  public boolean isNotBusyCell(int x, int y) {
    return height > y && y >= 0 &&
      width > x && x >= 0 &&
      board[y][x].isEmptyOrFruit();
  }

  public Cell getRandomCell() {
    return board[random(height)][random(width)];
  }

  @Override
  public void increaseScores() {

  }

  // Coming at you soon...
  @Override
  public void moveSnake(Direction direction) {
    if (direction == Direction.U && direction != Direction.D) {
      Integer[] dest = snake.getHead();
      snake.goUp();
      if (isNotBusyCell(dest[1], dest[0] - 1)) {
        Integer[] newHead = {dest[0] - 1, dest[1]};
        if (board[dest[0] - 1][dest[1]].isFruit()) {
          makeSnakeEat();
          snake.addNewHead(newHead);
        } else if (board[dest[0] - 1][dest[1]].isEmpty()) {
          snake.addNewHead(newHead);
          snake.removeLast();
        }
        refreshWorldState();
      } else {
        gameOver = true;
      }
    } else if (direction == Direction.D && direction != Direction.U) {
      Integer[] dest = snake.getHead();
      snake.goDown();
      if (isNotBusyCell(dest[1], dest[0] + 1)) {
        Integer[] newHead = {dest[0] + 1, dest[1]};
        if (board[dest[0] + 1][dest[1]].isFruit()) {
          makeSnakeEat();
          snake.addNewHead(newHead);
        } else if (board[dest[0] + 1][dest[1]].isEmpty()) {
          snake.addNewHead(newHead);
          snake.removeLast();
        }
        refreshWorldState();
      } else {
        gameOver = true;
      }
    } else if (direction == Direction.L && direction != Direction.R) {
      Integer[] dest = snake.getHead();
      snake.goLeft();
      if (isNotBusyCell(dest[1] - 1, dest[0])) {
        Integer[] newHead = {dest[0], dest[1] - 1};
        if (board[dest[0]][dest[1] - 1].isFruit()) {
          makeSnakeEat();
          snake.addNewHead(newHead);
        } else if (board[dest[0]][dest[1] - 1].isEmpty()) {
          snake.addNewHead(newHead);
          snake.removeLast();
        }
        refreshWorldState();
      } else {
        gameOver = true;
      }
    } else if (direction == Direction.R && direction != Direction.L) {
      Integer[] dest = snake.getHead();
      snake.goRight();
      if (isNotBusyCell(dest[1] + 1, dest[0])) {
        Integer[] newHead = {dest[0], dest[1] + 1};
        if (board[dest[0]][dest[1] + 1].isFruit()) {
          makeSnakeEat();
          snake.addNewHead(newHead);
        } else if (board[dest[0]][dest[1] + 1].isEmpty()) {
          snake.addNewHead(newHead);
          snake.removeLast();
        }
        refreshWorldState();
      } else {
        gameOver = true;
      }
    }
  }

  public boolean isGameOver() {
    return gameOver;
  }

  public void gameOver() { gameOver = true; }

  public void moveSnakeSameDirection() {
    moveSnake(snake.getCurrentDirection());
  }

  public String getPlayerName() {
    return player.getName();
  }

  public void setPlayerName(String name) {
    player.setName(name);
  }

  public Player getPlayer() {
    return player;
  }

  public int getPlayerScore() {
    return player.getScore();
  }

  public Cell getCell(int x, int y) {
    return board[x][y];
  }
}

