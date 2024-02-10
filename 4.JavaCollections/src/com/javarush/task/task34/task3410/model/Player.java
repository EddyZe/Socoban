package com.javarush.task.task34.task3410.model;

import javax.swing.*;
import java.awt.*;

public class Player extends CollisionObject implements Movable{
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public void draw(Graphics graphics) {
        String[] ss = getClass().getPackage().toString().split(" ");
        String s = ss[1].replace(".", "\\");
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\zemae\\IdeaProjects\\JavaRushTasks\\4.JavaCollections\\src\\" + s + "\\player2.jpg");
        Image player = imageIcon.getImage();
        graphics.setColor(Color.black);
        graphics.drawImage(player, this.x, this.y, this.width, this.height, Color.BLACK, null);
    }
}
