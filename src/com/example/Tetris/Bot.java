package com.example.Tetris;

public class Bot
{
    private GameState game;

    private int weightTouch = 0;
    private int weightVoid = 0;
    private int minWeight = 0;

    BotChoice botChoice = new BotChoice();

    public Bot(GameState game)
    {
        this.weightTouch = 2;
        this.weightVoid = -3;
        this.minWeight = -100;
        this.game = new GameState(game);
        this.botChoice =  new BotChoice(Selection());
    }

    public BotChoice Selection()
    {
        BotChoice botChoice[] = new BotChoice[4*game.getMatrixWidth()];

        for (int botChoiceCount = 0; botChoiceCount < 4*game.getMatrixWidth(); botChoiceCount++)
        {
            for (int rotate = 0; rotate < 3; rotate++)
            {
                for (int moveX = 0; moveX < game.getMatrixWidth(); moveX++)
                {
                    botChoice[botChoiceCount].setX(moveX);
                    botChoice[botChoiceCount].setRotateCount(rotate);

                    Figure tmpFigure = new Figure(game.getFigure());

                    for (int rotateCount = 0; rotateCount < rotate; rotateCount++)
                    {
                        tmpFigure.Rotate();
                    }

                    tmpFigure.Move(moveX, tmpFigure.getY());

                    while (Validate(tmpFigure))
                    {
                        tmpFigure.Move(tmpFigure.getX(), tmpFigure.getY() + 1);
                    }

                    tmpFigure.Move(tmpFigure.getX(), tmpFigure.getY() - 1);

                    if (Validate(tmpFigure))
                    {
                        botChoice[botChoiceCount].addWeight(PaymentTouches(tmpFigure));

                        GameState tmpGame = new GameState(game);

                        tmpGame.getFigure().Move(tmpFigure.getX(), tmpFigure.getY());

                        tmpGame.GameTick();

                        for (int NextFigureRotate = 0; NextFigureRotate < 3; NextFigureRotate++)
                        {
                            for (int NextFigureMoveX = 0; NextFigureMoveX < tmpGame.getMatrixWidth(); NextFigureMoveX++)
                            {
                                botChoice[botChoiceCount].setNextFigureX(NextFigureMoveX);
                                botChoice[botChoiceCount].setNextFigureRotateCount(NextFigureRotate);

                                tmpFigure = new Figure(tmpGame.getFigure());//Figure tmpNextFigure = new Figure(tmpGame.getFigure());

                                for (int rotateCount = 0; rotateCount < rotate; rotateCount++)
                                {
                                    tmpFigure.Rotate();
                                }

                                tmpFigure.Move(NextFigureMoveX, tmpFigure.getY());

                                while (Validate(tmpFigure))
                                {
                                    tmpFigure.Move(tmpFigure.getX(), tmpFigure.getY() + 1);
                                }

                                tmpFigure.Move(tmpFigure.getX(), tmpFigure.getY() - 1);

                                if (Validate(tmpFigure))
                                {
                                    botChoice[botChoiceCount].addWeight(PaymentTouches(tmpFigure));
                                }
                                else
                                {
                                    botChoice[botChoiceCount].addWeight(minWeight);
                                }
                            }
                        }
                    }
                    else
                    {
                        botChoice[botChoiceCount].addWeight(minWeight);
                    }
                }
            }
        }

        int maxWeight = minWeight;
        int numberMaxWeight = 0;

        for (int i = 0; i < 4*game.getMatrixWidth(); i++)
        {
                if (botChoice[i].getWeight() > maxWeight)
                {
                    maxWeight = botChoice[i].getWeight();
                    numberMaxWeight = i;
                }
        }

        return botChoice[numberMaxWeight];
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

    private int PaymentTouches(Figure figure)
    {
        int returnWeight = 0;

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
}
