package main.models;

public class Player {
  private String name;
  private int score;

  public Player() {
    this.name = "";
    this.score = 0;
  }

  public void increaseScore() {
    this.score++;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getScore() {
    return score;
  }
}
