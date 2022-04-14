import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  PImage background;

  float fltDvdX = random(0, 1000);
  float fltDvdY = random(0, 800);

  // pixels that x and y values increase/decrease by
  float fltSpeedX = 5;
  float fltSpeedY = 5;
	
  PImage [] dvdColours = new PImage[5];
  int numImages = 5; 

  int randNum;
  int previousColour;

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(1000, 800);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {

    // load images and add them as elements to an array
    dvdColours[0] = loadImage("Blue.png"); // img dimensions = 447 x 200
    dvdColours[1] = loadImage("Orange.png");
    dvdColours[2] = loadImage("Yellow.png");
    dvdColours[3] = loadImage("Purple.png");
    dvdColours[4] = loadImage("Cyan.png");
    background = loadImage("Black background.png");

    // randomize the starting dvd logo colour
    randColour();
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {

    // draw black background
    image(background, 0, 0);

    // randomly select from a list of dvd colours
    image(dvdColours[randNum], fltDvdX, fltDvdY);
    
    // animate dvd logo
    fltDvdX+=fltSpeedX;
    fltDvdY+=fltSpeedY;

    // if dvd logo touches horizontal edge of screen, set X animation motion to be reversed
    if (fltDvdX >= 553) {
      fltSpeedX = -fltSpeedX;
      fltDvdX = 553; 
      randColour();
      noDuplicates();
      
    } 

    // if dvd logo's is less than or equal to position of x = 0, change X animation motion, and set it in the opposite direction
    else if (fltDvdX <= 0) {
      fltSpeedX = -fltSpeedX;
      fltDvdX = 0;
      randColour();
      noDuplicates();
    }

    if (fltDvdY >= 600) {
      fltSpeedY = -fltSpeedY;
      fltDvdY = 600;
      randColour();
      noDuplicates();
    }
    
    else if (fltDvdY <= 0) {
      fltSpeedY = -fltSpeedY;
      fltDvdY = 0;
      randColour();
      noDuplicates();
    }
  }
  
  // define other methods down here.

  /**
   * Generates a random number depending on the number of images using numImages variable
   * 
   * @param: N/A
   * @return: N/A
   * @author: A. Chan
   */
  public void randColour() {
    randNum = (int) random(numImages);
  }

  /**
   * Checks to see if a number is repeated twice, if a number is repeated twice, generate a new number until condition is false. Prevents repeated colours when logo collides with edge of screen.
   * 
   * @param: N/A
   * @return: returns the new generated number and stores it in the previousColour variable 
   * @author: A. Chan
   */
  public void noDuplicates() {
    while (randNum == previousColour) {
      randColour();
    }
    previousColour = randNum;
  }
}