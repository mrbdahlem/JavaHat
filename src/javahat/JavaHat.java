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
    /** The pin the NeoPixels are connected to */
    public static final int PIXELS_GPIO = 12;
    /** The number of NeoPixels on the hat */
    public static final int NUM_PIXELS = 10;
    
    public static final int DPAD_UP_GPIO = 6;
    public static final int DPAD_DOWN_GPIO = 26;
    public static final int DPAD_LEFT_GPIO = 20;
    public static final int DPAD_RIGHT_GPIO = 13;
    public static final int DPAD_CENTER_GPIO = 16;
    
    public static final int A_BUTTON_GPIO = 24;
    public static final int B_BUTTON_GPIO = 23;
    
    /* Settings */
    private static final int PIXEL_BRIGHTNESS = 64;

    /* Instance variables */
    private final Pixel[] neoPixels;
    private final LedDriverInterface neoPixelDriver;
    private final ButtonPad bpad;
    
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
        
        bpad = new ButtonPad(DPAD_UP_GPIO, DPAD_DOWN_GPIO, DPAD_LEFT_GPIO, 
                DPAD_RIGHT_GPIO, DPAD_CENTER_GPIO, A_BUTTON_GPIO, B_BUTTON_GPIO);
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
     * Turn off the NeoPixel LEDs, setting their RGB values to 0. The neoPixels
     * do not wait for updatePixels() to be called.
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
     * Retrieve the instance of the controller on the hat
     * @return the controller on the hat
     */
    public ButtonPad getButtonPad() {
        return bpad;
    }

    /**
     * Demonstrate the RGB neopixel LEDS
     * 
     * @param hat the physical hardware
     */
    private static void pixelTest(JavaHat hat) {
        System.out.println("Rainbow Colors");

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
        
        // Demo the ButtonPad
        
        ButtonPad controller = hat.getButtonPad();
        Pixel[] pixels = hat.getNeoPixels();
        hat.neoPixelsOff();
        
        System.out.println("Button test: (Press A + B to end)");
        boolean endPressed = false;
        
        while (!endPressed) {
            if (controller.upPressed()) {
                pixels[0].setRed(255);
            }
            else {
                pixels[0].setRed(0);
            }
            
            if (controller.downPressed()) {
                pixels[1].setGreen(255);
            }
            else {
                pixels[1].setGreen(0);
            }
            
            if (controller.leftPressed()) {
                pixels[2].setBlue(255);
            }
            else {
                pixels[2].setBlue(0);
            }
            
            if (controller.rightPressed()) {
                pixels[3].setRed(255);
                pixels[3].setGreen(255);
            }
            else {
                pixels[3].setRed(0);
                pixels[3].setGreen(0);
            }
            
            if (controller.centerPressed()) {
                pixels[4].setGreen(255);
                pixels[4].setBlue(255);
            }
            else {
                pixels[4].setGreen(0);
                pixels[4].setBlue(0);
            }
            
            if (controller.aPressed()) {
                pixels[5].setRed(255);
                pixels[5].setBlue(255);
            }
            else {
                pixels[5].setRed(0);
                pixels[5].setBlue(0);
            }
            
            if (controller.bPressed()) {
                pixels[6].setRed(64);
                pixels[6].setBlue(64);
                pixels[6].setGreen(64);
            }
            else {
                pixels[6].setRed(0);
                pixels[6].setBlue(0);
                pixels[6].setGreen(0);
            }
            
            if (controller.aPressed() && controller.bPressed()) {
                endPressed = true;
            }
            
            hat.updatePixels();
        }
        
        System.out.println("Button test done.");
    }
}
