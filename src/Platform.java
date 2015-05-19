import javax.sound.sampled.Line;
import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Created by Zander on 5/14/2015.
 */
public class Platform {

    private int x, y, width, height, originalX;
    private Rectangle thisRect;
    private int sRadius;


    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.originalX = x;
        this.width = width;
        this.height = height;
        thisRect = new Rectangle(x, y, width, height);
        sRadius = -1;

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
    public Platform touches(Sprite s){           //test to see if the ball is touching the platform
                                            //key: -1 = NOT touching, 0 = top, 1 = left, 2 = bottom, 3 = right

        Point spriteCenter = new Point((int)( s.getX() + (s.getWidth() / 2 )), (int)( s.getY() + ( s.getHeight() / 2 )));

        double sRad = s.getHeight()/2;           //THIS ASSUMES THE SPRITE IS A CIRCLE!!!!!!!!!!!!!!!!!!!!
        if(sRadius<0)
            sRadius = (int)sRad;

        Rectangle boundRect = new Rectangle((int)(x-sRad+1), (int)(y-sRad+1), (int)(width+s.getHeight()-1), (int)(height+s.getHeight()-1));
        
        if(boundRect.contains(spriteCenter)){

            return this;
        }

        return null;


// int spriteX = (int)s.getX();
//        int spriteY = (int)s.getY();
//        int spriteW = s.getWidth();
//        int spriteH = s.getHeight();
//
//        Point botSprite, leftSprite, topSprite, rightSprite;
//
//        topSprite = new Point(spriteX + (spriteW/2), spriteY);
//        leftSprite = new Point(spriteX, spriteY + (spriteH/2));
//        botSprite = new Point(spriteX + (spriteW/2), spriteY+spriteH);
//        rightSprite = new Point(spriteX+spriteW, spriteY + (spriteH/2));
//
////        Point[] collisionPoints = {botSprite, rightSprite, topSprite, leftSprite};
////        for (int i = 0; i < collisionPoints.length; i++) {
////            if(thisRect.contains(collisionPoints[i])){
////                switch(i){
////                    case 0:
////                        s.setY(this.y - spriteH - 1);
////                        System.out.println("top");
////                        break;
////                    case 1:
////                        s.setX(this.x-spriteW-1);
////                        System.out.println("left");
////                        break;
////                    case 2:
////                        s.setY(this.y+height+1);
////                        System.out.println("bottom");
////                        break;
////                    case 3:
////                        s.setX(this.x+width+1);
////                        System.out.println("right");
////                        break;
////                    default:
////                        System.out.println("THIS ERROR SHOULD NEVER HAPPEN: PLATFORM INSIDE TOUCHES METHOD");
////                        break;
////                }
////                return i;
////            }
////        }
//        if(thisRect.contains(botSprite)){
//            return 0;
//        }
//        if(thisRect.contains(rightSprite)){
//            s.setX(x-spriteW-1);
//            return 1;
//        }
//        if(thisRect.contains(topSprite)){
//            s.setY(y+height+1);
//            return 2;
//        }
//        if(thisRect.contains(leftSprite)){
//            s.setX(x+width+1);
//            return 3;
//        }

    }

    public int whichSideTouches(Sprite s){
        double sRad = s.getHeight()/2;
        Point sCen = new Point((int)( s.getX() + (s.getWidth() / 2 )), (int)( s.getY() + ( s.getHeight() / 2 )));
        Rectangle boundRect = new Rectangle((int)(x-sRad+1), (int)(y-sRad+1), (int)(width+s.getHeight()-1), (int)(height+s.getHeight()-1));
        Line2D top, left, right, bot;
        Line2D sPath = new Line2D.Double(sCen.getX()- (3*s.getvX()), sCen.getY()-(3*s.getvY()), sCen.getX(), sCen.getY());


        top = new Line2D.Double(boundRect.getX(), boundRect.getY(), boundRect.getX()+boundRect.getWidth(), boundRect.getY());
        left = new Line2D.Double(boundRect.getX(), boundRect.getY(), boundRect.getX(), boundRect.getY()+boundRect.getHeight());
        right = new Line2D.Double(boundRect.getX()+boundRect.getWidth(), boundRect.getY(), boundRect.getX()+boundRect.getWidth(), boundRect.getY()+boundRect.getHeight());
        bot = new Line2D.Double(boundRect.getX(), boundRect.getY()+boundRect.getHeight(), boundRect.getX()+boundRect.getWidth(), boundRect.getY()+boundRect.getHeight());

        if(top.intersectsLine(sPath))
            return 0;
        if(left.intersectsLine(sPath))
            return 1;
        if(bot.intersectsLine(sPath))
            return 2;
        if(right.intersectsLine(sPath))
            return 3;
        System.out.println("this should never happen!, inside whichSideTouches");
        return -1;
    }

    public Point getBouncePos(int side, Sprite s){      //0 = top, 1 = left, 2 = bot, 3 = right
        switch(side){
            case 0:
                return new Point((int) s.getX(), y - s.getHeight() - 1);


            case 1:
                return new Point(x - s.getWidth() - 1, (int) s.getY());


            case 2:
                return new Point((int) s.getX(), y + height + 1);


            case 3:
                return new Point(x + width + 1, (int) s.getY());
            default:
                System.out.println("this error should never happen - inside Platform.getBouncePos()");
                return new Point(200, 200);



        }
    }

    public void draw(Graphics2D g2, int translateX){
        Rectangle theoreticalRectangle = new Rectangle((int)thisRect.getX()-translateX, (int)thisRect.getY(), (int)thisRect.getWidth(), (int)thisRect.getHeight());
        x = originalX - translateX;
        g2.setColor(Color.BLACK);
//        g2.fillRect(x, y, width, height);
        g2.fill(theoreticalRectangle);
//        g2.setColor(Color.GREEN);
//        g2.draw(boundRect);
    }
}


