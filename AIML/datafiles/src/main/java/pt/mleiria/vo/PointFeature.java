/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.vo;

/**
 *
 * @author manuel
 */
public class PointFeature implements Feature{
    
    private double x;
    
    private double[] y;

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    @Override
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double[] getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    @Override
    public void setY(double[] y) {
        this.y = y;
    }

    @Override
    public String toString() {
        String str =  "[x,y]=["+x;
        for(int i = 0; i < y.length; i++){
            str += "," + y[i];
        }
        str += "]";
        return str;
    }
    

    
    
    
}
