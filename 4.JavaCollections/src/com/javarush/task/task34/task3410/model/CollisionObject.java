package com.javarush.task.task34.task3410.model;

public abstract class CollisionObject extends GameObject{

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        switch (direction) {
            case LEFT:
                return ((this.x - Model.FIELD_CELL_SIZE) == gameObject.getX()) && (this.y == gameObject.getY());
            case RIGHT:
                return ((this.x + Model.FIELD_CELL_SIZE) == gameObject.getX()) && (this.getY() == gameObject.getY());
            case UP:
                return ((this.x == gameObject.getX() && (this.y - Model.FIELD_CELL_SIZE) == gameObject.getY()));
            case DOWN:
                return ((this.x == gameObject.getX() && (this.y + Model.FIELD_CELL_SIZE) == gameObject.getY()));
        }
        return false;
    }
}
