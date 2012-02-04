/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slimesoccer.util;

/**
 * This class defines a vector in
 * 2-dimensional space.
 * @author BuzzW
 */
public class Vector2D {

    //the components of the vector
    public float x, y;
    
    //scratch vectors for use elsewhere
    public static final Vector2D scratch = new Vector2D();
    public static final Vector2D scratch2 = new Vector2D();

    public Vector2D() {
        this(0, 0);
    }

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D v) {
        this(v.x, v.y);
    }

    public void setTo(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setTo(Vector2D v) {
        setTo(v.x, v.y);
    }

    public void setPolar(float magnitude, float radians) {
        setTo((float)Math.cos(radians) * magnitude, (float)Math.sin(radians) * magnitude);
    }

    public float length() {
        return (float) MathEx.getDistance((double)x, (double)y);
    }

    public void normalize() {
        divide(length());
    }

    public void add(Vector2D v) {
        x += v.x;
        y += v.y;
    }

    public void subtract(Vector2D v) {
        x -= v.x;
        y -= v.y;
    }

    public void divide(float scalar) {
        scale(1 / scalar);
    }

    public void scale(float scalar) {
        x *= scalar;
        y *= scalar;
    }

    /**
     * Returns the angle in radians
     * (0 - 2PI) that this vector is pointing.
     * @return 
     */
    public float getAngle() {
        //protect against NANswers
        if(x == 0){
             if(y > 0) return (float)MathEx.halfPi;
             else return (float)MathEx.threeOverTwoPi;
        }else if(y == 0){
            if(x > 0) return 0;
            else return (float)Math.PI;
        }
        
        float rads = (float)Math.atan(y / x);

        //figure out which quadrant the vector is in
        boolean xpos = x > 0;
        boolean ypos = y > 0;

        if (xpos && ypos) {
            //first quadrant
            return rads;
        } else if (ypos) {
            //second quadrant
            return (float)Math.PI + rads;
        } else if (xpos) {
            //fourth quadrant
            return (float)MathEx.twoPi + rads;
        } else {
            //third quadrant
            return (float)Math.PI + rads;
        }
    }
}
