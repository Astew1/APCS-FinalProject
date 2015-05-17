import java.awt.image.BufferedImage;

/**
 * Created by Zander on 5/12/2015.
 */
public class Player extends Sprite {

    private int jumpCount;

    public Player(BufferedImage bi, int w, int h) {
        super(bi, w, h);
        jumpCount = 0;
    }

    @Override
    public void update(int width, int height, Level level) {
        super.update(width, height, level);
        if(getY() >= height - getHeight() || resetBounces()){           //for bouncing on platforms
            jumpCount = 0;
        }
    }

    public void jump(){
        if(jumpCount < 2) {
            setvY(-35);
            jumpCount++;
        }
    }
}
