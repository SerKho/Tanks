package Tanks;

import Tanks.bfobjects.Brick;
import Tanks.bfobjects.Eagle;
import Tanks.bfobjects.Rock;

import javax.swing.*;
import java.awt.*;

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
        defender = new T34(this, battleField);
        agressor = new Tiger(this, battleField, AbstractTank.POSITION[(int)(Math.random()*3)],0, Direction.DOWN);
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
//        defender.turn(Direction.UP);
//        System.out.println(defender.getDirection());
//        defender.turn(Direction.LEFT);
//        System.out.println(defender.getDirection());
//        defender.turn(Direction.DOWN);
//        System.out.println(defender.getDirection());
//        defender.turn(Direction.RIGHT);
//        System.out.println(defender.getDirection());
//        Thread.sleep(5000);
        defender.fire();
        defender.fire();
//        Thread.sleep(5000);
//        defender.move();
//        Thread.sleep(10);
//        System.out.println(defender.getDirection());
//        defender.destroy();
//        Thread.sleep(5000);
        defender.fire();
//        defender.move();
//        defender.turn(4);
        defender.fire();
//        System.out.println(defender.getDirection());
//        while(true){defender.moveRandom();}
//defender.clean();

    }



    private boolean processInterception() {
        if ((bullet.getX() >= 0 && bullet.getX() <= 576) && (bullet.getY() >= 0 && bullet.getY() <= 576)) {
            if (getQuadrant(bullet.getAbstractTank().getX(), bullet.getAbstractTank().getY()).equals
                    (getQuadrant(bullet.getX(), bullet.getY()))) {
                return false;
            } else if ((battleField.scanBattleField(bulletQuadrant(0),bulletQuadrant(2)) instanceof Brick) &&
                    ((battleField.scanBattleField(bulletQuadrant(0),bulletQuadrant(2)).getX()>0))) {
                ((Brick) battleField.scanBattleField(bulletQuadrant(0),bulletQuadrant(2))).destroy();
                return true;
            } else if ((battleField.scanBattleField(bulletQuadrant(0),bulletQuadrant(2)) instanceof Rock) &&
            ((battleField.scanBattleField(bulletQuadrant(0),bulletQuadrant(2)).getX()>0))) {
                ((Rock) battleField.scanBattleField(bulletQuadrant(0),bulletQuadrant(2))).destroy();
                return true;
            } else if ((battleField.scanBattleField(bulletQuadrant(0),bulletQuadrant(2)) instanceof Eagle) &&
            ((battleField.scanBattleField(bulletQuadrant(0),bulletQuadrant(2)).getX()>0))) {
                ((Eagle) battleField.scanBattleField(bulletQuadrant(0),bulletQuadrant(2))).destroy();
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

    public void processTurn(AbstractTank defender) throws Exception {

        repaint();
    }

    public void processMove(AbstractTank defender) throws Exception {
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

    private int movePossibility(int direction) {
        if ((direction < 1 || direction > 4) || (direction == 1 && defender.getY() == 0) || (direction == 2 && defender.getY() == 512)
                || (direction == 3 && defender.getX() == 0) || (direction == 4 && defender.getX() == 512)) {
            return 0;
        } else {
            return 1;
        }
    }

    public void processFire(Bullet bullet) throws Exception {
        this.bullet = bullet;
        while (bullet.getY() >= 0 && bullet.getY() <= 576 && bullet.getX() >= 0 && bullet.getX() <= 576) {
            bullet.updateX(vertOrHor(defender.getDirection().getDirection()) * positivOrNegativ(defender.getDirection().getDirection()) * bullet.getSpeed());
            bullet.updateY((1 - vertOrHor(defender.getDirection().getDirection())) * positivOrNegativ(defender.getDirection().getDirection()) * bullet.getSpeed());
            if (processInterception() == true) {
                break;
            }
            repaint();
            Thread.sleep(defender.getSpeed());
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
}
