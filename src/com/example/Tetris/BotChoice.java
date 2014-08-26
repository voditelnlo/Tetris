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
        nextFigureX = 0;
        nextFigureRotateCount = 0;
        weight = 0;
    }

    public BotChoice(BotChoice botChoice)
    {
        this.x = botChoice.getX();
        this.rotateCount = botChoice.getRotateCount();
        this.nextFigureX = botChoice.getNextFigureX();
        this.nextFigureRotateCount = botChoice.getNextFigureRotateCount();
        this.weight = botChoice.getWeight();
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
