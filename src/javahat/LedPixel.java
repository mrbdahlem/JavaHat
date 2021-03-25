package javahat;

import com.diozero.ws281xj.LedDriverInterface;

/**
 *
 * @author dahlem.brian
 */
public class LedPixel implements Pixel {
    private final LedDriverInterface driver;
    private final int pixelNum;
    
    public LedPixel(LedDriverInterface driver, int pixelNum) {
        this.driver = driver;
        this.pixelNum = pixelNum;
    }
    
    @Override
    public int getRed() {
        return driver.getRedComponent(pixelNum);
    }
    
    @Override
    public int getGreen() {
        return driver.getGreenComponent(pixelNum);
    }
    
    @Override
    public int getBlue() {
        return driver.getBlueComponent(pixelNum);
    }

    @Override
    public void setRed(int val) {
        driver.setRedComponent(pixelNum, val);
    }
        
    @Override
    public void setGreen(int val) {
        driver.setGreenComponent(pixelNum, val);
    }

    @Override
    public void setBlue(int val) {
        driver.setBlueComponent(pixelNum, val);
    }

    @Override
    public int intValue() {
        return driver.getPixelColour(pixelNum);
    }

    @Override
    public void setIntValue(int val) {
        driver.setPixelColour(pixelNum, val);
    }
}
