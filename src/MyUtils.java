import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Zander on 5/12/2015.
 */
public class MyUtils {

    public static BufferedImage getImage(String name){
        try {
            BufferedImage image = ImageIO.read(new File("res/" + name));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getImage(String name, int width, int height){
        BufferedImage image;
        try {
            image = ImageIO.read(new File("res/" + name));
            int w = image.getWidth();
            int h = image.getHeight();
            double wS = width/(double)w;
            double hS = height/(double)h;
            BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            AffineTransform at = new AffineTransform();
            at.scale(wS, hS);
            AffineTransformOp scaleOp =
                    new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
            after = scaleOp.filter(image, after);
            return after;
        } catch (IOException e) {
            e.printStackTrace();
        }
       return null;
    }
}
