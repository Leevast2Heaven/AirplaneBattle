package game.gui.war;

import javax.imageio.ImageIO;
import javax.swing.*;

import game.gui.impact_score.Score;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;
/**
 * 窗口视图，事件
 */
public class MyFrame  extends JFrame  implements KeyListener{

    public final static int width = 400;                        //游戏宽度
    public final static int height = 700;                    //游戏高度
    private   boolean left = false ;                 //向左
    private   boolean right = false ;                //向右
    private   boolean up = false ;                   //向上
    private   boolean down = false ;                 //向右
    private   boolean shoot = false ;                //射击
    private   boolean gameState  = false;
    private  static Scene scene  = null ;                  //游戏场景
   private BufferedImage image =  null;
    private String picPath = "/images/icon.png" ;
    private final int WINDOW_WIDTH = 400 ;
    private final int WINDOW_HEIGHT = 700 ;
    private StartPane starting ;
    AudioClip ac  = null;

    public MyFrame() {
        starting  =  new StartPane(this);
        starting .setBounds(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);
        this.add(starting);

        scene = new Scene(this);
        scene.setSize(width,height);
        scene.addKeyListener(this);
        scene.setFocusable(true);
        setMusic();
        addMenu();
        setFrame();
    }
    @Override
    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_UP:
                 up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            case KeyEvent.VK_SPACE:
                shoot = false;
                break;
            default:break;
        }
    }
    private void addMenu(){
        	 JMenu menu = new JMenu("菜单");
             JMenu menu1 = new JMenu("帮助");
             JMenuBar bar = new JMenuBar();
             bar.add(menu);
             bar.add(menu1);
             JMenuItem i1 = new JMenuItem("开始游戏");
             JMenuItem i2 = new JMenuItem("退出游戏");
             i1.setMnemonic('N');
             i2.setMnemonic('D');
             i1.setAccelerator(KeyStroke.getKeyStroke('N', java.awt.Event.CTRL_MASK));
             i2.setAccelerator(KeyStroke.getKeyStroke('D', java.awt.Event.CTRL_MASK));
             menu.add(i1);
             menu.add(i2);
             i1.addActionListener(new ActionListener(){

 				@Override
 				public void actionPerformed(ActionEvent arg0) {
 					// TODO Auto-generated method stub
 					startGame();
 					scene.requestFocus(true);
 				}
     		}) ;
             i2.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					setDialog();
				}
    		}) ;
             menu1.addMouseListener(new MouseAdapter(){
                 public void mouseClicked(MouseEvent e){
                	JDialog  dg = new  JDialog(MyFrame.this,"帮助" ,true,null);
                	JPanel jp = new JPanel(new BorderLayout());
                	jp.add(new 	JLabel("空格键射击，Enter键暂停！"),BorderLayout.CENTER );
             //   	jp.add(null , BorderLayout.EAST);
                	jp.add(new JLabel("by 软工3班  -16210120332，"),BorderLayout.SOUTH);
                	dg.add(jp);
                	dg.setLocation(420, 300);
                	dg.setSize(300, 300);
                	dg.setVisible(true);        
                 }
             });
             this.setJMenuBar(bar);
        }
    private void setFrame(){
    	   try {
               image = ImageIO.read(getClass().getResource(picPath));   // 导入图片
           } catch (IOException e) {
               e.printStackTrace();
           }
    	   this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    	   this.setLocation(400, 10);
    	   this.setResizable(false);
    	   this.setVisible(true);
    	   setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    	   this.setTitle("v1.0");
    	   this.setIconImage(image);
    	   this.addWindowListener(new WindowAdapter(){
    		   @Override
    		   public void windowClosing(WindowEvent e) {
                             setDialog();  		
    		   }
    	   }); 
    }
    @Override
    public void keyTyped(KeyEvent e){}
	@Override
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            case KeyEvent.VK_SPACE:
                shoot = true;
                break;
            case KeyEvent.VK_ENTER:
                pauseGame();
                break;
            default:break;
        }
            }
    public void startGame(){
    	gameState = true ;
        this.add(scene);
        this.remove(starting);
        scene.startGame();
    }
    public void pauseGame(){
        if(gameState)
            gameState = false ;
        else
            gameState = true ;
    }
    public void gameOver(){
        this.remove(scene);
        this.add(starting);
          repaint();
         if(Score.sc!=0){
           File f= new File("src/score.txt") ;
             try {
			   Writer out = new FileWriter(f,true);
			   out.write("\n" + Score.sc);
			   out.close();
		    } catch (IOException e1) {
			  e1.printStackTrace();
		  }
        }
          JDialog dg = new JDialog(this,"游戏成就",true);
		   dg.setBounds(420, 200, 300, 200);
		   JLabel label = new JLabel("成绩为：" + Score.sc);
		   label.setBounds(100, 50, 100, 20);
		   MyButton bu1 = new MyButton();
		   bu1.setText("返回");
		   bu1.addMouseListener(new MouseAdapter(){
	            public void mouseClicked(MouseEvent e){
	            	dg.dispose();
	            }
	        });
		   bu1.setBounds(120, 100, 60, 30);
		   JPanel jp = new JPanel(null);
		   jp.add(label);
		   jp.add(bu1);
		   dg.add(jp);
		   dg.setResizable(false);
		   dg.setVisible(true);

    }
    public boolean getLeft(){
        return left;
    }
    public boolean getRight(){
        return right;
    }
    public boolean getUp(){
        return up;
    }
    public boolean getDown(){
        return down;
    }
    public boolean getShoot(){return shoot;}
    public boolean getGameState(){return gameState ;}
    public void initialise(){
    	     left = false ;                 
    	     right = false ;                
    	     up = false ;                   
    	     down = false ;                 
    	     shoot = false ;                
    }
    public  void setDialog(){
    	 JDialog dg = new JDialog(this,"退出程序",true);
		   dg.setBounds(420, 200, 300, 200);
		   JLabel label = new JLabel("你确认退出？");
		   label.setBounds(100, 50, 100, 20);
		   MyButton bu1 = new MyButton();
		   bu1.setText("退出");
		   MyButton bu2 = new MyButton();
		   bu2.setText("取消");
		   bu1.setBounds(50, 90, 70, 25);
		   bu2.setBounds(150, 90, 70, 25);
		   bu1.addMouseListener(new MouseAdapter(){
	            public void mouseClicked(MouseEvent e){
	                System.exit(1);
	            }
	        });
		   bu2.addMouseListener(new MouseAdapter(){
	            public void mouseClicked(MouseEvent e){
	                dg.dispose();
	            }
	        });
		   JPanel jp = new JPanel(null);
		   jp.add(label);
		   jp.add(bu1);
		   jp.add(bu2);
		   dg.add(jp);
		   dg.setResizable(false);
		   dg.setVisible(true);
		   }
    public void setpause(){
    	this.gameState = true ;
    }
    //设置music
    private void setMusic(){
    	File f = new File("src/music/a.wav");
    	try{
		  ac = Applet.newAudioClip(f.toURI().toURL());
         }catch(Exception e){}
    }
    
}