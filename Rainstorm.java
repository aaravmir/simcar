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
    
    public Rainstorm () {
        super (240);
        maxShift = 50;
    }
    
    public void addedToWorld (World w) {
        image = drawRainStorm(w.getWidth() + 100, w. getHeight() + 100, 100);
        setImage(image);
        ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) w.getObjects(Vehicle.class);
        for (Vehicle v : vehicles) {
            v.slowMeDown(240);
        }
        midX = getX();
        midY = getY();
    }
    
    public void act()
    {
        super.act();
    }
    
    public static GreenfootImage drawRainStorm (int width, int height, int severity){

        Color[] swatch = new Color [32];
        int red = 128;
        for (int i = 0; i < swatch.length; i++){
            swatch[i] = new Color (red, 240, 255);
            red+=2;
        }

        GreenfootImage temp = new GreenfootImage (width, height);
        // Run this loop one time per "severity"
        for (int i = 0; i < severity; i++){
            for (int j = 0; j < 100; j++){ // draw 100 dots

                // Choose a random colour from my swatch, and set its tranparency randomly
                int randColor = Greenfoot.getRandomNumber(32);
                int randTrans = Greenfoot.getRandomNumber(50) + 205; // almost opaque for dots
                //temp.setColor (swatch[randColor]);
                temp.setColor(Color.WHITE);
                temp.setTransparency(randTrans);
                // random locations for our dot
                int randX = Greenfoot.getRandomNumber (width);
                int randY = Greenfoot.getRandomNumber (height);
                temp.drawLine (randX, randY, randX, randY); // silly way to draw a dot..
            }
        }
        for (int i = 0; i < severity; i++){
            for (int j = 0; j < 100; j++){ // draw 100 circles

                // Choose a random colour from my swatch, and set its tranparency randomly
                int randColor = Greenfoot.getRandomNumber(32);
                int randTrans = Greenfoot.getRandomNumber(50) + 90; // around half transparent
                temp.setColor (swatch[randColor]);
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
