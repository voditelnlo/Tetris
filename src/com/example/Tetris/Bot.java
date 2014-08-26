package com.example.Tetris;

public class Bot
{
    private GameState game;

    public void Bot(GameState game)
    {
        this.game = new GameState(game);
    }

    private BotChoice Selection()
    {
        BotChoice botChoice[] = new BotChoice[4*game.getMatrixWidth()];

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < game.getMatrixWidth() - 1; j++)
            {
                botChoice[i*4+j].setX(j);
                botChoice[i*4+j].setRotateCount(i);

                botChoice[i*4+j].addWeight(-100);
            }
        }
// смена фигуры
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < game.getMatrixWidth() - 1; j++)
            {
                botChoice[i*4+j].setNextFigureX(j);
                botChoice[i*4+j].setNextFigureRotateCount(i);

                botChoice[i*4+j].addWeight(-100);
            }
        }

        int maxWeight = -100;
        int numberMaxWeight = 0;

        for (int i = 0; i < 4*game.getMatrixWidth() - 1; i++)
        {
                if (botChoice[i].getWeight() > maxWeight)
                {
                    numberMaxWeight = i;
                }
        }

        return botChoice[numberMaxWeight];
    }

    private int Step()
    {
        Figure tmpFigure = new Figure(game.getFigure());

        tmpFigure.Move(tmpFigure.getX(), tmpFigure.getY() + 1);

        if (StepValidate(tmpFigure))
        {
            game.getFigure().Move(game.getFigure().getX(), game.getFigure().getY() + 1);
            return -1;
        }
        else
        {
            int retJoin = Join(game.getFigure());

            if (retJoin < 0)
            {
                //game Over
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
                    if (figure.getX() + i < 0 || figure.getX() + i >= game.getMatrixWidth() || figure.getY() + j >= game.getMatrixHeight())
                    {
                        return false;

                    }
                    else if(figure.getY() + j >= 0)
                    {
                        if (game.getMatrix()[figure.getX() + i][figure.getY() + j] != 0)
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
                    if (figure.getY() + j >= game.getMatrixHeight())
                    {
                        return false;
                    }
                    else if(figure.getY() + j >= 0)
                    {
                        if (game.getMatrix()[figure.getX() + i][figure.getY() + j] != 0)
                        {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    private int PaymentTouches(Figure figure)
    {
        int returnWeight = 0;

        int weightTouch = 2;
        int weightVoid = -3;

        for (int i = 0; i < figure.getWidth(); i++)
        {
            for (int j = 0; j < figure.getHeight(); j++)
            {
                if (figure.getElements()[i][j] != 0)
                {
                    if (figure.getY() >= 0)
                    {
                        if (figure.getElements()[i][j] > 0)
                        {
                            //есть ли клетка или граница внизу
                            if (game.getMatrix()[figure.getX() + i][figure.getY() + j + 1] > 0 || figure.getY() + j + 1 == game.getMatrixHeight())
                            {
                                returnWeight += weightTouch;
                            }
                            else
                            {
                                returnWeight += weightVoid;
                            }

                            //есть ли клетка или граница справа
                            if (game.getMatrix()[figure.getX() + i + 1][figure.getY() + j] > 0 || figure.getX() + i + 1 == game.getMatrixWidth())
                            {
                                returnWeight += weightTouch;
                            }

                            //есть ли клетка или граница слева
                            if (game.getMatrix()[figure.getX() + i - 1][figure.getY() + j + 1] > 0 || figure.getX() + j - 1 == 0)
                            {
                                returnWeight += weightTouch;
                            }
                        }
                    }
                    else
                    {
                        return -1;//исправить с прицелом на расчет касаний
                    }
                }
            }
        }

        // вклад высоты над дном стакана верхней точки фигуры
        for (int i = 0; i < figure.getWidth(); i++)
        {
            for (int j = 0; j < figure.getHeight(); j++)
            {
                if (figure.getElements()[i][j] > 0)
                {
                    returnWeight += game.getMatrixHeight() - figure.getY();

                    i = figure.getWidth();
                    j = figure.getHeight();
                }
            }
        }
        // вклад высоты над дном стакана нижней точки фигуры

        for (int i = 0; i < figure.getWidth(); i++)
        {
            for (int j = figure.getHeight() - 1; j >= 0; j--)
            {
                if (figure.getElements()[i][j] > 0)
                {
                    returnWeight += game.getMatrixHeight() - figure.getY();

                    i = figure.getWidth();
                    j = -1;
                }

            }
        }

        return returnWeight;
    }

    private int AssessmentOfFigures()
    {


        return 0;
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
