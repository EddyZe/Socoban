package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable{
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
