package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View implements View.OnTouchListener {
  public GameView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.setOnTouchListener(this);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    Paint paint = new Paint();
    // fill
    paint.setStyle(Paint.Style.FILL);
    paint.setColor(Color.MAGENTA);

    canvas.drawCircle(150, 150, 2, paint);
  }

  @Override
  public boolean onTouch(View view, MotionEvent motionEvent) {
    return false;
  }


}
