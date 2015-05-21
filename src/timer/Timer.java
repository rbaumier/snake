package timer;

import android.os.Handler;

public class Timer implements Runnable {
  public Handler handler;

  private int refreshTime;
  private boolean running = true;

  public Timer(int refreshTime) {
    this.refreshTime = refreshTime;
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
      handler.sendEmptyMessage(0); // send tick
    }
  }

}

