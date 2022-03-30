import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Rainstorm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rainstorm extends Effect
{
    private int maxShift, xShift, yShift, midX, midY;
    private boolean rainstormAddedToMyWorld = false;
    
    public Rainstorm () {
        super (300);
        maxShift = 50;
        //System.out.println("Rainstorm added");
    }
    
    public void addedToWorld () {
        //System.out.println("World class is " + getWorld().getClass().getName());
        image = drawRainStorm(getWorld().getWidth() + 100, getWorld().getHeight() + 100, 100);
        setImage(image);
        ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) getWorld().getObjects(Vehicle.class);
        for (Vehicle v : vehicles) {
            //System.out.println("I am slowing down: " + v.getClass().getName());
            v.slip(300);
        }
        rainstormAddedToMyWorld = true;
        /*        midX = getX();
        midY = getY();*/
    }
    
    public void act()
    {
        super.act();   
        if (!rainstormAddedToMyWorld) {
            addedToWorld();
        }
                
    }
    
    public static GreenfootImage drawRainStorm (int width, int height, int severity){

        Color[] c = new Color [32];
        int red = 140;
        for (int i = 0; i < c.length; i++){
            c[i] = new Color (red, 150, 150);
            red+=2;
        }

        GreenfootImage temp = new GreenfootImage (width, height);
        // Run this loop one time per "severity"
        for (int i = 0; i < severity; i++){
            for (int j = 0; j < 100; j++){ // draw 100 dots

                // Choose a random colour from my swatch, and set its tranparency randomly
                int randColor = Greenfoot.getRandomNumber(32);
                int randTrans = Greenfoot.getRandomNumber(50) + 105; // almost opaque for dots
                //temp.setColor (swatch[randColor]);
                temp.setColor(Color.BLUE);
                temp.setTransparency(randTrans);
                // random locations for our dot
                int randX = Greenfoot.getRandomNumber (width);
                int randY = Greenfoot.getRandomNumber (height);
                temp.drawLine (randX, randY, randX, randY - 10); // silly way to draw a dot..
            }
        }
        for (int i = 0; i < severity; i++){
            for (int j = 0; j < 100; j++){ // draw 100 circles

                // Choose a random colour from my c, and set its tranparency randomly
                int randColor = Greenfoot.getRandomNumber(30);
                int randTrans = Greenfoot.getRandomNumber(50) + 70; // around half transparent
                temp.setColor (c[randColor]);
                temp.setTransparency(randTrans);
                // random locations for our dot
                int randX = Greenfoot.getRandomNumber (width);
                int randY = Greenfoot.getRandomNumber (height);
                int randSize = Greenfoot.getRandomNumber (5) + 1;
                temp.fillOval (randX, randY, randSize, randSize); // silly way to draw a dot..
            }
        }

        return temp;
    }
}
