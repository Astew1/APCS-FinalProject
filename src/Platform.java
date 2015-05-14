import java.awt.*;

/**
 * Created by Zander on 5/14/2015.
 */
public class Platform {

    private int x, y, width, height;

    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    //returns 0 if horizontal 1 if vertical -1 if none
    public int touches(Sprite s) {
        if ((s.getX() + s.getWidth()) > x && s.getX() < x + width && s.getY() + s.getHeight() > y
                && s.getY() < y + height) {
            if(Math.min(Math.abs(x  - s.getX()),Math.abs(x + width - (s.getX() + s.getWidth()))) >= Math.min(Math.abs(y  - s.getY()),Math.abs(y + height - (s.getY() + s.getHeight())))){
                return 1;
            }else{
                return 0;
            }
        }
       return -1;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.fillRect(x, y, width, height);
    }
}


