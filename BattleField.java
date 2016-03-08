package Tanks;

/**
 * Created by 777 on 15.02.2016.
 */
public class BattleField {
    private int BF_WIDTH = 576;
    private int BF_HEIGHT = 576;
    private String[][] battleField =
            {
            {" ", " ", "B", "B", " ", "B", "B", " ", " "},
            {" ", "B", "B", " ", "B", " ", " ", " ", " "},
            {" ", " ", " ", " ", "B", "B", "B", "B", "B"},
            {"B", "B", "B", "B", "B", " ", " ", " ", "B"},
            {"B", " ", " ", "B", "B", "B", "B", " ", " "},
            {" ", " ", " ", " ", " ", "B", "B", "B", " "},
            {" ", " ", "B", " ", " ", "B", " ", " ", "B"},
            {" ", " ", " ", " ", "B", "B", "B", " ", "B"},
            {" ", " ", " ", " ", " ", " ", " ", " ", " "}
            };

    public int getBF_WIDTH() {
        return BF_WIDTH;
    }

    public int getBF_HEIGHT() {
        return BF_HEIGHT;
    }

    public String[][] getBattleField() {
        return battleField;
    }

    public String scanQuadrant(int y, int x){
        return battleField[y][x];
    }

    public void updateQuadrant(int y, int x, String object){
        battleField[y][x] = object;
    }

    public int getDimensionX(){
        return battleField[0].length;
    }

    public int getDimensionY(){
        return battleField.length;
    }
}
