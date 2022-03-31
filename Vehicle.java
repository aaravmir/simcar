import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the superclass for Vehicles.
 * 
 */
public abstract class Vehicle extends SuperSmoothMover
{
    protected double maxSpeed;
    protected double speed;
    protected int direction; // 1 = right, -1 = left
    protected boolean moving;
    protected int yOffset;
    protected VehicleSpawner origin;
    protected boolean isRaining = false;
    protected boolean isFastEnough;
    public static String INNER_LANE = "INNER";
    public static String MIDDLE_LANE = "MIDDLE";
    public static String OUTER_LANE = "OUTER";

    protected abstract boolean checkHitPedestrian ();

    public Vehicle (VehicleSpawner origin) {
        this.origin = origin;
        moving = true;

        if (origin.facesRightward()){
            direction = 1;
        } else {
            direction = -1;
            getImage().mirrorHorizontally();
        }
    }

    protected boolean canMoveRight() {
        Vehicle toRightGoingRight = (Vehicle) getOneObjectAtOffset(0, direction * (int)(speed + getImage().getWidth()/2 + 10), Vehicle.class);
        if (toRightGoingRight != null) {
            Vehicle toRightGoingLeft = (Vehicle) getOneObjectAtOffset(0, direction * (int)(speed + getImage().getWidth()/2 - 10), Vehicle.class);
            return toRightGoingLeft == null;                    
        } else {
            return true;
        }
    }

    protected boolean canMoveLeft() {
        Vehicle toLeftGoingRight = (Vehicle) getOneObjectAtOffset(0, direction * (int)(speed + getImage().getWidth()/2 - 10), Vehicle.class);
        if(toLeftGoingRight != null){
            Vehicle toLeftGoingLeft = (Vehicle) getOneObjectAtOffset(0, direction * (int)(speed + getImage().getWidth()/2 + 10), Vehicle.class);
            return toLeftGoingLeft == null;
        } else {
            return true;
        }
    }

    protected String getLane() {        
        if((getY() > 249 && getY() < 253) || (getY() > 522 && getY() < 526)) {
            return OUTER_LANE;            
        } else if((getY() > 304 && getY() < 308) || (getY() > 466 && getY() < 471)) {
            return MIDDLE_LANE;
        } else {
            return INNER_LANE;
        }
    }

    protected boolean checkIfFastEnough()
    {
        Vehicle ahead = (Vehicle) getOneObjectAtOffset (direction * (int)(speed + getImage().getWidth()/2 + 4), 0, Vehicle.class);
        return speed >= ahead.getSpeed();
    }

    public void addedToWorld (World w){
        setLocation (origin.getX() - (direction * 100), origin.getY() - yOffset);
    }

    /**
     * A method used by all Vehicles to check if they are at the edg
     */
    protected boolean checkEdge() {
        if (direction == 1){
            return getX() > getWorld().getWidth() + 200;
        } else {
            return getX() < -200;
        }
    }

    /**
     * Method that deals with movement. Speed can be set by individual subclasses in their constructors
     */
    public void drive() 
    {
        // Ahead is a generic vehicle - we don't know what type BUT
        // since every Vehicle "promises" to have a getSpeed() method,
        // we can call that on any vehicle to find out it's speed
        Vehicle ahead = (Vehicle) getOneObjectAtOffset (direction * (int)(speed + getImage().getWidth()/2 + 4), 0, Vehicle.class);
        if (ahead == null)
        {
            speed = maxSpeed;
        } else {
            speed = ahead.getSpeed();
        }
        speed = isRaining ? speed/3 : speed;
        move(speed * direction);
    }   

    /**
     * An accessor that can be used to get this Vehicle's speed. Used, for example, when a vehicle wants to see
     * if a faster vehicle is ahead in the lane.
     */
    public double getSpeed() {
        return speed;
    }

    public void slip(int n) {
        isRaining = true;
    }

    public void returnToNormalSpeed() {
        isRaining = false;
    }
}
