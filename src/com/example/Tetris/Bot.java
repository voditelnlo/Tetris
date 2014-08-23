package com.example.Tetris;

/**
 * Created by nnn on 22.08.2014.

public class Bot
{
    private int matrixWidth = 0;
    private int matrixHeight = 0;

    private int figureWidth = 0;
    private int figureHeight = 0;

    private int delayCounter = 0;
    private int delay = 0;

    private int score = 0;

    private int matrix[][];

    private boolean gameOver = true;

    private Figure figure = new Figure();

    public void Bot(GameState game)
    {
        matrixWidth = game.getMatrixWidth();
        matrixHeight = game.getMatrixHeight();
        matrix = new int[matrixWidth][matrixHeight];

        figureWidth = game.getFigureWidth();
        figureHeight = game.getFigureHeight();
        game.get
        for(int i = 0; i < figureWidth - 1; i++)
        {
            for (int j = 0; j < figureHeight - 1; j++)
            {
                figure.
                        ga
            }
        }


    }




        private void fsdfdf(GameState game)
        {
            int SH_ST = game.getMatrixWidth();
            int SH_FG = game.getFigureWidth();

            int VS_ST = game.getMatrixHeight();

            int shmass = SH_ST + SH_FG;

            int[][] priyatnost = new int[4][shmass];

            int[][]st = (int[,])stak.Clone();

            Figure fg = fgr;

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
 */