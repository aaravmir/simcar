import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bear here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bear extends SuperSmoothMover
{
    GreenfootSound growl;
    
    private double speed;
    private double maxSpeed;
    private int direction;
    private boolean awake;
    private int actsUntilPass;
    private int passCount;
    private int passCounter;

    public Bear (int direction) {
        // choose a random speed
        maxSpeed = Math.random() * 1.5 + 1;
        speed = maxSpeed;
        // start as awake 
        awake = true;
        this.direction = direction;
        actsUntilPass = 300;
        growl = new GreenfootSound("beargrowl.mp3");
        growl.setVolume(0);
        growl.play();
        growl.stop();
    }
    
    /**
     * Act - do whatever the Pedestrian wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (awake){
            maul();            
        }
        //say how if its put in the "if awake" the bear doesnt actually kill them
        if (getOneObjectAtOffset(0, (int)(direction * getImage().getHeight()/2 + (int)(direction * speed)), Pedestrian.class) == null){
            setLocation (getX(), getY() + (int)(speed*direction));
        }
        if (direction == -1 && getY() < 100){
            getWorld().removeObject(this);
        } else if (direction == 1 && getY() > 550){
            getWorld().removeObject(this);
        }
        if(isAwake() != true || speed == 0)
        {
            passAway();
        }
    }

    public boolean maul () {
        Pedestrian p = (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Pedestrian.class);
        if (p != null){
            p.knockDown();
            bearRoar();
            return true;  
        }
        return false;
    }
    
    public void bearRoar() {
        growl.setVolume(50);
        growl.play();
        growl.stop();
    }

    public boolean isAwake () {
        return awake;
    }

    public void sedated () {
        speed = 0;
        setRotation (90);
        awake = false;
    }
    
    public void passAway () {
        if (actsUntilPass > 0)
        {
            actsUntilPass--;
        }
        else
        {
            if (passCount == 0)
            {
                getWorld().removeObject(this);
            }
        }
    }   
}
