import javax.sound.sampled.Line;
import java.awt.*;

/**
 * Created by Zander on 5/14/2015.
 */
public class Platform {

    private int x, y, width, height;
    private Rectangle thisRect;

    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        thisRect = new Rectangle(x, y, width, height);
    }


    //returns 0 if horizontal 1 if vertical -1 if none
//    public int touches(Sprite s) {
//        if ((s.getX() + s.getWidth()) > x && s.getX() < x + width && s.getY() + s.getHeight() > y
//                && s.getY() < y + height) {
//            if(Math.min(Math.abs(x  - s.getX()),Math.abs(x + width - (s.getX() + s.getWidth()))) >= Math.min(Math.abs(y  - s.getY()),Math.abs(y + height - (s.getY() + s.getHeight())))){
//                return 1;
//            }else{
//                return 0;
//            }
//        }
//       return -1;
//    }

                                //This method will only work if the ball's velocity will never allow it to go completely through the platform - it has to be inside at one point
    public int touches(Sprite s){           //test to see if the ball is touching the platform
                                            //key: -1 = NOT touching, 0 = top, 1 = left, 2 = bottom, 3 = right
        int spriteX = (int)s.getX();        //also sets the sprite to be on the outside of the platform (so it wont collide again)
        int spriteY = (int)s.getY();
        int spriteW = (int)s.getWidth();
        int spriteH = (int)s.getHeight();

        Point botSprite, leftSprite, topSprite, rightSprite;

        topSprite = new Point(spriteX + (spriteW/2), spriteY);
        leftSprite = new Point(spriteX, spriteY + (spriteH/2));
        botSprite = new Point(spriteX + (spriteW/2), spriteY+spriteH);
        rightSprite = new Point(spriteX+spriteW, spriteY + (spriteH/2));

//        Point[] collisionPoints = {botSprite, rightSprite, topSprite, leftSprite};
//        for (int i = 0; i < collisionPoints.length; i++) {
//            if(thisRect.contains(collisionPoints[i])){
//                switch(i){
//                    case 0:
//                        s.setY(this.y - spriteH - 1);
//                        System.out.println("top");
//                        break;
//                    case 1:
//                        s.setX(this.x-spriteW-1);
//                        System.out.println("left");
//                        break;
//                    case 2:
//                        s.setY(this.y+height+1);
//                        System.out.println("bottom");
//                        break;
//                    case 3:
//                        s.setX(this.x+width+1);
//                        System.out.println("right");
//                        break;
//                    default:
//                        System.out.println("THIS ERROR SHOULD NEVER HAPPEN: PLATFORM INSIDE TOUCHES METHOD");
//                        break;
//                }
//                return i;
//            }
//        }
        if(thisRect.contains(botSprite)){
            s.setY(y-spriteH-1);
            return 0;
        }
        if(thisRect.contains(rightSprite)){
            s.setX(x-spriteW-1);
            return 1;
        }
        if(thisRect.contains(topSprite)){
            s.setY(y+height+1);
            return 2;
        }
        if(thisRect.contains(leftSprite)){
            s.setX(x+width+1);
            return 3;
        }
        return -1;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.BLACK);
//        g2.fillRect(x, y, width, height);
        g2.fill(thisRect);
    }
}


