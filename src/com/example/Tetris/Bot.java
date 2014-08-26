package com.example.Tetris;

public class Bot
{
    private GameState game;

    public void Bot(GameState game)
    {
        this.game = new GameState(game);
    }

    private void AssessmentOfOptions()
    {

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
                        //game.getMatrix()[figure.getX() + i][figure.getY() + j] = figure.getElements()[i][j];

                        //расчет касаний и закинуть фигуру на это место для расчета следующей
                    }
                    else
                    {
                        return -1;
                    }
                }
            }
        }

        //получить очки
/*
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
    */

        return 100*lineDown;
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


    private void qwe()
    {



        int SH_ST = game.getMatrixWidth();
        int SH_FG = game.getFigure().getWidth();

        int VS_ST = game.getMatrixHeight();

        int shmass = SH_ST + SH_FG;

        int[][] priyatnost = new int[4][shmass];

        int[][]st = game.getMatrix().clone();

        game.get

        Figure fg = ;

        int prKas = 2;
        int prLin = 5;
        int prMin = -100;
        int prPus = -3;

        int niz = 0;

        for (int i = 0; i < SH_ST; i++) {
            for (int j = 0; j < VS_ST; j++) {
                if (st[i][j]!=0)
                {
                    if (niz < j) niz = j;

                    j = VS_ST;
                }
                else
                {
                    if (j == VS_ST - 1) {
                        niz = j;
                        i = SH_ST;
                    }
                }
            }
        }

        for (int n = 0; n < 4; n++) {
            for (int m = 0; m < shmass; m++) {
                game.
                        game.getFigureX() = m - SH_FG;
                fg.polozhenie.Y = fgr.polozhenie.Y;

                if (Stolknovenie(fg, st, new Point(0, 0)) == 0) {
                    while (Stolknovenie(fg, st, new Point(0, 1)) != 2) {
                        fg.polozhenie.Y++;
                    }

                    int verh = 0;

                    for (int i = 0; i < SH_FG; i++) {
                        for (int j = 0; j < VS_FG; j++) {
                            if (fg.figura[i,j]!=0)
                            {
                                if (verh > j) verh = j;
                                j = VS_FG;
                            }

                        }
                    }

                    priyatnost[n, m]+=(fg.polozhenie.Y - niz);

                    for (int y = 0; y < SH_FG; y++) {
                        for (int x = 0; x < VS_FG; x++) {
                            if (fg.figura[x,y]!=0)
                            {
                                int polX = fg.polozhenie.X + x;
                                int polY = fg.polozhenie.Y + y;

                                if (polY == VS_ST - 1) {
                                    priyatnost[n, m]+=prKas;
                                } else {
                                    if (st[polX,polY + 1]!=0)
                                    {
                                        priyatnost[n, m]+=prKas;//касание клетки ниже
                                    }
                                    else
                                    {
                                        if (y < VS_FG - 1) if (fg.figura[x,y + 1]==0)priyatnost[n, m]+=
                                        prPus;//внизу пусто
                                    }
                                }

                                if (polX == 0) {
                                    priyatnost[n, m]+=prKas;
                                } else {
                                    if (st[polX - 1,polY]!=0)priyatnost[n, m]+=prKas;
                                }

                                if (polX == SH_ST - 1) {
                                    priyatnost[n, m]+=prKas;
                                } else {
                                    if (st[polX + 1,polY]!=0)priyatnost[n, m]+=prKas;
                                }

                                int tmp = 0;

                                for (int i = 0; i < SH_ST; i++) {
                                    if (st[i,polY]!=0)
                                    {
                                        tmp++;
                                    }
                                    else
                                    {
                                        if (i >= fg.polozhenie.X && i < fg.polozhenie.X + SH_FG) {
                                            if (fg.figura[i - fg.polozhenie.X,polY - fg.polozhenie.Y]!=0)
                                            {
                                                tmp++;
                                            }
                                        }
                                    }
                                }

                                if (tmp == SH_ST) priyatnost[n, m]+=prLin;
                            }
                        }
                    }
                } else {
                    priyatnost[n, m]=prMin;
                }
            }

            fg = PovorotFigury(fg);

            fg.polozhenie.Y = fgr.polozhenie.Y;
        }

        fg = fgr;

        int max = priyatnost[0, 0];
        int povorot = 0;

        for (int n = 0; n < 4; n++) {
            for (int m = 0; m < shmass; m++) {
                if (priyatnost[n,m]>max)
                {
                    max = priyatnost[n, m];

                    fg.polozhenie.X = m - SH_FG;
                    povorot = n;
                }
            }
        }

        for (int i = 0; i < povorot; i++) {
            fg = PovorotFigury(fg);
        }

        while (Stolknovenie(fg, st, new Point(0, 1)) != 2) {
            fg.polozhenie.Y++;
        }

        return fg;
    }
    }
}
