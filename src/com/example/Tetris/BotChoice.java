package com.example.Tetris;

public class BotChoice
{
    private int x = 0;
    private int rotateCount = 0;
    private int nextFigureX = 0;
    private int nextFigureRotateCount = 0;
    private int weight = 0;

    public BotChoice()
    {
        x = 0;
        rotateCount = 0;
        weight = 0;
    }

    public int getWeight() {
        return weight;
    }

    public void addWeight(int weight) {
        this.weight += weight;
    }

    public int getRotateCount() {
        return rotateCount;
    }

    public void setRotateCount(int rotateCount) {
        this.rotateCount = rotateCount;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getNextFigureRotateCount() {
        return nextFigureRotateCount;
    }

    public void setNextFigureRotateCount(int nextFigureRotateCount) {
        this.nextFigureRotateCount = nextFigureRotateCount;
    }

    public int getNextFigureX() {
        return nextFigureX;
    }

    public void setNextFigureX(int nextFigureX) {
        this.nextFigureX = nextFigureX;
    }
}
