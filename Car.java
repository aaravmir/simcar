import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Car subclass
 */
public class Car extends Vehicle
{
    GreenfootSound carHorn;
    private boolean copIsNear = false;
    public Car(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        maxSpeed = 1.5 + ((Math.random() * 30)/5);
        speed = maxSpeed;
        yOffset = 0;
        carHorn = new GreenfootSound("carHonk.mp3");
        carHorn.setVolume(0);
        carHorn.play();
        carHorn.stop();
        
    }

    public void act()
    {
        drive(); 
        killedSomeone();
        checkHitPedestrian();
        if (checkEdge()){
            getWorld().removeObject(this);
        }
    }
    
    public void drive() {
        Vehicle ahead = (Vehicle) getOneObjectAtOffset (direction * (int)(speed + getImage().getWidth()/2 + 4), 0, Vehicle.class);
        if (ahead == null)
        {
            speed = maxSpeed;
        } else {
            speed = ahead.getSpeed();
        }
        speed = copIsNear ? speed/2 : speed;
        move(speed * direction);
    }
    
    /**
     * When a Car hit's a Pedestrian, it should knock it over
     */
    public boolean checkHitPedestrian () {
        Pedestrian p = (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Pedestrian.class);
        
        if (p != null){
            p.knockDown();
            honk();
            return true;
        }
        return false;
    }
    
    public void honk() {
        carHorn.setVolume(50);
        carHorn.play();
    }
    
    public void slowMeDown(int n) {
        copIsNear = true;
    }
    public boolean killedSomeone () {
        if(checkHitPedestrian() == true){
            return true;
        }
        return false;
    }
}
