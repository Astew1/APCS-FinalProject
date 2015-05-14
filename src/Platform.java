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

    public boolean touches(Sprite s) {
        if ((s.getX() + s.getWidth()) > x && s.getX() < x + width && s.getY() + s.getHeight() > y
                && s.getY() < y + height) {
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.fillRect(x, y, width, height);
    }
}


