package game.gui.enemy;

import game.gui.war.Jobject;
import game.gui.war.MyFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class  Enemy extends Jobject{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
    private int time = 0 ;
    private String url = "/images/enemy1.png";
    public int life ;

    public Enemy(){                        //无参构造方法
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
    public void setLife(){
        this.life  -- ;
    }
    public int getLife(){
        return this .life ;
    }
}
