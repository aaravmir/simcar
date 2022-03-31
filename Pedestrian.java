import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Pedestrian that tries to walk across the street
 */
public abstract class Pedestrian extends SuperSmoothMover
{
    protected double speed;
    protected double maxSpeed;
    protected int direction;
    protected boolean awake;
    protected int actsUntilPass;
    protected int passCount;
    protected int passCounter;
    
    public Pedestrian(int direction) {
        // choose a random speed
        maxSpeed = Math.random() * 2 + 1;
        speed = maxSpeed;
        // start as awake 
        awake = true;
        this.direction = direction;
        actsUntilPass = 300;
    }
    
    
    /**
     * Act - do whatever the Pedestrian wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
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
          
        if(isAwake() != true && getY() > 50 && getY() < 200)
        {
            passAway();
        }
    }

    /**
     * Method to cause this Pedestrian to become knocked down - stop moving, turn onto side
     */
    public void knockDown () {
        speed = 0;
        setRotation (90);
        getWorld().addObject(new Blood(), getX(), getY());
        awake = false;
    }

    /**
     * Method to allow a downed Pedestrian to be healed
     */
    public void healMe () {
        speed = maxSpeed;
        setRotation (0);
        awake = true;
    }
    
    public void pickUp () {
        getWorld().removeObject(this);
    }
    
    public boolean isAwake () {
        return awake;
    }
    
    protected void passAway () {
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
