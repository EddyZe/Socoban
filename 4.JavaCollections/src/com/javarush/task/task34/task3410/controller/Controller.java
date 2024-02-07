package com.javarush.task.task34.task3410.controller;

import com.javarush.task.task34.task3410.model.Model;
import com.javarush.task.task34.task3410.view.View;

public class Controller {

    private final View view;
    private final Model model;

    public static void main(String[] args) {

    }

    public Controller()
    {
        this.model = new Model();
        this.view = new View(this);
    }
}
