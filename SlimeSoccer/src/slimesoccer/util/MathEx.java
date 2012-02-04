/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slimesoccer.util;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * A bunch of useful but unclassifiable functions useful in game programming.
 * @author Connor Willison
 */
public class MathEx{

    private static GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    private static Random r = new Random();
    
    public static final double twoPi = Math.PI * 2;
    public static final double halfPi = Math.PI / 2;
    public static final double threeOverTwoPi = 3 * Math.PI / 2;

    public static double getAngleOfLineNormalSpace (double x1, double y1, double x2, double y2){
	return getAngleOfLineNormalSpace (x1 - x2, y1 - y2);
    }

    /**
     * Finds the angle of a line with point 1 at unanchored end and
     * point 2 at the origin. The returned value can be used directly with
     * the affine transform "rotate" method.
     */
    public static double getAngleOfLineNormalSpace (double deltax, double deltay) {

	boolean xneg = deltax < 0;
	boolean yneg = deltay < 0;

	double factor;

	if (xneg && yneg) {
	    factor = 180;

	} else if (xneg) {
	    factor = 180;


	} else if (yneg) {
	    factor = 360;
	} else {
	    factor = 0;
	}

	return Math.toDegrees(Math.atan(deltay/deltax)) + factor;
    }

    public static double getAngleOfLineScreenSpace (double x1, double y1, double x2, double y2){
	return getAngleOfLineScreenSpace (x1 - x2, y1 - y2);
    }

    /**
     * Finds the angle of a line with point 1 at unanchored end and
     * point 2 at the origin. The returned value can be used directly with
     * the affine transform "rotate" method.
     */
    public static double getAngleOfLineScreenSpace (double deltax, double deltay) {

	//modify deltay for screen space
	deltay *= -1;

	boolean xneg = deltax < 0;
	boolean yneg = deltay < 0;

	double factor;

	if (xneg && yneg) {
	    factor = 180;

	} else if (xneg) {
	    factor = 180;


	} else if (yneg) {
	    factor = 360;
	} else {
	    factor = 0;
	}

	return Math.toDegrees(Math.atan(deltay/deltax)) + factor;
    }

    /**
     * Finds the distance between two points using the Pythagorean Theorem,
     * but leaves the final value squared.
     */
    public static double getDistanceSquared(double x1, double y1, double x2, double y2) {
	return getDistanceSquared(x2 - x1, y2 - y1);
    }

    /**
     * Finds the distance between two points using the Pythagorean Theorem,
     * but leaves the final value squared.
     */
    public static double getDistanceSquared(double dx, double dy) {
	return dx * dx + dy * dy;
    }

    /**
     * Finds the distance between two points using the Pythagorean Theorem.
     */
    public static double getDistance(double x1, double y1, double x2, double y2) {
	return Math.sqrt(getDistanceSquared(x1, y1, x2, y2));
    }

    /**
     * Finds the distance between two points using the Pythagorean Theorem.
     */
    public static double getDistance(double dx, double dy) {
	return Math.sqrt(getDistanceSquared(dx, dy));
    }

    /**
     * Get a random number generator.
     * @return
     */
    public static Random getRandom() {
	return r;
    }
    
    public static Image loadImageFromJar(String filename){
	try {
	    return ImageIO.read(MathEx.class.getResource(filename));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	return null;
    }
    
    /**
     * Returns a random number of type int between the
     * two arguments, inclusive.
     * @param low
     * @param high
     * @return 
     */
    public static int getRandomInt(int low,int high){
        return (Math.abs(r.nextInt()) % (high - low + 1))+ low;
    }
    
    /**
     * Returns a random number of type double between the
     * two arguments, inclusive.
     * @param low
     * @param high
     * @return 
     */
    public static double getRandomDouble(double low,double high){
        return (Math.abs(r.nextDouble()) * (high - low)) + low;
    }
    
    /**
     * Returns a random number of type double between the
     * two arguments, inclusive.
     * @param low
     * @param high
     * @return 
     */
    public static long getRandomLong(long low,long high){
        return (Math.abs(r.nextLong()) % (high - low + 1)) + low;
    }
    
    public static boolean percent(double percent){
        percent = Math.min(percent,100.0);
        percent = Math.max(percent,0.0);
        
        percent /= 100;
        
        return r.nextDouble() <= percent;
    }
}
