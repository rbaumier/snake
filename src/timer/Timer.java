package timer;

import android.os.Handler;

public class Timer implements Runnable {
  public Handler handler;
  private boolean running = true;

  public Timer() {
  }

  public void stop() {
    running = false;
  }

  @Override
  public void run() {
    while (running) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
      handler.sendEmptyMessage(0); // send tick
    }
  }

}

