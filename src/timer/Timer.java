package timer;

import android.os.Handler;
import controllers.Controller;
import models.World;

public class Timer implements Runnable {
  public Handler handler;

  private int refreshTime;
  private boolean running = true;
  private Controller controller;

  public Timer(int refreshTime, Controller controller) {
    this.refreshTime = refreshTime;
    this.controller = controller;
  }

  public void stop() {
    running = false;
  }

  @Override
  public void run() {
    while (running) {
      controller.update();
      try {
        Thread.sleep(refreshTime);
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
      handler.sendEmptyMessage(0); // send tick
    }
  }

}

