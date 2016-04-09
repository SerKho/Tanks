package Tanks.tankobjects;

import Tanks.ActionField;
import Tanks.enums.Direction;
import Tanks.interfaces.BattleField;

/**
 * Created by 777 on 04.03.2016.
 */
public class Tiger extends AbstractTank {
    private int armor = 1;

    public Tiger(BattleField bf, int x, int y, Direction direction){
        super(bf, x, y, direction);
    }

    public Tiger(BattleField bf){
        super(bf);
    }

    public int getArmor() {
        return armor;
    }

    public void updateArmor(int i){
        armor+=i;
        if(armor<0){
            armor = 0;
        }
    }

    @Override
    public void destroy() {
        if(armor>0){
            updateArmor(-1);
        }
        else {
            super.destroy();
        }
    }
}
