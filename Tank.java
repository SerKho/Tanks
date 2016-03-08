package Tanks;

import com.sun.prism.Graphics;

import java.awt.*;
import java.util.Random;

/**
 * Created by 777 on 16.02.2016.
 */
public class Tank {
    final static int[] position = {0, 256, 512};
    private int speed = 10;
    private int x;
    private int y;
    private Direction direction;

    private ActionField af;
    private BattleField bf;

    public int getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void updateX(int x){
        this.x+=x;
    }

    public void updateY(int y){
        this.y+=y;
    }


    Tank(ActionField af, BattleField bf){
        this(af,bf,0,512, Direction.UP);
    }

    Tank(ActionField af, BattleField bf, int x, int y, Direction direction) {
        this.af = af;
        this.bf = bf;
        this.x = x;
        this.y = y;
        this.direction = direction;

    }


    public void turn(Direction direction) throws Exception {
        this.direction = direction;
        System.out.println("Turn " + this.direction);
        af.processTurn(this);
    }

    public void move() throws Exception {
        af.processMove(this)  ;
    }

    public void fire() throws Exception {
        Bullet bullet = new Bullet(x + 25, y + 25, direction);
        af.processFire(bullet);
    }

    public void moveRandom()throws Exception {
        Random r = new Random();
        int i;
        while (true) {
            i = r.nextInt(5);
            if (i > 0) {
                setDirection(direction.getInstanse(i));
                af.processMove(this);
            }
        }
    }

    public void moveToQuadrant(int v, int h)throws Exception {
        int x = Integer.parseInt((af.getQuadrantXY(v, h)).substring(0, (af.getQuadrantXY(v, h)).indexOf("_")));
        int y = Integer.parseInt((af.getQuadrantXY(v, h)).substring((af.getQuadrantXY(v, h)).indexOf("_") + 1));
        while (x != getX()) {
            turn(direction.getInstanse(2 + moveAnalize(x, getX())));
            fireWhileMove(1, x, 1);
            move();
        }
        while (y != getY()) {
            turn(direction.getInstanse(moveAnalize(y, getY())));
            fireWhileMove(0, y, 1);
            move();
        }
    }

    int moveAnalize(int coordinate, int tankCoordinate) {
        if (coordinate < tankCoordinate) {
            return 1;
        } else {
            return 2;
        }
    }

    int analizeNextQuadrant(int parameter) {
        if (parameter == 1) {
            return -1;
        } else {
            return 64;
        }
    }

    void fireWhileMove(int a, int b, int c) throws Exception {
        if (bf.scanQuadrant(Integer.parseInt(af.getQuadrant(getX() + a * c * analizeNextQuadrant(moveAnalize(b, getX())),
                getY() + (1 - a) * c * analizeNextQuadrant(moveAnalize(b, getY()))).substring(0,1)),
                Integer.parseInt(af.getQuadrant(getX() + a * c * analizeNextQuadrant(moveAnalize(b, getX())),
                getY() + (1 - a) * c	* analizeNextQuadrant(moveAnalize(b, getY()))).substring(2))).equals("B"))
        {
            fire();
        }
    }

    void clean() throws Exception {
        fireWhileMove(1, 0, 0);
        moveToQuadrant(1, 9);
        for (int i = 0; i < 9; i++) {
            int countB = 0;
            for (int j = 0; j < 9; j++) {
                if (bf.scanQuadrant(j, i).equals("B")) {
                    countB += 1;
                }
            }
            if (countB > 0 && i > 0) {
                if (bf.scanQuadrant(8,i).equals("B")) {
                    countB -= 1;
                }
                moveToQuadrant(i + 1, 9);
                turn(direction.getInstanse(1));
            }
            while (countB > 0) {
                fire();
                countB--;
            }
        }
        turn(direction.getInstanse(1));
    }

    private void destroy(){
        updateX(-1000);
        updateY(-1000);
        af.repaint();
    }

}


