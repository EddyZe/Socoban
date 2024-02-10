package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;
import java.util.Set;

public class Model {

    public static final int FIELD_CELL_SIZE = 20;

    private EventListener eventListener;

    private GameObjects gameObjects = getGameObjects();
    private int currentLevel = 1;
    LevelLoader levelLoader = new LevelLoader(Paths.get("C:\\Users\\zemae\\IdeaProjects\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task34\\task3410\\res\\levels.txt"));


    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restartLevel(currentLevel);
    }

    public void move(Direction direction) {
        if (checkWallCollision(gameObjects.getPlayer(), direction))
            return;
        if (checkBoxCollisionAndMoveIfAvailable(direction))
            return;

        int x = 0;
        int y = 0;
        switch (direction) {
            case LEFT:
                x = -Model.FIELD_CELL_SIZE;
                break;
            case RIGHT:
                x = Model.FIELD_CELL_SIZE;
                break;
            case UP:
                y = -Model.FIELD_CELL_SIZE;
                break;
            case DOWN:
                y = Model.FIELD_CELL_SIZE;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }
        gameObjects.getPlayer().move(x, y);
        checkCompletion();
    }


    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        Set<Wall> wallSet = gameObjects.getWalls();
        for (Wall wall : wallSet) {
            if (gameObject.isCollision(wall, direction))
                return true;
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        Player player = gameObjects.getPlayer();
        Set<Box> boxes = gameObjects.getBoxes();
        for (Box box : boxes) {
            if (player.isCollision(box, direction)) {
                for (Box b : boxes) {
                    if ((!box.equals(b)) && box.isCollision(b, direction))
                        return true;
                    if (checkWallCollision(box, direction))
                        return true;
                }
                int x = 0;
                int y = 0;
                switch (direction) {
                    case LEFT:
                        x = -Model.FIELD_CELL_SIZE;
                        break;
                    case RIGHT:
                        x = Model.FIELD_CELL_SIZE;
                        break;
                    case UP:
                        y = -Model.FIELD_CELL_SIZE;
                        break;
                    case DOWN:
                        y = Model.FIELD_CELL_SIZE;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + direction);
                }
                box.move(x, y);
            }
        }
        return false;
    }

    public void checkCompletion() {
        Set<Box> boxes = gameObjects.getBoxes();
        Set<Home> homes = gameObjects.getHomes();

        int count = 0;
        for (Home h : homes) {
            for (Box b : boxes) {
                if (h.getX() == b.getX() && h.getY() == b.getY())
                    count++;
            }
        }
        if (count == homes.size())
            eventListener.levelCompleted(currentLevel);
    }
}
