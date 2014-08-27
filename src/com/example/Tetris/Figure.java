package com.example.Tetris;

import android.graphics.Color;

public class Figure
{
    private int x = 0;
    private int y = 0;

    private int figureWidth = 0;
    private int figureHeight = 0;

    private int numberOfFigure = 0;

    private int elements[][];

    private int numberOfFigures = 0;
    private int colorFigures[] = {Color.rgb(139,0,0), Color.rgb(0,206,209), Color.rgb(255,140,0), Color.rgb(0,0,255), Color.rgb(200,0,255), Color.rgb(0,128,0), Color.rgb(255,0,0)};
    private int arrayOfFigures[][][] = {
            {
                {1,1,0,0},
                {1,1,0,0},
                {0,0,0,0},
                {0,0,0,0}
            },{
                {0,1,0,0},
                {0,1,0,0},
                {0,1,0,0},
                {0,1,0,0}
            },{
                {0,1,0,0},
                {0,1,0,0},
                {0,1,1,0},
                {0,0,0,0}
            },{
                {0,1,0,0},
                {0,1,0,0},
                {1,1,0,0},
                {0,0,0,0}
            },{
                {0,1,0,0},
                {1,1,1,0},
                {0,0,0,0},
                {0,0,0,0}
            },{
                {1,0,0,0},
                {1,1,0,0},
                {0,1,0,0},
                {0,0,0,0}
            },{
                {0,0,1,0},
                {0,1,1,0},
                {0,1,0,0},
                {0,0,0,0}
            }};

    public Figure()
    {
        x = 0;
        y = 0;

        figureWidth = 4;
        figureHeight = 4;

        numberOfFigure = 0;

        numberOfFigures = 7;

        elements = new int[figureWidth][figureHeight];

        NewFigure();
    }

     public Figure(Figure source)
    {
        numberOfFigures = 7;

        x = source.getX();
        y = source.getY();
        
        figureWidth = source.getWidth();
        figureHeight = source.getHeight();

        elements = new int[figureWidth][figureHeight];

        for(int i = 0; i < figureWidth; i++)
        {
            for(int j = 0; j < figureHeight; j++)
            {
                elements[i][j] = source.getElements()[i][j];
            }
        }
        
        numberOfFigure = source.getNumberOfFigure();
    }

    public void NewFigure()
    {
        x = 0;
        y = 0;

        numberOfFigure = (int)(Math.random() * numberOfFigures);

        for(int i = 0; i < figureWidth; i++)
        {
            for(int j = 0; j < figureHeight; j++)
            {
                elements[i][j] = arrayOfFigures[numberOfFigure][i][j] * colorFigures[numberOfFigure];
            }
        }

        for (int i = (int)(Math.random() * 4); i > 0; i--)
        {
            Rotate();
        }
    }

    public void Rotate()
    {
        if(numberOfFigure != 0)
        {
            int tmpFigure[][] = new int[figureWidth][figureHeight];

            for (int i = 0; i < figureWidth - 1; i++)
            {
                for (int j = 0; j < figureHeight - 1; j++)
                {
                    tmpFigure[figureHeight - j - 2][i] = elements[i][j];
                }
            }

            if(numberOfFigure == 1)
            {
                tmpFigure[1][3] = elements[3][1];
                tmpFigure[3][1] = elements[1][3];
            }

            elements = tmpFigure.clone();
        }
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getWidth()
    {
        return figureWidth;
    }
    
    public int getHeight()
    {
        return figureHeight;
    }

    public int[][] getElements()
    {
        return elements;
    }

    public void Move(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getNumberOfFigure()
    {
        return numberOfFigure;
    }
}
