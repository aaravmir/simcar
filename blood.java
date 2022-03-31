import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class blood here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Blood extends Effect
{
    public Blood() {
        super(300);
    }
    /**
     * Act - do whatever the blood wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(actCounter > 0) {
            if (this instanceof Blood) {
                //System.out.print(actCounter + ", ");
            }
            actCounter--;
            if (actCounter == 0)
            {
                getWorld().removeObject(this);
            }
        }
    }    
}
