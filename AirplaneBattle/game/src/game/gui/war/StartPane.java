package game.gui.war;

import javax.imageio.ImageIO;
import javax.swing.*;

import game.gui.impact_score.Score;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 开始面板
 */
public class StartPane extends JPanel {
    private BufferedImage image;
    private MyFrame myframe =  null ;
    public static int music = 0;

    public StartPane (MyFrame myframe){
        try{
            image = ImageIO.read(getClass().getResource("/images/bg/bg4.jpg"));   // 导入
        }catch(IOException e){
            e.printStackTrace();
        }
        this.myframe = myframe ;
        this.setLayout(null);
        addButton();
    }
    private void addButton(){
        JButton bu = new MyButton() ;
        bu.setText("新游戏");
        bu.setBounds(150,300,100,25);
        this.add(bu);
        JButton bu1 = new MyButton() ;
        bu1.setText("音乐");
        bu1.setBounds(150,420,100,25);
        this.add(bu1);
        JButton bu2 = new MyButton() ;
        bu2.setText("分数");
        bu2.setBounds(150,380,100,25);
        this.add(bu2);
        JButton bu3 = new MyButton() ;
        bu3.setText("退出游戏");
        bu3.setBounds(150,340,100,25);
        this.add(bu3);
        bu.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                myframe.startGame();
            }
        });
        bu1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
            	if(music == 0){
            		myframe.ac.loop();
            		music = 1;
            	}else{
            		myframe.ac.stop();
            		music =0;
            	}
            }
        });
        bu2.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
            	JFrame jf = new JFrame();
            	JPanel jp = new JPanel();
            	jf.add(jp);
            	jf.setVisible(true);
            	jf.setSize(200,300);
            	jf.setLocation(420, 100);
            	File f= new File("src/score.txt");
            	SortedSet<Integer> allSet = new TreeSet<Integer>() ;
            	try {
					Scanner scane = new Scanner(f);
					while(scane.hasNext()){
						allSet.add(Integer.parseInt((scane.next().trim())));
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            Iterator<Integer> x = allSet.iterator();
            while(x.hasNext()){
            	jp.add(new JLabel("           "+ x.next() +" 分              "));
            }
            }
        });
        bu3.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                myframe.setDialog();
            }
        });
    }
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(image,0,0,this);
        paintChildren(g);    
    }
}
