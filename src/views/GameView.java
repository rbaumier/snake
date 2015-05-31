package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import models.World;

public class GameView extends View implements View.OnTouchListener {
  World world;
  Paint paint;

  public GameView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.setOnTouchListener(this);
  }

  public void initWorld(World w) {
    world = w;
  }

  @Override
  public void onDraw(Canvas canvas) {
    System.out.println("invalidated");
    makePaint();
    drawCells(canvas);
  }

  private void makePaint() {
    paint = new Paint();
    paint.setStyle(Paint.Style.FILL);
    paint.setColor(Color.LTGRAY);
  }

  private void drawCells(Canvas canvas) {
    int offsetX = this.getWidth() / world.width;
    int offsetY = this.getHeight() / world.height;
    int cellWidth = getDim(offsetX);
    int cellHeight = getDim(offsetY);
    for (int i = 0; i < world.height; i++) {
      for (int j = 0; j < world.width; j++) {
        canvas.drawRect(
          drawCell(getPos(j, offsetX), getPos(i, offsetY), cellWidth, cellHeight),
          paint
        );
      }
    }
  }

  @Override
  public boolean onTouch(View view, MotionEvent motionEvent) {
    return false;
  }

  public Rect drawCell(int posX, int posY, int width, int height) {
    return new Rect(posX, posY, posX + width, posY + height);
  }

  public int getDim(int offset) {
    return offset - offset / 8;
  }

  private int getPos(int start, int offset) {
    return start * offset + offset / 8;
  }
}
