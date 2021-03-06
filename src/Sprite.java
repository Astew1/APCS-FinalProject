import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * Created by Zander on 5/12/2015.
 */
public abstract class Sprite {

    private int w, h;
    private double x, y, vX, vY;
    private BufferedImage image;
    private ImageObserver observer;
    private final int maxVX = 100;
    private final int maxVY = 100;
    private boolean hasBouncedOnPlatform;

    public Sprite(BufferedImage image, int w, int h){
        this.image = image;
        this.w = w;
        this.h = h;
        this.hasBouncedOnPlatform = false;

        vX = 0;
        vY = 0;
        x = 0;
        y = 120;
        observer = new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        };
    }

    public void draw(Graphics2D g2){
        g2.drawImage(image, (int)x, (int)y, observer);
    }

    public void update(int width, int height, Level level){

        if(vX > .6){
            vX -=.6;
        }else if(vX < -.6){
            vX+=.6;
        }else{
            vX = 0;
        }

        if(vY > .6){
            vY -=.6;
        }else if(vY < -.6){
            vY+=.6;
        }else{
            vY = 0;
        }
        vY-= .01* vY;       //drag
        vX-= .01* vX;

        Platform touches = level.touches(this);
        if(touches!=null) {
            int touch = touches.whichSideTouches(this);
            if (touch == 0) {                    //hit on the bottom
                if(vY <= 3){
                    vY = 0;
                }

                vY *= -1;
                hasBouncedOnPlatform = true;    //stuff with this allows for bouncing on platforms
            }
            if (touch == 1)                    //hit on right
                vX *= -1;
            if (touch == 2)                    //hit on top
                vY *= -1;
            if (touch == 3)                    //hit on left
                vX *= -1;
            if (touch != -1) {
                Point newPos = touches.getBouncePos(touch, this);
                this.setX((int) newPos.getX());
                this.setY((int) newPos.getY());
            }

        }

        vY += 3;
        x += vX;
        y += vY;

        if(x < 0){
            x = 0;
            vX *= -1;
        }else if(x > width - w){
            x = width - w;
            vX *= -1;
        }
        if(y < 0){
            y = 0;
            vY *= -1;
        }else if(y > height - h){
            y = height - h;
            vY *= -1;
        }
        if(vX > maxVX)
            vX = maxVX;
        if(vY > maxVY)
            vY = maxVY;


    }


    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean resetBounces(){
        if(hasBouncedOnPlatform) {
            hasBouncedOnPlatform = false;
            return true;
        }
        return false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public void setvX(double vX) {
        this.vX = vX;
    }

    public void setvY(double vY) {
        this.vY = vY;
    }

    public double getvX() {
        return vX;
    }

    public double getvY() {
        return vY;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }
}
