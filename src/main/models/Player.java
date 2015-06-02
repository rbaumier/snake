package main.models;

public class Player {
  private String name;
  private int score;
  private GamePlayer gamePlayer;

  public Player() {
    this.name = "";
    this.score = 0;
  }

  public void increaseScore(int amount) {
    this.score += amount;
  }
}
