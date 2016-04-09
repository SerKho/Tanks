package Tanks.bfobjects;

import Tanks.ActionField;
import Tanks.interfaces.Destroyable;

import java.awt.*;

/**
 * Created by 777 on 20.03.2016.
 */
public class Rock extends BFObject implements Destroyable {

    public Rock(ActionField af, int x, int y){
        super(af,x,y);
        this.setCrossable(false);
    }

    public void draw(Graphics g){
        g.setColor(new Color(128, 128, 128));
        g.fillRect(this.getY(), this.getX(), 64, 64);
    }

    public void destroy(){
        this.setX(-1000);
        this.setY(-1000);
        af.repaint();

    }
}

