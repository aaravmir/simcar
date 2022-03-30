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
    GreenfootSound gunShot;
    private Politician targetPolitician;
    
    public Crook(int direction) {
        super(direction);
        gunShot = new GreenfootSound("gunshot.mp3");
        gunShot.setVolume(0);
        gunShot.play();
        gunShot.stop();
        maxSpeed = Math.random() * 2 - 2;
    }
    
    public void act()
    {
        if(awake){
            targetPolitician();
        }
        if (getOneObjectAtOffset(0, (int)(direction * getImage().getHeight()/2 + (int)(direction * speed)), Vehicle.class) == null){
            setLocation (getX(), getY() + (int)(speed*direction));
        }
        if (direction == -1 && getY() < 100){
            getWorld().removeObject(this);
        } else if (direction == 1 && getY() > 550){
            getWorld().removeObject(this);
        }
    }
    
   /* public boolean shootPolitician () {
        Politician p = (Politician)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Politician.class);
        if (p != null){
            p.knockDown();
            shootingSound();
            return true;  
        }
        return false;
    }*/
    
    public void shootingSound() {
        gunShot.setVolume(35);
        gunShot.play();
    }
    
    public void targetPolitician() {
        double closestTargetDistance = 0;
        double distanceToPolitician;
        
        ArrayList<Politician> politicians = (ArrayList<Politician>)getObjectsInRange(100, Politician.class);
        ArrayList<Politician> removeList = new ArrayList<Politician>();
        for(Politician p : politicians) {
            if(!p.isAwake()){
                removeList.add(p);
            }
        }
        politicians.removeAll (removeList);
        if (!politicians.isEmpty())
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
        if (targetPolitician != null && getWorld() != null)
            shootPolitician();
    }
    
    public void shootPolitician ()
    {
        if (targetPolitician.getWorld() == null){
            targetPolitician = null;
            return;
        }
        turnTowards(targetPolitician.getX(), targetPolitician.getY());
        move(speed);
        
        Politician p = (Politician)getOneIntersectingObject(Politician.class);
        if(p != null){
            p.knockDown();
            shootingSound();

        }
        }
    }

