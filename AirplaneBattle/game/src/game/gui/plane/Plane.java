package game.gui.plane;

import game.gui.war.Jobject;
import game.gui.war.MyFrame;
import game.gui.war.Scene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import static game.gui.war.MyFrame.height;
import static game.gui.war.MyFrame.width;

/**
 * 飞机类
 * Created by Leevast
 */
public class Plane extends Jobject{

    private BufferedImage image;                           
    private BufferedImage image1;                          
    private  LinkedList<Bullet> bullets = new LinkedList<>();   //子弹集合
    private  LinkedList<Bullet> rebullets = new LinkedList<>();   //子弹集合
    private int x ,y  ;                                 
    private boolean fire = false  ;                
    private int life  = 4;
    private int speed = 3 ;
    private int  time = 0 ;
    private String picpath = "/images/plane.png";
    private Scene scene = null ;
    
    public Plane(Scene scene){
        this.scene = scene ;
        this.x = width / 2 - 50;        //战机的初始位置
        this.y = height-100;

        try{
             image = ImageIO.read(getClass().getResource(picpath));   // 导入图片
             image1  = ImageIO.read(getClass().getResource("/images/life.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /*
    *  绘制战机和子弹
    * */
    @Override
    public void paint(Graphics g){
        g.drawImage(image,x,y,this);
        for(int i= 0;i < life;i++){
            g.drawImage(image1,10,(40 + 20*i),this );
        }

        if(life > 0){
            for(Bullet bullet:bullets){
                bullet.paint(g);
            }
        }
    }
    /*
    *   更新飞机位置
    * */
    @Override
     public void update() {
        if(life > 0){
            this.move();
            if (fire) {
                if (time == 10) {
                	if(!rebullets.isEmpty()){
                		rebullets.getFirst().setl(x, y);
                		bullets.add(rebullets.getFirst());
                		rebullets.removeFirst();
                	}else
                	     bullets.add(new Bullet(x,y));
                   time = 0;                     
                
                	}
                time++;
            }
            for(int i = 0;i < bullets.size();i++){
            	if(bullets.get(i).move()){
            		rebullets.add(bullets.get(i));
            		bullets.remove(i);
            	}
     
            }
        }

    }
    public void  move(){
        MyFrame  frame  = scene.getMyFrame();
        if(frame.getLeft() )
            if(x > 0)
            x -= speed;
        if(frame.getRight())
            if(x < width - 50)
            x += speed;
        if(frame.getUp())
            if(y > 0)
            y -= speed;
        if(frame.getDown())
            if(y < height -100)
                 y += speed;
        fire = frame.getShoot();
    }
    public Rectangle impactArea(){
        return new Rectangle(this.x,this.y,image.getWidth() ,image .getHeight() ) ;
    }
    public void setLife(){
        this.life -- ;
    }
    public  int getLife(){
        return this.life ;
    }
    public void setX( int x){this.x = x  ;}
    public int  getX(){return this.x;}
    public int getY(){
        return this.y;
    }
    public void setY(int y ){
        this.y = y ;
    }
    public LinkedList<Bullet>  getBullets(){
        return  bullets;
    }
    
    public void initialise(){
        this.x = width / 2 - 50;        
        this.y = height-100;
        this.life = 4;
    }
}
