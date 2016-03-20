package Tanks.interfaces;

import Tanks.ActionField;
import Tanks.bfobjects.BFObject;
import Tanks.bfobjects.Brick;
import Tanks.bfobjects.Eagle;
import Tanks.bfobjects.Water;

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

        battleField[0][0] = new BFObject(af, 1,1);
        battleField[0][1] = new BFObject(af, 1,2);
        battleField[0][2] = new BFObject(af, 1,3);
        battleField[0][3] = new Brick(af, 1,4);
        battleField[0][4] = new BFObject(af, 1,5);
        battleField[0][5] = new BFObject(af, 1,6);
        battleField[0][6] = new BFObject(af, 1,7);
        battleField[0][7] = new Brick(af, 1,8);
        battleField[0][8] = new BFObject(af, 1,9);

        battleField[1][0] = new BFObject(af, 2,1);
        battleField[1][1] = new BFObject(af, 2,2);
        battleField[1][2] = new BFObject(af, 2,3);
        battleField[1][3] = new Brick(af, 2,4);
        battleField[1][4] = new BFObject(af, 2,5);
        battleField[1][5] = new BFObject(af, 2,6);
        battleField[1][6] = new BFObject(af, 2,7);
        battleField[1][7] = new Brick(af, 2,8);
        battleField[1][8] = new BFObject(af, 2,9);

        battleField[2][0] = new BFObject(af, 3,1);
        battleField[2][1] = new Water(af, 3,2);
        battleField[2][2] = new Water(af, 3,3);
        battleField[2][3] = new Water(af, 3,4);
        battleField[2][4] = new Water(af, 3,5);
        battleField[2][5] = new Water(af, 3,6);
        battleField[2][6] = new Water(af, 3,7);
        battleField[2][7] = new Water(af, 3,8);
        battleField[2][8] = new BFObject(af, 3,9);

        battleField[3][0] = new Brick(af, 4,1);
        battleField[3][1] = new BFObject(af, 4,2);
        battleField[3][2] = new BFObject(af, 4,3);
        battleField[3][3] = new BFObject(af, 4,4);
        battleField[3][4] = new BFObject(af, 4,5);
        battleField[3][5] = new BFObject(af, 4,6);
        battleField[3][6] = new BFObject(af, 4,7);
        battleField[3][7] = new BFObject(af, 4,8);
        battleField[3][8] = new BFObject(af, 4,9);

        battleField[4][0] = new Brick(af, 5,1);
        battleField[4][1] = new BFObject(af, 5,2);
        battleField[4][2] = new BFObject(af, 5,3);
        battleField[4][3] = new BFObject(af, 5,4);
        battleField[4][4] = new BFObject(af, 5,5);
        battleField[4][5] = new BFObject(af, 5,6);
        battleField[4][6] = new BFObject(af, 5,7);
        battleField[4][7] = new BFObject(af, 5,8);
        battleField[4][8] = new BFObject(af, 5,9);

        battleField[5][0] = new BFObject(af, 6,1);
        battleField[5][1] = new BFObject(af, 6,2);
        battleField[5][2] = new BFObject(af, 6,3);
        battleField[5][3] = new BFObject(af, 6,4);
        battleField[5][4] = new BFObject(af, 6,5);
        battleField[5][5] = new BFObject(af, 6,6);
        battleField[5][6] = new BFObject(af, 6,7);
        battleField[5][7] = new BFObject(af, 6,8);
        battleField[5][8] = new BFObject(af, 6,9);

        battleField[6][0] = new BFObject(af, 7,1);
        battleField[6][1] = new BFObject(af, 7,2);
        battleField[6][2] = new BFObject(af, 7,3);
        battleField[6][3] = new BFObject(af, 7,4);
        battleField[6][4] = new BFObject(af, 7,5);
        battleField[6][5] = new BFObject(af, 7,6);
        battleField[6][6] = new BFObject(af, 7,7);
        battleField[6][7] = new BFObject(af, 7,8);
        battleField[6][8] = new BFObject(af, 7,9);

        battleField[7][0] = new BFObject(af, 8,1);
        battleField[7][1] = new BFObject(af, 8,2);
        battleField[7][2] = new BFObject(af, 8,3);
        battleField[7][3] = new BFObject(af, 8,4);
        battleField[7][4] = new BFObject(af, 8,5);
        battleField[7][5] = new BFObject(af, 8,6);
        battleField[7][6] = new BFObject(af, 8,7);
        battleField[7][7] = new BFObject(af, 8,8);
        battleField[7][8] = new BFObject(af, 8,9);

        battleField[8][0] = new BFObject(af, 9,1);
        battleField[8][1] = new BFObject(af, 9,2);
        battleField[8][2] = new BFObject(af, 9,3);
        battleField[8][3] = new BFObject(af, 9,4);
        battleField[8][4] = new Eagle(af, 9,5);
        battleField[8][5] = new BFObject(af, 9,6);
        battleField[8][6] = new BFObject(af, 9,7);
        battleField[8][7] = new BFObject(af, 9,8);
        battleField[8][8] = new BFObject(af, 9,9);

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
//    public void updateQuadrant(int y, int x, String object){
//        battleField[y][x] = object;
//    }

    public int getDimensionX(){
        return battleField[0].length;
    }

    public int getDimensionY(){
        return battleField.length;
    }
}
