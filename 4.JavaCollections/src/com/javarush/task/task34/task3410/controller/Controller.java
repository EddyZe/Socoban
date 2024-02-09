package com.javarush.task.task34.task3410.controller;

import com.javarush.task.task34.task3410.model.Direction;
import com.javarush.task.task34.task3410.model.Model;
import com.javarush.task.task34.task3410.view.View;

public class Controller implements EventListener{

    private final View view;
    private final Model model;

    public static void main(String[] args) {
        Controller controller = new Controller();
    }

    public Controller() {
        this.model = new Model();
        this.view = new View(this);
        view.init();
    }

    @Override
    public void move(Direction direction) {
        
    }

    @Override
    public void restart() {

    }

    @Override
    public void startNextLevel() {

    }

    @Override
    public void levelCompleted(int level) {

    }
}
