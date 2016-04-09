package Tanks.enums;

/**
 * Created by 777 on 06.04.2016.
 */
public enum Action {
    NONE(1), FIRE(2), MOVE_UP(3), MOVE_DOWN(4), MOVE_LEFT(5), MOVE_RIGHT(6), TURN_UP(7), TURN_DOWN(8), TURN_LEFT(9), TURN_RIGHT(10);
    int id;

    private Action(int id){
        this.id = id;
        getAction();
    }

    public int getAction(){
        return id;
    }


    public Action getInstanse(int id){
        for(Action action: values()) {
            if (action.getAction() == id) {
                return action;
            }
        }
        return this;
    }
}
