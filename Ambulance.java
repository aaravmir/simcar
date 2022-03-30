import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Ambulance subclass
 */
public class Ambulance extends Vehicle
{
    public Ambulance(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first

        maxSpeed = 2.5;
        speed = maxSpeed;
    }

    /**
     * Act - do whatever the Ambulance wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        drive();
        checkHitPedestrian();        
        tranquilize();
    }

    public boolean checkHitPedestrian () {
        int halfMyWidth = getImage().getWidth()/2;
        int halfMyHeight = getImage().getHeight()/2;
        // right edge, mid point
        Pedestrian p = (Pedestrian)getOneObjectAtOffset((int)((speed + halfMyWidth)*direction), 0, Pedestrian.class);
        // top right corner
        Pedestrian p2 = (Pedestrian)getOneObjectAtOffset((int)((speed + halfMyWidth)*direction), -halfMyHeight, Pedestrian.class);
        // top left corner
        Pedestrian p3 = (Pedestrian)getOneObjectAtOffset((int)((speed + halfMyWidth)*direction), halfMyHeight, Pedestrian.class);

        if (p != null){
            p.healMe();
            return true;
        } else if (p2 != null){
            p2.healMe();
            return true;
        } else if (p3 != null){
            p3.healMe();
            return true;
        }
        return false;
    }
    
    public boolean tranquilize () {
        int halfMyWidth = getImage().getWidth()/2;
        int halfMyHeight = getImage().getHeight()/2;
        // right edge, mid point
        Bear b = (Bear)getOneObjectAtOffset((int)((speed + halfMyWidth)*direction), 0, Bear.class);
        // top right corner
        Bear b2 = (Bear)getOneObjectAtOffset((int)((speed + halfMyWidth)*direction), -halfMyHeight, Bear.class);
        // top left corner
        Bear b3 = (Bear)getOneObjectAtOffset((int)((speed + halfMyWidth)*direction), halfMyHeight, Bear.class);

        if (b != null){
            b.sedated();
            return true;
        } else if (b2 != null){
            b2.sedated();
            return true;
        } else if (b3 != null){
            b3.sedated();
            return true;
        }
        return false;
    }
    
    public boolean takeToMorgue () {
        int halfMyWidth = getImage().getWidth()/2;
        int halfMyHeight = getImage().getHeight()/2;
        // right edge, mid point
        Crook c = (Crook)getOneObjectAtOffset((int)((speed + halfMyWidth)*direction), 0, Crook.class);
        // top right corner
        Crook c2 = (Crook)getOneObjectAtOffset((int)((speed + halfMyWidth)*direction), -halfMyHeight, Crook.class);
        // top left corner
        Crook c3 = (Crook)getOneObjectAtOffset((int)((speed + halfMyWidth)*direction), halfMyHeight, Crook.class);

        if (c.isAwake() == false){
            c.pickUp();
            speed = maxSpeed;
            return true;
        } else if (c2.isAwake() == false){
            c2.pickUp();
            speed = maxSpeed;
            return true;
        } else if (c3.isAwake() == false){
            c3.pickUp();
            speed = maxSpeed;
            return true;
        }
        return false;
    }
}
