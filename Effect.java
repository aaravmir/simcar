import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Effect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Effect extends Actor
{
    protected int totalActs, actCounter;
    protected GreenfootImage image;

    public Effect (int totalActs) {
        this.totalActs = totalActs;
        actCounter = totalActs;
    }
    
    

    public void act () 
    {
        if(actCounter > 0) {
            if (this instanceof Rainstorm) {
                //System.out.print(actCounter + ", ");
            }
            actCounter--;
            if (actCounter < 60) {
                image.setTransparency (actCounter * 2);
            } 
            if (actCounter == 0)
            {
                if (this instanceof Rainstorm) {
                    //System.out.println("Removing Rainstorm when actCounter = " + actCounter);
                    ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) getWorld().getObjects(Vehicle.class);
                    for (Vehicle v : vehicles) {
                        v.returnToNormalSpeed();
                    }                    
                }
                getWorld().removeObject(this);
            }
        }
    }
}
