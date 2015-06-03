package main.timer;

import android.os.Handler;

public class Timer implements Runnable {
  private Handler handler;
  private int refreshTime;
  private boolean running = true;

  public Timer(Handler handler, int refreshTime) {
    this.handler = handler;
    this.refreshTime = refreshTime;
  }

  public void start() {
    running = true;
  }

  public void stop() {
    running = false;
  }

  @Override
  public void run() {
    while (running) {
      try {
        Thread.sleep(refreshTime);
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
      handler.sendEmptyMessage(0);
    }
  }
}

