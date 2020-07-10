package game.gui.war;

import game.gui.enemy.EnemyCommander;
import game.gui.impact_score.Crash;
import game.gui.impact_score.Score;
import game.gui.plane.Plane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Leevast
 */
public class Scene extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Background bg = null; // 背景类
	private Plane plane = null; // 我方飞机
	private EnemyCommander ec = null; // 敌机管理
	private Crash crash = null; // 碰撞
	private Score score = null; // 记分板
	private MyFrame myframe = null; // 该类的调用者
	private long time1 = System.nanoTime();
	private long time2 = 0l;
	private long time3 = 0l;
	private long time4 = 0l;
	private long time5 = 0l;
	private int t = 0;
	public static int degree = 10;

	// private String picPath = "/images/game_start.png" ;
	public Scene(MyFrame myframe) {
		this.myframe = myframe;
		bg = new Background();
		plane = new Plane(this);
		ec = new EnemyCommander();
		crash = new Crash(this);
		score = new Score(this);
	}

	public void startGame() {
		plane.initialise();
		ec.initialise();
		crash.initialise();
		score.initialise();
		myframe.initialise();
		new Thread(new updateDate()).start();
	}

	public synchronized void paint(Graphics g) {
		bg.paint(g);
		g.setColor(Color.CYAN);
		if (myframe.getGameState()) {
			bg.paint(g);
			plane.paint(g);
			ec.paint(g);
			crash.paint(g);
			score.paint(g);
		}
		countFPS(g);
		paintComponents(g);
	}

	// synchronized
	public synchronized void update() {
		bg.update();
		plane.update();
		ec.update();
		crash.update();
	}

	class updateDate implements Runnable {
		public void run() {
			while (plane.getLife() > 0 && !EnemyCommander.stop ) {
				if (myframe.getGameState()) {
					repaint();
					update();
				}else{
					JDialog dg = new JDialog(myframe,true);
					   dg.setBounds(420, 200, 300, 200);
					   JLabel label = new JLabel("游戏暂停中..。");
					   label.setBounds(100, 50, 100, 20);
					   MyButton bu1 = new MyButton();
					   bu1.setText("返回游戏");
					   bu1.addMouseListener(new MouseAdapter(){
				            public void mouseClicked(MouseEvent e){
				            	dg.dispose();
				            	myframe.setpause();
				            }
				        });
					   bu1.setBounds(100, 100, 90, 20);
					   JPanel jp = new JPanel(null);
					   jp.add(label);
					   jp.add(bu1);
					   dg.add(jp);
					   dg.setResizable(false);
					   dg.setVisible(true);

				}
				try {
					Thread.sleep(16);
				} catch (Exception e) {
				}
			}
			myframe.gameOver();
		}
	}
	public Plane getPlane() {
		return plane;
	}

	public EnemyCommander getEnemyCommander() {
		return ec;
	}

	public MyFrame getMyFrame() {
		return this.myframe;
	}

	public Score getScore() {
		return score;
	}

	private void countFPS(Graphics g) {
		time2 = time1;
		time1 = System.nanoTime();
		time5 += (time1 - time2);
		time2 = 1000000000L / (time1 - time2);
		time3 += time2;
		t++;
		g.drawString("FPS:" + time4, 340, 20);
		if (time5 > 1000000000) {
			time4 = time3 / t + 1;
			t = 0;
			time3 = 0l;
			time5 = 0l;
		}
	}
}