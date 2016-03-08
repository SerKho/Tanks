package Tanks;

import Tanks.ActionField;

/**
 * Created by 777 on 17.02.2016.
 */
public class Launcher {

    public static void main(String[] args) throws Exception {
//        System.out.println( Tank.position[(int)(Math.random()*3)]);
        ActionField af = new ActionField();
        af.runTheGame();
    }
}
