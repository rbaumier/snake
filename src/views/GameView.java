package views;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View implements View.OnTouchListener {
  public GameView(Context context) {
    super(context);

  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
  }

  @Override
  public boolean onTouch(View view, MotionEvent motionEvent) {
    return false;
  }
}
