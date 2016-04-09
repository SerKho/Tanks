package Tanks;

import Tanks.bfobjects.BFObject;
import Tanks.bfobjects.Brick;
import Tanks.bfobjects.Eagle;
import Tanks.bfobjects.Rock;
import Tanks.enums.*;
import Tanks.enums.Action;
import Tanks.interfaces.BattleField;
import Tanks.tankobjects.AbstractTank;
import Tanks.tankobjects.BT7;
import Tanks.tankobjects.T34;
import Tanks.tankobjects.Tiger;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by 777 on 15.02.2016.
 */
public class ActionField extends JPanel {
    private final boolean COLORDED_MODE = false;
    private BattleField battleField;
    private AbstractTank defender;
    private AbstractTank agressor;
    private Bullet bullet;
    private final String[] MOVE = { "Illegal move", "Move UP", "Move DOWN", "Move LEFT", "Move RIGHT" };
    private int step=1;

    public ActionField()throws Exception {
        battleField = new BattleField(this);
        defender = new T34(battleField);
        agressor = new BT7(battleField, AbstractTank.POSITION[(int)(Math.random()*3)],0, Direction.DOWN);
        bullet = new Bullet(-100, -100, Direction.UP, this.defender);

        JFrame frame = new JFrame("BATTLE FIELD, DAY 2");
        frame.setLocation(400, 100);
        frame.setMinimumSize(new Dimension(battleField.getBF_WIDTH() + 16, battleField.getBF_HEIGHT() + 39));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }

    public void runTheGame() throws Exception {
//Action[]a = new Action[]{Action.NONE, Action.MOVE_UP, Action.MOVE_DOWN, Action.MOVE_LEFT, Action.MOVE_RIGHT, Action.FIRE};
Thread.sleep(10000);
        while(true){
//            agressor.setAction(a[(int)(Math.random() * 6)]);
//            tankAction(agressor);
            if(this.getBattleField().getBattleField()[8][4] instanceof Eagle) {
                agressor.setAction(agressor.eagleDestroyAlgoritm());
                tankAction(agressor);
            }
            else {
                break;
            }
        }
    }



    private boolean processInterception() {
        if ((bullet.getX() >= 10 && bullet.getX() <= 566) && (bullet.getY() >= 10 && bullet.getY() <= 566)) {
            if (getQuadrant(bullet.getAbstractTank().getX(), bullet.getAbstractTank().getY()).equals
                    (getQuadrant(bullet.getX(), bullet.getY()))) {
                return false;
            } else if (battleField.scanBattleField(bulletQuadrant(0),bulletQuadrant(2)) instanceof Brick){
                ((Brick) battleField.scanBattleField(bulletQuadrant(0),bulletQuadrant(2))).destroy();
                this.battleField.updateQuadrant(bulletQuadrant(0),bulletQuadrant(2),
                        new BFObject(this,bulletQuadrant(0)+1,bulletQuadrant(2)+1));
                return true;
            } else if ((battleField.scanBattleField(bulletQuadrant(0),bulletQuadrant(2)) instanceof Rock) &&
            ((battleField.scanBattleField(bulletQuadrant(0),bulletQuadrant(2)).getX()>0))) {
                ((Rock) battleField.scanBattleField(bulletQuadrant(0),bulletQuadrant(2))).destroy();
                return true;
            } else if ((battleField.scanBattleField(bulletQuadrant(0),bulletQuadrant(2)) instanceof Eagle) &&
            ((battleField.scanBattleField(bulletQuadrant(0),bulletQuadrant(2)).getX()>0))) {
                ((Eagle) battleField.scanBattleField(bulletQuadrant(0),bulletQuadrant(2))).destroy();
                this.battleField.updateQuadrant(bulletQuadrant(0),bulletQuadrant(2),
                        new BFObject(this,bulletQuadrant(0)+1,bulletQuadrant(2)+1));
                return true;

            } else if (getQuadrant(agressor.getX(), agressor.getY()).equals(getQuadrant(bullet.getX(), bullet.getY()))) {
                agressor.destroy();
                return true;
            }
            else if (getQuadrant(defender.getX(), defender.getY()).equals(getQuadrant(bullet.getX(), bullet.getY()))) {
                agressor.destroy();
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    private int bulletQuadrant(int coordinate) {
        return Integer.parseInt((getQuadrant(bullet.getX(), bullet.getY()) + "0").substring(coordinate, coordinate + 1));
    }

    public String getQuadrant(int x, int y) {

        return y / 64 + "_" + x / 64;
    }

    public String getQuadrantXY(int v, int h) {
        return (v - 1) * 64 + "_" + (h - 1) * 64;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int i = 0;
        Color cc;
        for (int v = 0; v < 9; v++) {
            for (int h = 0; h < 9; h++) {
                if (COLORDED_MODE) {
                    if (i % 2 == 0) {
                        cc = new Color(252, 241, 177);
                    } else {
                        cc = new Color(233, 243, 255);
                    }
                } else {
                    cc = new Color(180, 180, 180);
                }
                i++;
                g.setColor(cc);
                g.fillRect(h * 64, v * 64, 64, 64);
            }
        }

        for (int j = 0; j < battleField.getDimensionY(); j++) {
            for (int k = 0; k < battleField.getDimensionY(); k++) {
                battleField.scanBattleField(j, k).draw(g);
            }
        }

        agressor.draw(g);
        defender.draw(g);
        bullet.draw(g);
    }

    public void processTurn(AbstractTank defender, Direction direction) throws Exception {
        defender.setDirection(direction);
        System.out.println("Turn " + direction);
        repaint();
    }

    public void processMove(AbstractTank defender) throws Exception {
        if(movePossibility(defender, defender.getDirection().getDirection())==0){
            System.out.println("TankX=" + defender.getX() + "; TankY=" + defender.getY() + "." + MOVE[0]);
            return;
        }else {
            int covered = 0;
            while (covered < 64) {
                defender.updateX(vertOrHor(defender.getDirection().getDirection()) * positivOrNegativ(defender.getDirection().getDirection()) * step);
                defender.updateY((1 - vertOrHor(defender.getDirection().getDirection())) * positivOrNegativ(defender.getDirection().getDirection()) * step);
                System.out.println("TankX=" + defender.getX() + "; TankY=" + defender.getY() + "." + MOVE[defender.getDirection().getDirection()]);
                covered += step;
                repaint();
                Thread.sleep(defender.getSpeed());
            }
        }

    }

    private int movePossibility(AbstractTank defender, int direction) {
        if ((direction < 1 || direction > 4) || (direction == 1 && defender.getY() == 0) || (direction == 2 && defender.getY() == 512)
                || (direction == 3 && defender.getX() == 0) || (direction == 4 && defender.getX() == 512) ||
        (direction == 1 && !(battleField.scanBattleField((defender.getY()-1)/64, (defender.getX())/64).isCrossable())) ||
        (direction == 2 && !(battleField.scanBattleField((defender.getY()+64)/64, defender.getX()/64).isCrossable())) ||
        (direction == 3 && !(battleField.scanBattleField((defender.getY())/64, (defender.getX()-1)/64).isCrossable())) ||
        (direction == 4 && !(battleField.scanBattleField(defender.getY()/64, (defender.getX()+64)/64).isCrossable())))
        {
            return 0;
        } else {
            return 1;
        }
    }

    public void processFire(AbstractTank tank) throws Exception {
        this.bullet = new Bullet(tank.getX()+25, tank.getY()+25, tank.getDirection(), tank);
        while (bullet.getY() >= 0 && bullet.getY() <= 576 && bullet.getX() >= 0 && bullet.getX() <= 576) {
            bullet.updateX(vertOrHor(tank.getDirection().getDirection()) * positivOrNegativ(tank.getDirection().getDirection()) * bullet.getSpeed());
            bullet.updateY((1 - vertOrHor(tank.getDirection().getDirection())) * positivOrNegativ(tank.getDirection().getDirection()) * bullet.getSpeed());
            if (processInterception() == true) {
                break;
            }
            repaint();
            Thread.sleep(tank.getSpeed());
        }
        bullet.destroy();
        repaint();
    }

   private int vertOrHor(int direction) {
        if (direction < 3) {
            return 0;
        } else {
            return 1;
        }
    }

   private int positivOrNegativ(int direction) {
        if (direction == 2 || direction == 4) {
            return 1;
        } else {
            return -1;
        }
    }

    public BattleField getBattleField() {
        return battleField;
    }

    public void tankAction(AbstractTank tank)throws Exception{
        if(tank.setUp().equals(Action.MOVE_UP)){
            this.processTurn(tank, Direction.UP);
            this.processMove(tank);
        }
        else if(tank.setUp().equals(Action.MOVE_DOWN)){
            this.processTurn(tank, Direction.DOWN);
            this.processMove(tank);
        }
        else if(tank.setUp().equals(Action.MOVE_LEFT)){
            this.processTurn(tank, Direction.LEFT);
            this.processMove(tank);
        }
        else if(tank.setUp().equals(Action.MOVE_RIGHT)){
            this.processTurn(tank, Direction.RIGHT);
            this.processMove(tank);
        }
        else if(tank.setUp().equals(Action.TURN_UP)){
            this.processTurn(tank, Direction.UP);
        }
        else if(tank.setUp().equals(Action.TURN_DOWN)){
            this.processTurn(tank, Direction.DOWN);
        }
        else if(tank.setUp().equals(Action.TURN_LEFT)){
            this.processTurn(tank, Direction.LEFT);
        }
        else if(tank.setUp().equals(Action.TURN_RIGHT)){
            this.processTurn(tank, Direction.RIGHT);
        }
        else if(tank.setUp().equals(Action.FIRE)){
            this.processFire(tank);
        }
        else{}
    }
}
