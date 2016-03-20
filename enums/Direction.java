package Tanks.enums;

/**
 * Created by 777 on 02.03.2016.
 */
public enum Direction {
    UP(1), DOWN(2), LEFT(3), RIGHT(4);
    int id;

    private Direction(int id){
        this.id = id;
        getDirection();
    }

    public int getDirection(){
        return id;
    }

    public Direction getInstanse(int id){
        for(Direction direction: values()) {
            if (direction.getDirection() == id) {
                return direction;
            }
        }
        return this;
    }
}
