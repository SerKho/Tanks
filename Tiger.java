package Tanks;

/**
 * Created by 777 on 04.03.2016.
 */
public class Tiger extends Tank {
    private int armor = 1;

    Tiger(ActionField af, BattleField bf, int x, int y, Direction direction){
        super(af, bf, x, y, direction);
    }

    Tiger(ActionField af, BattleField bf){
        super(af, bf);
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
