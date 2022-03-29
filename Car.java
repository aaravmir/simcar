import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Car subclass
 */
public class Car extends Vehicle
{
    GreenfootSound carHorn;
    GreenfootSound longerHorn;
    
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

    public boolean killedSomeone () {
        if(checkHitPedestrian() == true){
            return true;
        }
        return false;
    }
}
