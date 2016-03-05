package Tanks;

/**
 * Created by 777 on 15.02.2016.
 */
public class Bullet {
    private int speed = 5;
    private int x;
    private int y;
    private Direction direction;

    Bullet (int x, int y, Direction direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getY() {
        return y;
    }

    public void updateX(int x){
        this.x += x;
    }

    public void updateY(int y){
        this.y += y;
    }

    public void destroy(){
        y = -100;
        x = -100;
    }
}
