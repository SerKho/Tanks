package Tanks;

/**
 * Created by 777 on 04.03.2016.
 */
public class BT7 extends AbstractTank {
    private int speed = super.getSpeed()/2;

    BT7(ActionField af, BattleField bf, int x, int y, Direction direction){
        super(af, bf, x, y, direction);
    }

    BT7(ActionField af, BattleField bf){
        super(af, bf);
    }
}
