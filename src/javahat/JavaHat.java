package javahat;

import com.diozero.ws281xj.LedDriverInterface;
import com.diozero.ws281xj.PixelAnimations;
import com.diozero.ws281xj.PixelColour;
import com.diozero.ws281xj.rpiws281x.WS281x;

/**
 *
 * @author dahlem.brian
 */
public class JavaHat {
    
    /* I/O Constants */
    /** The pin the neopixels are connected to */
    public static final int PIXELS_GPIO = 12;
    /** The number of neopixels on the hat */
    public static final int NUM_PIXELS = 10;
    
    /* Settings */
    private static final int PIXEL_BRIGHTNESS = 64;

    /* Instance variables */
    private final Pixel[] neoPixels;
    private final LedDriverInterface neoPixelDriver;
    
    /*
     There is only one hat connection, so only allow a single instance
    */
    private static JavaHat instance;
    /**
     * Construct the one instance of the JavaHat API
     */
    private JavaHat() {
        neoPixelDriver = new WS281x(PIXELS_GPIO, PIXEL_BRIGHTNESS, NUM_PIXELS);
        
        neoPixels = new LedPixel[NUM_PIXELS];
        for (int i = 0; i < neoPixels.length; i++) {
            neoPixels[i] = new LedPixel(neoPixelDriver, i);
        }
    }
    
    /**
     * Retrieve a reference to the JavaHat API.
     * 
     * @return a reference to the single instance of the JavaHat 
     */
    public static JavaHat getHat() {
        if (instance == null) {
            instance = new JavaHat();
        }
        
        return instance;
    }
    
    /**
     * Turn off the NeoPixel LEDs, setting their RGB values to 0
     */
    public void neoPixelsOff() {
        neoPixelDriver.allOff();
    }
    
    /**
     * Retrieve the pixels that control the RGB NeoPixel LEDS
     * 
     * @return the array of NeoPixels
     */
    public Pixel[] getNeoPixels() {
       return neoPixels; 
    }
    
    /**
     * Update the NeoPixels with any modified colors since last updated
     */
    public void updatePixels() {
        neoPixelDriver.render();
    }

    /**
     * Demonstrate the RGB neopixel LEDS
     * 
     * @param hat the physical hardware
     */
    private static void pixelTest(JavaHat hat) {
        System.out.println("Rainbow Colours");

        int[] colours = PixelColour.RAINBOW;
        Pixel[] pixels = hat.getNeoPixels();

        for (int i = 0; i < 250; i++) {
            for (int pixel = 0; pixel < pixels.length; pixel++) {
                pixels[pixel].setIntValue(colours[(i + pixel) % colours.length]);
            }

            hat.updatePixels();
            PixelAnimations.delay(50);
        }
        
        System.out.println("Color Test");
        
        // Set all off
        hat.neoPixelsOff();
        
        int delay = 20;

        // Gradually add red
        System.out.println("Adding red...");
        for (int i = 0; i < 256; i += 2) {
            for (Pixel pixel : pixels) {
                pixel.setRed(i);
            }

            hat.updatePixels();
            PixelAnimations.delay(delay);
        }

        // Gradually add green
        System.out.println("Adding green...");
        for (int i = 0; i < 256; i += 2) {
            for (Pixel pixel : pixels) {
                pixel.setGreen(i);
            }

            hat.updatePixels();
            PixelAnimations.delay(delay);
        }

        // Gradually add blue
        System.out.println("Adding blue...");
        for (int i = 0; i < 256; i += 2) {
            for (Pixel pixel : pixels) {
                pixel.setBlue(i);
            }

            hat.updatePixels();
            PixelAnimations.delay(delay);
        }

        // Set all off
        hat.neoPixelsOff();
    }
    
    /**
     * Hardware demo
     * @param args ignored
     */
    public static void main(String[] args) {
        JavaHat hat = JavaHat.getHat(); // Get a connection to the hardware
        
        // Demo the neoPixels on the hat
        pixelTest(hat);
    }
}
