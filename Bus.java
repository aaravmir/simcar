import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Bus subclass
 */
public class Bus extends Vehicle
{
    public Bus(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first
        
        //Set up values for Bus
        maxSpeed = 1.5 + ((Math.random() * 10)/5);
        speed = maxSpeed;
        // because the Bus graphic is tall, offset it a up (this may result in some collision check issues)
        yOffset = 15;
    }

    /**
     * Act - do whatever the Bus wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        drive();
        pickUpPolitician();
        if (checkEdge()){
            getWorld().removeObject(this);
        }
        
    }
    

    public boolean checkHitPedestrian () {
        return false;
    }
    
    public boolean pickUpPolitician () {
        int halfMyWidth = getImage().getWidth()/2;
        int halfMyHeight = getImage().getHeight()/2;
        // right edge, mid point
        Politician p = (Politician)getOneObjectAtOffset((int)((speed + halfMyWidth)*direction), 0, Politician.class);
        // top right corner
        Politician p2 = (Politician)getOneObjectAtOffset((int)((speed + halfMyWidth)*direction), -halfMyHeight, Politician.class);
        // top left corner
        Politician p3 = (Politician)getOneObjectAtOffset((int)((speed + halfMyWidth)*direction), halfMyHeight, Politician.class);

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
}
