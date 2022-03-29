import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Crook here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Crook extends Pedestrian
{
    /**
     * Act - do whatever the Crook wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private Politician targetPolitician;
    private ArrayList<Politician> politicians;
    
    public Crook(int direction) {
        super(direction);
    }
    
    public void act()
    {
        // If there is a v
        if (awake){
            if (getOneObjectAtOffset(0, (int)(direction * getImage().getHeight()/2 + (int)(direction * speed)), Vehicle.class) == null){
                setLocation (getX(), getY() + (int)(speed*direction));
            }
            if (direction == -1 && getY() < 100){
                getWorld().removeObject(this);
            } else if (direction == 1 && getY() > 550){
                getWorld().removeObject(this);
            }
        }
    }
    
    public void targetPolitician() {
        double closestTargetDistance = 0;
        double distanceToPolitician;
        int numPoliticians;
        
        if (politicians.size() > 0)
        {
            // set the first one as my target
            targetPolitician = politicians.get(0);
            // Use method to get distance to target. This will be used
            // to check if any other targets are closer
            closestTargetDistance = VehicleWorld.getDistance (this, targetPolitician);

            // Loop through the objects in the ArrayList to find the closest target
            for (Politician o : politicians)
            {
                // Cast for use in generic method
                //Actor a = (Actor) o;
                // Measure distance from me
                distanceToPolitician = VehicleWorld.getDistance(this, o);
                // If I find a Flower closer than my current target, I will change
                // targets
                if (distanceToPolitician < closestTargetDistance)
                {
                    targetPolitician = o;
                    closestTargetDistance = distanceToPolitician;
                }
            }
        }
    }
    
    public void killPolitician ()
    {
        turnTowards(targetPolitician.getX(), targetPolitician.getY());

        
        if (this.getNeighbours (30, true, Politician.class).size() > 0)
        {
            // If I was able to eat, increase by life by flower's nibble power
            int tryToKill = targetPolitician.kill();
            if (tryToKill > 0 && hp < maxHP)
            {
                hp += tryToEat;
            }
        }
        else
        {
            move (mySpeed);
        }
    }
}
