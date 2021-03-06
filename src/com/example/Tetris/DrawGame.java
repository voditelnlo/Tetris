package com.example.Tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class DrawGame extends View
{
    public DrawGame(Context context)
    {
        super(context);
    }

    private float touchX = 0;
    private float touchY = 0;

    private GameState player = new GameState();

    private void GameDraw(Canvas canvas, GameState game, int offsetX, int offsetY, int delta) {
        Paint paint = new Paint();

        if (game.getGameOver()) {
            paint.setColor(Color.WHITE);
            paint.setTextSize(this.getWidth() / 11);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("GAME OVER", this.getWidth()/2, this.getHeight()/2 - 2*paint.getTextSize(), paint);
            canvas.drawText("Your score:", this.getWidth()/2, this.getHeight()/2, paint);
            canvas.drawText(String.valueOf(game.getScore()), this.getWidth()/2, this.getHeight()/2 + 2*paint.getTextSize(), paint);
        } else {
            paint.setColor(Color.WHITE);
            paint.setTextSize(((game.getNextFigure().getWidth() + 2) * delta) / 6);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("Score:", offsetX + (game.getMatrixWidth() + 1 + game.getNextFigure().getWidth()/2)*delta, offsetY + (game.getMatrixHeight()/2 + 2)*delta, paint);
            canvas.drawText(String.valueOf(game.getScore()), offsetX + (game.getMatrixWidth() + 1 + game.getNextFigure().getWidth() / 2) * delta, offsetY + (game.getMatrixHeight() / 2 + 2) * delta + paint.getTextSize(), paint);
            canvas.drawText("Speed:", offsetX + (game.getMatrixWidth() + 1 + game.getNextFigure().getWidth() / 2) * delta, offsetY + (game.getMatrixHeight() / 2 + 2) * delta + 2 * paint.getTextSize(), paint);
            canvas.drawText(String.valueOf(117 - game.getDelay()), offsetX + (game.getMatrixWidth() + 1 + game.getNextFigure().getWidth() / 2) * delta, offsetY + (game.getMatrixHeight() / 2 + 2) * delta + 3 * paint.getTextSize(), paint);

            paint.setColor(Color.GRAY);
            paint.setStyle(Paint.Style.STROKE);

            canvas.drawRect(offsetX + (game.getMatrixWidth() + 0.5F) * delta, offsetY + 0.5F * delta, offsetX + (game.getMatrixWidth() + 0.5F + game.getNextFigure().getWidth() + 1) * delta, offsetY + (0.5F + game.getNextFigure().getHeight() + 1) * delta, paint);
            canvas.drawRect(offsetX + (game.getMatrixWidth() + 0.5F) * delta, offsetY + (game.getMatrixHeight() / 2 + 0.5F) * delta, offsetX + (game.getMatrixWidth() + 0.5F + game.getNextFigure().getWidth() + 1) * delta, offsetY + (game.getMatrixHeight() / 2 + 0.5F + game.getFigure().getWidth() + 1) * delta, paint);

            for (int i = 0; i <= game.getMatrixWidth(); i++) {
                canvas.drawLine(offsetX + i * delta, offsetY, offsetX + i * delta, offsetY + game.getMatrixHeight() * delta, paint);
            }

            for (int i = 0; i <= game.getMatrixHeight(); i++) {
                canvas.drawLine(offsetX, offsetY + i * delta, offsetX + game.getMatrixWidth() * delta, offsetY + i * delta, paint);
            }

            for (int i = 0; i < game.getMatrixWidth(); i++) {
                for (int j = 0; j < game.getMatrixHeight(); j++) {
                    if (game.getMatrix()[i][j] != 0) {
                        paint.setColor(game.getMatrix()[i][j]);
                        paint.setStyle(Paint.Style.FILL);
                        canvas.drawRect(offsetX + i * delta, offsetY + j * delta, offsetX + (i + 1) * delta, offsetY + (j + 1) * delta, paint);

                        paint.setColor(Color.WHITE);
                        paint.setStyle(Paint.Style.STROKE);
                        canvas.drawRect(offsetX + i * delta, offsetY + j * delta, offsetX + (i + 1) * delta, offsetY + (j + 1) * delta, paint);
                    }
                }
            }

            for (int i = 0; i < game.getFigure().getWidth(); i++) {
                for (int j = 0; j < game.getFigure().getHeight(); j++) {
                    if (game.getFigure().getElements()[i][j] != 0) {
                        if (game.getFigure().getY() + j >= 0) {
                            paint.setColor(game.getFigure().getElements()[i][j]);
                            paint.setStyle(Paint.Style.FILL);
                            canvas.drawRect(offsetX + (i + game.getFigure().getX()) * delta, offsetY + (game.getFigure().getY() + j) * delta, offsetX + (i + 1 + game.getFigure().getX()) * delta, offsetY + (game.getFigure().getY() + j + 1) * delta, paint);

                            paint.setColor(Color.WHITE);
                            paint.setStyle(Paint.Style.STROKE);
                            canvas.drawRect(offsetX + (i + game.getFigure().getX()) * delta, offsetY + (game.getFigure().getY() + j) * delta, offsetX + (i + 1 + game.getFigure().getX()) * delta, offsetY + (game.getFigure().getY() + j + 1) * delta, paint);
                            paint.setColor(Color.GREEN);
                            canvas.drawLine(offsetX + (i + game.getFigure().getX()) * delta, offsetY + game.getMatrixHeight() * delta, offsetX + (i + 1 + game.getFigure().getX()) * delta, offsetY + game.getMatrixHeight() * delta, paint);
                        }
                    }
                }
            }

            for (int i = 0; i < game.getNextFigure().getWidth(); i++) {
                for (int j = 0; j < game.getNextFigure().getHeight(); j++) {
                    if (game.getNextFigure().getElements()[i][j] != 0) {
                        paint.setColor(game.getNextFigure().getElements()[i][j]);
                        paint.setStyle(Paint.Style.FILL);
                        canvas.drawRect(offsetX + (i + game.getMatrixWidth() + 1) * delta, offsetY + (1 + j) * delta, offsetX + (i + 1 + game.getMatrixWidth() + 1) * delta, offsetY + (1 + j + 1) * delta, paint);

                        paint.setColor(Color.WHITE);
                        paint.setStyle(Paint.Style.STROKE);
                        canvas.drawRect(offsetX + (i + game.getMatrixWidth() + 1) * delta, offsetY + (1 + j) * delta, offsetX + (i + 1 + game.getMatrixWidth() + 1) * delta, offsetY + (1 + j + 1) * delta, paint);

                    }
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        Paint paint = new Paint();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);

        int fieldWidth = player.getMatrixWidth() + 1 + player.getNextFigure().getWidth() + 1;
        int fieldHeight = player.getMatrixHeight() + 1;

        int delta = this.getWidth() / fieldWidth;

        if (delta*fieldHeight > this.getHeight())
        {
            delta = this.getHeight() / fieldHeight;
        }

        int offsetX = (this.getWidth() - delta*fieldWidth) / 2;
        int offsetY = (this.getHeight() - delta*fieldHeight) / 2;

        if (player.getGameOver() && player.getScore() == 0)
        {
            paint.setColor(Color.WHITE);
            paint.setTextSize(this.getWidth() / 11);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("T E T R I S", this.getWidth()/2, this.getHeight()/2, paint);
            canvas.drawText("Tap to start", this.getWidth()/2, this.getHeight()/2 + 2*paint.getTextSize(), paint);
        }
        else
        {
            GameDraw(canvas, player, offsetX, offsetY, delta);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if (player.getGameOver())
            {
                player = new GameState();
                player.GameStart();
            }
            else
            {
                touchX = event.getX();
                touchY = event.getY();

                if (touchY > this.getHeight() / 2)
                {
                    if (touchX > this.getWidth() / 2)
                    {
                        player.Rotate();
                    }
                    else
                    {
                        player.Fall();
                    }
                }
                else
                {
                    if (touchX > this.getWidth() / 2)
                    {
                        player.MoveRight();
                    }
                    else
                    {
                        player.MoveLeft();
                    }
                }

            }

            invalidate();
        }
        return true;
    }

    public void GameTick()
    {
        if (!player.getGameOver())
        {
            player.GameTick();
        }

        invalidate();
    }
}