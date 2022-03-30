import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * The Car subclass
 */
public class Cop extends Vehicle
{
    private int size = 400;
    public Cop(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        maxSpeed = 1 + ((Math.random() * 20)/5);
        speed = maxSpeed;
        yOffset = 0;
    }

    public void act()
    {
        drive(); 
        ticket();
        arrest();
        if (getWorld() == null){
            return;
        }
        if (checkEdge()){
            getWorld().removeObject(this);
            return;
        }
        
        ArrayList<Car> cars = (ArrayList<Car>) getObjectsInRange((int)size, Car.class);
        for (Car c : cars) {
        c.slowMeDown(420);
        }
    }
        
    
    /**
     * When a Cop hits a car who previously killed someone arrest them
     */
    public boolean ticket () {
        Car c = (Car)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Car.class);
        
        if (c != null && c.killedSomeone() == true){
            getWorld().removeObject(c);
            return true;
        }
        return false;
    }
    
    public boolean arrest () {
        int halfMyWidth = getImage().getWidth()/2;
        int halfMyHeight = getImage().getHeight()/2;
        // right edge, mid point
        Crook p = (Crook)getOneObjectAtOffset((int)((speed + halfMyWidth)*direction), 0, Crook.class);
        // top right corner
        Crook p2 = (Crook)getOneObjectAtOffset((int)((speed + halfMyWidth)*direction), -halfMyHeight, Crook.class);
        // top left corner
        Crook p3 = (Crook)getOneObjectAtOffset((int)((speed + halfMyWidth)*direction), halfMyHeight, Crook.class);

        if (p != null && p.isAwake()){
            p.pickUp();
            speed = maxSpeed;
            return true;
        } else if (p2 != null && p2.isAwake()){
            p2.pickUp();
            speed = maxSpeed;
            return true;
        } else if (p3 != null && p3.isAwake()){
            p3.pickUp();
            speed = maxSpeed;
            return true;
        }
        return false;
    }
    
        public boolean checkHitPedestrian () {
        return false;
    }
}

