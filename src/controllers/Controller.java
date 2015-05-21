package controllers;

import models.World;

// This class is needed for Console class. Its role will be handled by the activity later
public class Controller {
  private World world;

  public Controller(World world) {
    this.world = world;
  }

  public void update() {
    this.world.displayWorld();
  }
}
