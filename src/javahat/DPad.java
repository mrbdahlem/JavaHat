/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javahat;

import com.diozero.api.DigitalInputDevice;
import com.diozero.api.GpioPullUpDown;

/**
 *
 * @author dahlem.brian
 */
public class DPad {
    private final DigitalInputDevice up;
    private final DigitalInputDevice down;
    private final DigitalInputDevice left;
    private final DigitalInputDevice right;
    private final DigitalInputDevice center;
    
    /** 
     * Construct a new DPad.
     * Note: the constructor is Package Private so only the hat constructor
     * can create an instance
     */
    DPad(int upGpio, int downGpio, int leftGpio, int rightGpio, int centerGpio) {
        up =  DigitalInputDevice.builder(upGpio).setPullUpDown(GpioPullUpDown.PULL_DOWN).build();
        down = DigitalInputDevice.builder(downGpio).setPullUpDown(GpioPullUpDown.PULL_DOWN).build();
        left = DigitalInputDevice.builder(leftGpio).setPullUpDown(GpioPullUpDown.PULL_DOWN).build();
        right = DigitalInputDevice.builder(rightGpio).setPullUpDown(GpioPullUpDown.PULL_DOWN).build();
        center = DigitalInputDevice.builder(centerGpio).setPullUpDown(GpioPullUpDown.PULL_DOWN).build();        
    }
    
    /**
     * Determine if the dpad is pressed up.
     * @return true if the dpad is being pressed up.
     */
    public boolean upPressed() {
        return up.isActive();
    }
    
    /**
     * Determine if the dpad is pressed down.
     * @return true if the dpad is being pressed down.
     */
    public boolean downPressed() {
        return down.isActive();
    }
    
    /**
     * Determine if the dpad is pressed left.
     * @return true if the dpad is being pressed left.
     */
    public boolean leftPressed() {
        return left.isActive();
    }
    
    /**
     * Determine if the dpad is pressed right.
     * @return true if the dpad is being pressed right.
     */
    public boolean rightPressed() {
        return right.isActive();
    }
    
    /**
     * Determine if the dpad is pressed center.
     * @return true if the dpad is being pressed center.
     */
    public boolean centerPressed() {
        return center.isActive();
    }
}
