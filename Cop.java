import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Car subclass
 */
public class Cop extends Vehicle
{
    
    public Cop(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        maxSpeed = 1 + ((Math.random() * 20)/5);
        speed = maxSpeed;
        yOffset = 0;
    }

    public void act()
    {
        drive(); 
        makeArrest();
        if (checkEdge()){
            getWorld().removeObject(this);
        }
    }
    
    /**
     * When a Cop hits a car who previously killed someone arrest them
     */
    public boolean makeArrest () {
        Car c = (Car)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Car.class);
        
        if (c != null && c.killedSomeone() == true){
            getWorld().removeObject(c);
            return true;
        }
        return false;
    }
    
        public boolean checkHitPedestrian () {
        return false;
    }
}

