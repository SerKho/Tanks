package Tanks.interfaces;

import Tanks.ActionField;
import Tanks.bfobjects.*;

/**
 * Created by 777 on 15.02.2016.
 */
public class BattleField {
    private int BF_WIDTH = 576;
    private int BF_HEIGHT = 576;
    ActionField af;
    BFObject[][] battleField;
//            {
//            {" ", " ", "B", "B", " ", "B", "B", " ", " "},
//            {" ", "B", "B", " ", "B", " ", " ", " ", " "},
//            {" ", " ", " ", " ", "B", "B", "B", "B", "B"},
//            {"B", "B", "B", "B", "B", " ", " ", " ", "B"},
//            {"B", " ", " ", "B", "B", "B", "B", " ", " "},
//            {" ", " ", " ", " ", " ", "B", "B", "B", " "},
//            {" ", " ", "B", " ", " ", "B", " ", " ", "B"},
//            {" ", " ", " ", " ", "B", "B", "B", " ", "B"},
//            {" ", " ", " ", " ", " ", " ", " ", " ", " "}
//            };

    public int getBF_WIDTH() {
        return BF_WIDTH;
    }

    public int getBF_HEIGHT() {
        return BF_HEIGHT;
    }

    public BattleField(ActionField af){
        this.af = af;
        battleField = new BFObject[9][9];
        for(int i=0;i<9;i++){
            for(int j=0; j<9;j++){
                battleField[i][j] = new BFObject(af, i+1,j+1);
            }
        }
        battleField[0][3] = new Rock(af, 1,4);
        battleField[2][2] = new Rock(af, 3,3);
//        battleField[1][0] = new Brick(af, 2,1);
        battleField[2][0] = new Brick(af, 3,1);
        battleField[6][0] = new Brick(af, 7,1);
        battleField[7][2] = new Brick(af, 8,3);
        battleField[7][0] = new Brick(af, 8,1);
        battleField[2][5] = new Water(af, 3,6);
        battleField[8][4] = new Eagle(af, 9,5);
        battleField[6][4] = new Rock(af, 7,5);


    }

    public BFObject[][] getBattleField() {
        return battleField;
    }

    public BFObject scanBattleField(int x, int y){
        return battleField[x][y];
    }

//    public String scanQuadrant(int y, int x){
//        return battleField[y][x];
//    }
//
    public void updateQuadrant(int y, int x, BFObject object){
        battleField[y][x] = object;
    }

    public int getDimensionX(){
        return battleField[0].length;
    }

    public int getDimensionY(){
        return battleField.length;
    }
}
