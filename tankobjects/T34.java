package Tanks.tankobjects;

import Tanks.ActionField;
import Tanks.enums.Direction;
import Tanks.interfaces.BattleField;

import java.awt.*;

/**
 * Created by 777 on 15.03.2016.
 */
public class T34 extends AbstractTank {
   public T34(BattleField bf){
        super(bf);
    }
   public T34(BattleField bf, int x, int y, Direction direction){
        super(bf, x, y, direction);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 150, 150));
        g.fillRect(this.getX(), this.getY(), 64, 64);

        g.setColor(new Color(20, 20, 50));
        if (this.getDirection().getDirection() == 1) {
            g.fillRect(this.getX() + 20, this.getY(), 24, 34);
        } else if (this.getDirection().getDirection() == 2) {
            g.fillRect(this.getX() + 20, this.getY() + 30, 24, 34);
        } else if (this.getDirection().getDirection() == 3) {
            g.fillRect(this.getX(), this.getY() + 20, 34, 24);
        } else {
            g.fillRect(this.getX() + 30, this.getY() + 20, 34, 24);
        }
    }
}
