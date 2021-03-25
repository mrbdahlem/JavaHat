/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javahat;

/**
 *
 * @author bdahl
 */
public interface Pixel {
    /**
     * Get the amount of blue in this pixel's color.
     * 
     * @return the blue level [0..255] 
     */
    int getBlue();

    /**
     * Get the amount of blue in this pixel's color.
     * 
     * @return the green level [0..255] 
     */
    int getGreen();

    /**
     * Get the amount of blue in this pixel's color.
     * 
     * @return the red level [0..255] 
     */
    int getRed();

    /**
     * Set the amount of blue in this pixel's color.
     * 
     * @param val the amount of blue to include in the color [0..255]
     */
    void setBlue(int val);

    /**
     * Set the amount of green in this pixel's color.
     * 
     * @param val the amount of green to include in the color [0..255]
     */
    void setGreen(int val);

    /**
     * Set the amount of red in this pixel's color.
     * 
     * @param val the amount of red to include in the color [0..255]
     */
    void setRed(int val);
    
    /**
     * Retrieve the integer RGB value of this pixel's color
     * 
     * @return the RGB value of this pixel in its native format and bit-depth.
     *         NOTE: color ordering may not follow red-green-blue
     */
    int intValue();
    
    /**
     * Set the integer RGB value of this pixel's color
     * 
     * @param val the RGB value to assign this pixel, following the native 
     *        color format NOTE: native format may not be 24-bit and RGB
     *        ordering may not follow red-green-blue
     */
    void setIntValue(int val);
}
