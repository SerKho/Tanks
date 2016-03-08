package Tanks;

import com.sun.javafx.font.directwrite.DWFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 777 on 15.02.2016.
 */
public class ActionField extends JPanel {
    private final boolean COLORDED_MODE = false;
    private BattleField battleField;
    private Tank tank;
    private Tank agressor;
    private Bullet bullet;
    private final String[] MOVE = { "Illegal move", "Move UP", "Move DOWN", "Move LEFT", "Move RIGHT" };
    private int step=1;

    public ActionField()throws Exception {
        battleField = new BattleField();
        tank = new BT7(this, battleField);
        agressor = new BT7(this, battleField, Tank.position[(int)(Math.random()*3)],0, Direction.DOWN);
        bullet = new Bullet(-100, -100, Direction.UP);

        JFrame frame = new JFrame("BATTLE FIELD, DAY 2");
        frame.setLocation(400, 100);
        frame.setMinimumSize(new Dimension(battleField.getBF_WIDTH() + 16, battleField.getBF_HEIGHT() + 39));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }

    public void runTheGame() throws Exception {
//        tank.turn(Direction.UP);
//        System.out.println(tank.getDirection());
//        tank.turn(Direction.LEFT);
//        System.out.println(tank.getDirection());
//        tank.turn(Direction.DOWN);
//        System.out.println(tank.getDirection());
//        tank.turn(Direction.RIGHT);
//        System.out.println(tank.getDirection());
//        Thread.sleep(5000);
//        tank.fire();
//        Thread.sleep(5000);
//        tank.move();
//        Thread.sleep(10);
//        System.out.println(tank.getDirection());
//        tank.destroy();
//        Thread.sleep(5000);
//        tank.fire();
//        tank.move();
//        tank.turn(4);
//        tank.fire();
//        System.out.println(tank.getDirection());
//        while(true){tank.moveRandom();}
tank.clean();

    }



    private boolean processInterception() {
        if ((bullet.getX() >= 0 && bullet.getX() <= 576) && (bullet.getY() >= 0 && bullet.getY() <= 576)) {
            if (battleField.scanQuadrant(bulletQuadrant(0), bulletQuadrant(2)).equals("B")) {
                battleField.updateQuadrant(bulletQuadrant(0), bulletQuadrant(2), " ");
                return true;
            } else {
                return false;
            }
        } else {
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
                if (battleField.scanQuadrant(j, k).equals("B")) {
                    String coordinates = getQuadrantXY(j + 1, k + 1);
                    int separator = coordinates.indexOf("_");
                    int y = Integer.parseInt(coordinates.substring(0, separator));
                    int x = Integer.parseInt(coordinates.substring(separator + 1));
                    g.setColor(new Color(0, 0, 255));
                    g.fillRect(x, y, 64, 64);
                }
            }
        }

        g.setColor(new Color(255, 0, 0));
        g.fillRect(tank.getX(), tank.getY(), 64, 64);

        g.setColor(new Color(0, 255, 0));
        if (tank.getDirection().getDirection() == 1) {
            g.fillRect(tank.getX() + 20, tank.getY(), 24, 34);
        } else if (tank.getDirection().getDirection() == 2) {
            g.fillRect(tank.getX() + 20, tank.getY() + 30, 24, 34);
        } else if (tank.getDirection().getDirection() == 3) {
            g.fillRect(tank.getX(), tank.getY() + 20, 34, 24);
        } else {
            g.fillRect(tank.getX() + 30, tank.getY() + 20, 34, 24);
        }

        g.setColor(new Color(255, 0, 0));
        g.fillRect(agressor.getX(), agressor.getY(), 64, 64);

        g.setColor(new Color(0, 255, 0));
        if (agressor.getDirection().getDirection() == 1) {
            g.fillRect(agressor.getX() + 20, agressor.getY(), 24, 34);
        } else if (agressor.getDirection().getDirection() == 2) {
            g.fillRect(agressor.getX() + 20, agressor.getY() + 30, 24, 34);
        } else if (agressor.getDirection().getDirection() == 3) {
            g.fillRect(agressor.getX(), agressor.getY() + 20, 34, 24);
        } else {
            g.fillRect(agressor.getX() + 30, agressor.getY() + 20, 34, 24);
        }

        g.setColor(new Color(255, 255, 0));
        g.fillRect(bullet.getX(), bullet.getY(), 14, 14);
    }

    public void processTurn(Tank tank) throws Exception {

        repaint();
    }

    public void processMove(Tank tank) throws Exception {
            int covered = 0;
            while (covered < 64) {
                tank.updateX(vertOrHor(tank.getDirection().getDirection()) * positivOrNegativ(tank.getDirection().getDirection()) * step);
                tank.updateY((1 - vertOrHor(tank.getDirection().getDirection())) * positivOrNegativ(tank.getDirection().getDirection()) * step);
                System.out.println("TankX=" + tank.getX() + "; TankY=" + tank.getY() + "." + MOVE[tank.getDirection().getDirection()]);
                covered += step;
                repaint();
                Thread.sleep(tank.getSpeed());
            }

    }

    private int movePossibility(int direction) {
        if ((direction < 1 || direction > 4) || (direction == 1 && tank.getY() == 0) || (direction == 2 && tank.getY() == 512)
                || (direction == 3 && tank.getX() == 0) || (direction == 4 && tank.getX() == 512)) {
            return 0;
        } else {
            return 1;
        }
    }

    public void processFire(Bullet bullet) throws Exception {
        this.bullet = bullet;
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
}
