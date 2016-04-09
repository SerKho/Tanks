package Tanks.bfobjects;

import Tanks.ActionField;

import java.awt.*;

/**
 * Created by 777 on 20.03.2016.
 */
public class Water extends BFObject {

    public Water(ActionField af, int x, int y){
        super(af, x, y);
        this.setCrossable(false);
    }

    public void draw(Graphics g){
        g.setColor(new Color(30, 144, 255));
        g.fillRect(this.getY(), this.getX(), 64, 64);
    }
}
