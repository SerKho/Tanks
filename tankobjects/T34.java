package Tanks.tankobjects;

import Tanks.ActionField;
import Tanks.enums.Direction;
import Tanks.interfaces.BattleField;

/**
 * Created by 777 on 15.03.2016.
 */
public class T34 extends AbstractTank {
    T34(ActionField af, BattleField bf){
        super(af, bf);
    }
    T34(ActionField af, BattleField bf, int x, int y, Direction direction){
        super(af, bf, x, y, direction);
    }
}
