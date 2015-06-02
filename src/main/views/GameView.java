package main.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import main.models.Cell;
import main.models.Game;

public class GameView extends View implements View.OnTouchListener {
  Game game;
  Paint paint;

  public GameView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.setOnTouchListener(this);
  }

  // to remove
  public void init(Game w) {
    game = w;
  }

  @Override
  public void onDraw(Canvas canvas) {
    makePaint();
    drawCells(canvas);
  }

  private void makePaint() {
    paint = new Paint();
    paint.setStyle(Paint.Style.FILL);
    paint.setColor(Color.LTGRAY);
  }

  private void drawCells(Canvas canvas) {
    int offsetX = this.getWidth() / game.width;
    int offsetY = this.getHeight() / game.height;
    int cellWidth = getDim(offsetX);
    int cellHeight = getDim(offsetY);
    for (int i = 0; i < game.height; i++) {
      for (int j = 0; j < game.width; j++) {
        paint.setColor(getCellColor(i, j));
        canvas.drawRect(
          drawCell(getPos(j, offsetX), getPos(i, offsetY), cellWidth, cellHeight),
          paint
        );
      }
    }
  }

  private int getCellColor(int x, int y) {
    Cell cell = game.getCell(x, y);
    if (cell.isEmpty()) {
      return color("#CFD8DC");
    } else if (cell.isFruit()) {
      return color("#F44336");
    } else if (cell.isHead()) {
      return color("#1B5E20");
    } else { // is tail or last
      return color("#4CAF50");
    }
  }

  public int color(String hex) {
    return Color.parseColor(hex);
  }

  @Override
  public boolean onTouch(View v, MotionEvent e) {
    if (e.getAction() == MotionEvent.ACTION_DOWN) {
      // TODO: add controller to handle this, the view shouldn't have a direct relationship with the world
      game.rotateSnake();
      this.invalidate();
    }
    return true;
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
