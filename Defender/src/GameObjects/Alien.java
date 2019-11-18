package GameObjects;
import UserInterface.MyApplication;
//import com.sun.javafx.scene.traversal.Direction;


import java.util.Random;

public class Alien extends GameObject {
    protected double moveConstTime = 20;
    private moveDirection direction;

    int points;

    public Alien(){
        super((450 - (int)(Math.random() * 200)), (int)(Math.random() * 400));
        setSpeed(2);
        move();
    }
    public Alien(int x,int  y){
        super(x,y);
        setSpeed(2);
        move();

    }
    public int getScore() {
        return points;
    }

    private int counter = 0;
    Random rand = new Random();
    private int i = rand.nextInt(4);
    public void move(){
        if (counter >= 15){
            counter = 0;
            i = rand.nextInt(4);
        }
        else {
            counter += 1;
            if (i == 0) {
                direction = moveDirection.RIGHT;
            }
            if (i == 1) {
                direction = moveDirection.LEFT;
            }
            if (i == 2) {
                direction = moveDirection.UP;
            }
            if (i == 3) {
                direction = moveDirection.DOWN;
            }
            switch (direction) {
                case UP:
                    setY(getY() - this.getSpeed());
                    break;
                case DOWN:
                    setY(getY() + this.getSpeed());
                    break;
                case LEFT:
                    setX(getX() - this.getSpeed());
                    direction = moveDirection.LEFT;
                    break;
                case RIGHT:
                    setX(getX() + this.getSpeed());
                    direction = moveDirection.RIGHT;
                    break;
            }

            if (this.getX() > 600) setX(20);
            if (this.getX() < 0)   setX(580);
            if (this.getY() > 500) setY(490);
            if (this.getY() < 0)   setY(10);
        }
    }

    private int fireCounter = 0;
    public Projectile fire(){
        if (fireCounter >= 30){
            fireCounter = 0;
            Coordinate shipCo = MotherShip.getInstance().getCoordinates();
            if (this.getX() < shipCo.getX()){
                int x = this.getX();
                int y = this.getY();
                Coordinate ship_Coordinate = new Coordinate(x,y);
                moveDirection newDirection = moveDirection.RIGHT;
                return new AlienProjectile(ship_Coordinate, newDirection);
            }
            else{
                int x = this.getX();
                int y = this.getY();
                Coordinate ship_Coordinate = new Coordinate(x,y);
                moveDirection newDirection = moveDirection.LEFT;
                return new AlienProjectile(ship_Coordinate, newDirection);
            }
        }
        else {
            fireCounter++;
            return null;
        }
    }
}
