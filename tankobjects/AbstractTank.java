package Tanks.tankobjects;

import Tanks.ActionField;
import Tanks.Bullet;
import Tanks.bfobjects.Brick;
import Tanks.bfobjects.Rock;
import Tanks.bfobjects.Water;
import Tanks.enums.Action;
import Tanks.enums.Direction;
import Tanks.interfaces.BattleField;
import Tanks.interfaces.Destroyable;
import Tanks.interfaces.Drawable;

import java.awt.*;
import java.util.Random;

/**
 * Created by 777 on 16.02.2016.
 */
public abstract class AbstractTank implements Drawable, Destroyable {
    public final static int[] POSITION = {0, 256, 512};
    private int speed = 10;
    private int x;
    private int y;
    private Action previousAction = Action.MOVE_DOWN;
    private Direction direction;
    public final static Action[] MOVES = {Action.MOVE_UP, Action.MOVE_DOWN, Action.MOVE_LEFT, Action.MOVE_RIGHT};

//    private ActionField af;
    private BattleField bf;
    private Action action;

    public Action getPreviousAction() {
        return previousAction;
    }

    public void setPreviousAction(Action previousAction) {
        this.previousAction = previousAction;
    }

   public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

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


    AbstractTank(BattleField bf){
        this(bf,128,512, Direction.UP);
    }

    AbstractTank(BattleField bf, int x, int y, Direction direction) {
//        this.af = af;
        this.bf = bf;
        this.x = x;
        this.y = y;
        this.direction = direction;

    }

    public Action setUp(){
        return getAction();
    }

    public BattleField getBf() {
        return bf;
    }

    //    public void turn(Direction direction) throws Exception {
//        this.direction = direction;
//        System.out.println("Turn " + this.direction);
//        af.processTurn(this);
//    }

//    public void move() throws Exception {
//        af.processMove(this)  ;
//    }
//
//    public void fire() throws Exception {
//        Bullet bullet = new Bullet(x + 25, y + 25, direction, this);
//        af.processFire(bullet);
//    }

//    public void moveRandom()throws Exception {
//        Random r = new Random();
//        int i;
//        while (true) {
//            i = r.nextInt(5);
//            if (i > 0) {
//                setDirection(direction.getInstanse(i));
//                af.processMove(this);
//            }
//        }
//    }

//    public void moveToQuadrant(int v, int h)throws Exception {
//        int x = Integer.parseInt((af.getQuadrantXY(v, h)).substring(0, (af.getQuadrantXY(v, h)).indexOf("_")));
//        int y = Integer.parseInt((af.getQuadrantXY(v, h)).substring((af.getQuadrantXY(v, h)).indexOf("_") + 1));
//        while (x != getX()) {
//            turn(direction.getInstanse(2 + moveAnalize(x, getX())));
//            fireWhileMove(1, x, 1);
//            move();
//        }
//        while (y != getY()) {
//            turn(direction.getInstanse(moveAnalize(y, getY())));
//            fireWhileMove(0, y, 1);
//            move();
//        }
//    }

//    int moveAnalize(int coordinate, int tankCoordinate) {
//        if (coordinate < tankCoordinate) {
//            return 1;
//        } else {
//            return 2;
//        }
//    }
//
//    int analizeNextQuadrant(int parameter) {
//        if (parameter == 1) {
//            return -1;
//        } else {
//            return 64;
//        }
//    }

//    void fireWhileMove(int a, int b, int c) throws Exception {
//        if (bf.scanQuadrant(Integer.parseInt(af.getQuadrant(getX() + a * c * analizeNextQuadrant(moveAnalize(b, getX())),
//                getY() + (1 - a) * c * analizeNextQuadrant(moveAnalize(b, getY()))).substring(0,1)),
//                Integer.parseInt(af.getQuadrant(getX() + a * c * analizeNextQuadrant(moveAnalize(b, getX())),
//                getY() + (1 - a) * c	* analizeNextQuadrant(moveAnalize(b, getY()))).substring(2))).equals("B"))
//        {
//            fire();
//        }
//    }

//    void clean() throws Exception {
//        fireWhileMove(1, 0, 0);
//        moveToQuadrant(1, 9);
//        for (int i = 0; i < 9; i++) {
//            int countB = 0;
//            for (int j = 0; j < 9; j++) {
//                if (bf.scanQuadrant(j, i).equals("B")) {
//                    countB += 1;
//                }
//            }
//            if (countB > 0 && i > 0) {
//                if (bf.scanQuadrant(8,i).equals("B")) {
//                    countB -= 1;
//                }
//                moveToQuadrant(i + 1, 9);
//                turn(direction.getInstanse(1));
//            }
//            while (countB > 0) {
//                fire();
//                countB--;
//            }
//        }
//        turn(direction.getInstanse(1));
//    }

    public void destroy(){
        updateX(-1000);
        updateY(-1000);
//        af.repaint();
    }

    public void draw(Graphics g){
        g.setColor(new Color(255, 0, 0));
        g.fillRect(this.getX(), this.getY(), 64, 64);

        g.setColor(new Color(0, 255, 0));
        if (this.getDirection().getDirection() == 1) {
            g.fillRect(this.getX() + 20, this.getY(), 24, 34);
        } else if (this.getDirection().getDirection() == 2) {
            g.fillRect(this.getX() + 20, this.getY() + 30, 24, 34);
        } else if (this.getDirection().getDirection() == 3) {
            g.fillRect(this.getX(), this.getY() + 20, 34, 24);
        } else {
            g.fillRect(this.getX() + 30, this.getY() + 20, 34, 24);
        }
    }


    public Action eagleDestroyAlgoritm(){
        if(readyToDestroyEagle(this)==true){
            if (readyToShoot(this)==true) {
                if(isAimed()==true){
                    return Action.FIRE;
                }
                else{
                    return aimAtEagle();
                }
            }
            else {
                if(movePossibility(getPreviousAction())==true){
                    return getPreviousAction();
                }
                else{
                    if(nextIsBrick(getPreviousAction())==true){
                        return Action.FIRE;
                    }
                    else {
                        return randomMove();
                    }
                }
            }
        }
        else {
            if (movePossibility(getPreviousAction()) == true) {
                return getPreviousAction();
            } else {
                if (nextIsBrick(getPreviousAction()) == true) {
                    return Action.FIRE;
                } else {
                    return randomMove();
                }
            }
        }
    }

    private boolean readyToDestroyEagle(AbstractTank tank){
        if(tank.getX()==256 || tank.getY()==512){
            return true;
        }
        else{
            return  false;
        }
    }

    private boolean readyToShoot(AbstractTank tank){
        if(tank.getX()==256){
            for (int i = tank.getY()/64; i<9; i++){
                if (tank.getBf().getBattleField()[i][4] instanceof Rock){
                    return false;
                }
            }
            return true;
        }
        if(tank.getY()==512){
            if (tank.getX()>256){
                for(int i = tank.getX()/64; i>4; i-- ) {
                    if(tank.getBf().getBattleField()[8][i] instanceof Rock){
                        return false;
                    }
                }
                return true;
            }
            else{
                for(int i = tank.getX()/64; i<5; i++ ) {
                    if(tank.getBf().getBattleField()[8][i] instanceof Rock){
                        return false;
                    }
                }
                return true;

            }
        }
        return false;
    }

    private Action randomMove(){
        setPreviousAction(MOVES[(int)(Math.random() * 4)]);
        return Action.NONE;
    }

    private boolean movePossibility(Action a){
        if(a.equals(Action.MOVE_UP)){
            if (this.getY()==0 || this.getBf().getBattleField()[(this.getY()/64)-1][this.getX()/64] instanceof Brick ||
                    this.getBf().getBattleField()[(this.getY()/64)-1][this.getX()/64] instanceof Water ||
                    this.getBf().getBattleField()[(this.getY()/64)-1][this.getX()/64] instanceof Rock) {
                return false;
            }
            else { return true;}
        }
        else if(a.equals(Action.MOVE_DOWN)){
            if (this.getY()==512 || this.getBf().getBattleField()[(this.getY()/64)+1][this.getX()/64] instanceof Brick ||
                    this.getBf().getBattleField()[(this.getY()/64)+1][this.getX()/64] instanceof Water ||
                    this.getBf().getBattleField()[(this.getY()/64)+1][this.getX()/64] instanceof Rock) {
                return false;
            }
            else { return true;}
        }
        else if(a.equals(Action.MOVE_LEFT)){
            if (this.getX()==0 || this.getBf().getBattleField()[this.getY()/64][(this.getX()/64)-1] instanceof Brick ||
                    this.getBf().getBattleField()[this.getY()/64][(this.getX()/64)-1] instanceof Water ||
                    this.getBf().getBattleField()[this.getY()/64][(this.getX()/64)-1] instanceof Rock) {
                return false;
            }
            else { return true;}
        }
        else if(a.equals(Action.MOVE_RIGHT)){
            if (this.getX()==512 || this.getBf().getBattleField()[this.getY()/64][(this.getX()/64)+1] instanceof Brick ||
                    this.getBf().getBattleField()[this.getY()/64][(this.getX()/64)+1] instanceof Water ||
                    this.getBf().getBattleField()[this.getY()/64][(this.getX()/64)+1] instanceof Rock) {
                return false;
            }
            else { return true;}
        }
        else{
            return false;
        }
    }

    private boolean nextIsBrick(Action a){
        if(a.equals(Action.MOVE_UP) && this.getY()>0 &&
                this.getBf().getBattleField()[(this.getY()/64)-1][this.getX()/64] instanceof Brick){
            return true;}
        else if(a.equals(Action.MOVE_DOWN) && this.getY()<512 &&
                this.getBf().getBattleField()[(this.getY()/64)+1][this.getX()/64] instanceof Brick){
            return true;
        }
        else if(a.equals(Action.MOVE_LEFT) && this.getX()>0 &&
                this.getBf().getBattleField()[this.getY()/64][(this.getX()/64)-1] instanceof Brick) {
            return true;
        }
        else if(a.equals(Action.MOVE_RIGHT) && this.getX()<512 &&
                this.getBf().getBattleField()[this.getY()/64][(this.getX()/64)+1] instanceof Brick) {
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isAimed(){
        if((this.getX()==256 && this.getDirection().equals(Direction.DOWN)) ||
                (this.getY()==512 && this.getX()<256 && this.getDirection().equals(Direction.RIGHT)) ||
                (this.getY()==512 && this.getX()>256 && this.getDirection().equals(Direction.LEFT))){
            return true;
        }
        return false;
    }

    private Action aimAtEagle(){
        if(this.getX()==256 && !(this.getDirection().equals(Direction.DOWN))){
            return Action.TURN_DOWN;
        }
        else if(this.getY()==512 && this.getX()>256 && this.getDirection().equals(Direction.DOWN)){
            return Action.TURN_LEFT;
        }
        else{
            return Action.TURN_RIGHT;
        }
    }

}


