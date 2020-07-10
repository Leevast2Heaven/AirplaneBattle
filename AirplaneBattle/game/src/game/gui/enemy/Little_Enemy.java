package game.gui.enemy;

import game.gui.war.MyFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Leevast
 */
public class Little_Enemy extends Enemy{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
    private String url = "/images/enemy" + (int)(Math.random() * 6 + 1)+ ".png" ;

    public Little_Enemy(){                        //无参构造方法
    	x= (int) (Math.random()*200 + 50);
        try{
             image = ImageIO.read(getClass().getResource(url));   // 导入图片
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(image,x,y,this);
    }
    /*
    *
    * */

    public boolean  update_1(){
            if (y > MyFrame.height ) {
               return true ;
            }else{
                this.y += 1;
                return false ;
            }
    }
    public Rectangle impactArea() {
        return new Rectangle(this.x, this.y, image.getWidth(this), image.getHeight(this));
    }
}
