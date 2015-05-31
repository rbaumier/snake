package models;

public class Player {
  public String name;
  public int score;

  public Player() {
    this.name = "";
    this.score = 0;
  }

  public void increaseScore(int amount) {
    this.score += amount;
  }
}
