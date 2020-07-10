package game.gui.war;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 背景类
 * */
public class Background extends Jobject {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage bg1 ;
	BufferedImage bg2 ;
	int i = -700;

    public Background(){
        try{
                bg1 =  ImageIO.read(getClass().getResource("/images/bg/bg2.jpg"));
                bg2 =  ImageIO.read(getClass().getResource("/images/bg/bg2.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void paint(Graphics g){

        g.drawImage(bg1,x,y,this);
        g.drawImage(bg2,x,i,this);
    }
    @Override
    public void update() {
        y++;
        i++;
        if(y == MyFrame.height)
            y  = -700;
        if(i == MyFrame.height)
            i = -700;

    }
}
