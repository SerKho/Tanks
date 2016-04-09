package Tanks.bfobjects;

import Tanks.ActionField;
import Tanks.interfaces.Drawable;

import java.awt.*;

/**
 * Created by 777 on 20.03.2016.
 */
public class BFObject implements Drawable{
    int x;
    int y;
    boolean crossable;
    ActionField af;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isCrossable() {
        return crossable;
    }

    public void setCrossable(boolean crossable) {
        this.crossable = crossable;
    }

    public ActionField getAf() {
        return af;
    }

    public BFObject(ActionField af, int x, int y){
        this.af = af;
        this.x = (x-1)*64;
        this.y = (y-1)*64;
        this.setCrossable(true);
    }


    public void draw(Graphics g){}
}
