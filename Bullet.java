package Tanks;

import Tanks.enums.Direction;
import Tanks.interfaces.Destroyable;
import Tanks.interfaces.Drawable;
import Tanks.tankobjects.AbstractTank;

import java.awt.*;

/**
 * Created by 777 on 15.02.2016.
 */
public class Bullet implements Drawable, Destroyable {
    private int speed = 5;
    private int x;
    private int y;
    private Direction direction;
    private AbstractTank abstractTank;

  public Bullet (int x, int y, Direction direction, AbstractTank abstractTank){
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.abstractTank = abstractTank;
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

    public AbstractTank getAbstractTank() {
        return abstractTank;
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

    public void draw(Graphics g){
        g.setColor(new Color(255, 255, 0));
        g.fillRect(this.getX(), this.getY(), 14, 14);
    }
}
