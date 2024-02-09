package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class Field extends JPanel {

    private View view;

    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        Set<GameObject> gameObjects = view.getGameObjects().getAll();
        gameObjects.forEach((i) -> i.draw(g));

    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }
}
