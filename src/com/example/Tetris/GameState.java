package com.example.Tetris;

/**
 * Created by nnn on 20.08.2014.
 */
public class GameState
{
    private int matrixWidth = 0;
    private int matrixHeight = 0;

    private int delayCounter = 0;
    private int delay = 0;

    private int score = 0;

    private int matrix[][];

    private boolean gameOver = true;

    private Figure figure = new Figure();
    private Figure nextFigure = new Figure();

    public GameState()
    {
        gameOver = true;
        delayCounter = 0;
        score = 0;

        matrixWidth = 10;
        matrixHeight = 20;
        matrix = new int[matrixWidth][matrixHeight];        
        figure.Move(matrixWidth/2 - figure.getWidth()/2, -1);
        nextFigure.Move(matrixWidth/2 - figure.getWidth()/2, -1);
        delay = (int)Math.pow(10, 35380.0 / (this.score + 17290) + 0.025);
    }

    public GameState(GameState game)
    {
        gameOver = true;
        delayCounter = 0;
        score = game.getScore();

        matrixWidth = game.getMatrixWidth();
        matrixHeight = game.getMatrixHeight();
        matrix = new int[matrixWidth][matrixHeight];

        figure = new Figure(game.getFigure());
        nextFigure = new Figure(game.getNextFigure());

        for(int i = 0; i < matrixWidth - 1; i++)
        {
            for (int j = 0; j < matrixHeight - 1; j++)
            {
                matrix[i][j] = game.getMatrix()[i][j];
            }
        }

        delay = game.getDelay();
    }

    public void GameStart()
    {
        gameOver = false;
    }

    public int getScore()
    {
        return score;
    }

    public boolean getGameOver()
    {
        return gameOver;
    }

    public int[][] getMatrix()
{
    return matrix;
}

    public Figure getNextFigure()
    {
        return nextFigure;
    }

    public int getMatrixWidth()
    {
        return matrixWidth;
    }

    public int getMatrixHeight()
    {
        return matrixHeight;
    }

    public Figure getFigure()
    {
        return figure;
    }

    public int getDelay()
    {
        return delay;
    }

    public void Rotate()
    {
        Figure tmpFigure = new Figure(figure);

        tmpFigure.Rotate();

        if (Validate(tmpFigure))
        {
            figure.Rotate();
        }
    }

    private int Step()
    {
        Figure tmpFigure = new Figure(figure);

        tmpFigure.Move(tmpFigure.getX(), tmpFigure.getY() + 1);

        if (StepValidate(tmpFigure))
        {
            figure.Move(figure.getX(), figure.getY() + 1);
            return -1;
        }
        else
        {
            int retJoin = Join(figure);

            if (retJoin < 0)
            {
                gameOver = true;
            }

            return 0;
        }
    }

    public void Fall()
    {
        int score = 0;
        int retStep = 0;

        do {
            retStep = Step();

            if (retStep == -1)
            {
                score++;
            }
            else
            {
                score += retStep;
            }

        }while(retStep == -1);

        AddScore(score);
    }

    public void MoveRight()
    {
        Figure tmpFigure = new Figure(figure);

        tmpFigure.Move(figure.getX() + 1, figure.getY());

        if (Validate(tmpFigure))
        {
            figure.Move(figure.getX() + 1, figure.getY());
        }
    }

    public void MoveLeft()
    {
        Figure tmpFigure = new Figure(figure);

        tmpFigure.Move(figure.getX() - 1, figure.getY());

        if (Validate(tmpFigure))
        {
            figure.Move(figure.getX() - 1, figure.getY());
        }
    }

    private boolean Validate(Figure figure)
    {
        for (int i = 0; i < figure.getWidth(); i++)
        {
            for (int j = 0; j < figure.getHeight(); j++)
            {
                if (figure.getElements()[i][j] != 0)
                {
                    if (figure.getX() + i < 0 || figure.getX() + i >= matrixWidth || figure.getY() + j >= matrixHeight)
                    {
                        return false;

                    }
                    else if(figure.getY() + j >= 0)
                    {
                        if (matrix[figure.getX() + i][figure.getY() + j] != 0)
                        {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    private boolean StepValidate(Figure figure)
    {
        for (int i = 0; i < figure.getWidth(); i++)
        {
            for (int j = 0; j < figure.getHeight(); j++)
            {
                if (figure.getElements()[i][j] != 0)
                {
                    if (figure.getY() + j >= matrixHeight)
                    {
                        return false;
                    }
                    else if(figure.getY() + j >= 0)
                    {
                        if (matrix[figure.getX() + i][figure.getY() + j] != 0)
                        {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    private int Join(Figure figure)
    {
        int lineDown = 0;

        for (int i = 0; i < figure.getWidth(); i++)
        {
            for (int j = 0; j < figure.getHeight(); j++)
            {
                if (figure.getElements()[i][j] != 0)
                {
                    if (figure.getY() >= 0)
                    {
                        matrix[figure.getX() + i][figure.getY() + j] = figure.getElements()[i][j];
                    }
                    else
                    {
                        return -1;
                    }
                }
            }
        }

        for (int i = matrixHeight - 1; i >= 0; i--)
        {
            int count = 0;

            for (int j = 0; j < matrixWidth; j++)
            {
                if (matrix[j][i] != 0)
                {
                    count++;
                }
            }

            if (count == matrixWidth)
            {
                lineDown++;

                for (int ii = matrixHeight - 1; ii > 0; ii--)
                {
                    for (int jj = 0; jj < matrixWidth; jj++)
                    {
                        if (i >= ii)
                        {
                            matrix[jj][ii] = matrix[jj][ii-1];
                        }
                        else
                        {
                            jj = matrixWidth;
                        }
                    }
                }

                i++;
            }
        }

        this.figure = new Figure(nextFigure);

        nextFigure.NewFigure();
        nextFigure.Move(3,-1);

        return 100*lineDown;
    }

    public void GameTick()
    {
        delayCounter++;

        if (delayCounter >= delay)
        {
            delayCounter = 0;
            Step();
        }
    }

    private void AddScore(int score)
    {
        this.score += score;
        delay = (int)Math.pow(10, 35380.0 / (this.score + 17290) + 0.025);
    }
}
