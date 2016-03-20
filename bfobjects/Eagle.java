package Tanks.bfobjects;

import Tanks.ActionField;
import Tanks.Destroyable;

import java.awt.*;

/**
 * Created by 777 on 20.03.2016.
 */
public class Eagle extends BFObject implements Destroyable {

    public Eagle(ActionField af, int x, int y){
        super(af,x,y);
    }

    public void draw(Graphics g){
        g.setColor(new Color(255, 215, 0));
        g.fillRect(this.getY(), this.getX(), 64, 64);
    }

    public void destroy(){
        this.setX(-1000);
        this.setY(-1000);
        af.repaint();

    }
}

