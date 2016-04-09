package Tanks.tankobjects;

import Tanks.ActionField;
import Tanks.bfobjects.Brick;
import Tanks.bfobjects.Rock;
import Tanks.bfobjects.Water;
import Tanks.enums.Action;
import Tanks.enums.Direction;
import Tanks.interfaces.BattleField;

/**
 * Created by 777 on 04.03.2016.
 */
public class BT7 extends AbstractTank {
    private Action previousAction = Action.MOVE_DOWN;
    private int speed = super.getSpeed()/2;

   public BT7(BattleField bf, int x, int y, Direction direction){
        super(bf, x, y, direction);
    }

  public   BT7(BattleField bf){
        super(bf);
    }


}
