package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Home extends GameObject{
    public Home(int x, int y) {
        super(x, y);
        this.height = 2;
        this.width = 2;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.drawOval(this.x, this.y, this.width, this.height);
    }
}
