package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class App extends JPanel implements MouseMotionListener, KeyListener{
	
	private static final long serialVersionUID = -616365487435413161L;
	static int W = 1000, H = 600;
	static JFrame frame;
	boolean timing  = false; 
	String timeInp = "";
	Timer clock = new Timer();
	Font font = new Font("Impact", Font.PLAIN, 50);
	
	public App() {
		setSize(new Dimension(W,H));
		setPreferredSize(new Dimension(W,H));
		addKeyListener(this);
		addMouseMotionListener((MouseMotionListener) this);
		setFocusable(true);
	}
	public static void main(String[] args) {	
		App screen = new App();	
		frame = new JFrame("My Drawing");
		frame.add(screen);
		frame.pack();
    	frame.setVisible(true);
    	frame.setResizable(false);
    	System.out.println("Running");
	};
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, W, H);
		if (timing) {
			clock.run();
			clock.render(g2);
			//System.out.println(System.currentTimeMillis());
		} else {
			FontMetrics metrics = g.getFontMetrics(font);
			int h = metrics.getHeight();
			int x = (1000 - metrics.stringWidth(timeInp)) / 2;
			int y = ((600 - h) / 2) + metrics.getAscent();
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString(timeInp, x, y);
		}
		repaint();
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		 if(e.getKeyCode() == KeyEvent.VK_SPACE){
			 if (timing) {
				 clock.toggle();
			 }
		}else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			timing = true;
			clock.setTimer(Integer.parseInt(timeInp));
		} else if ('0'<=e.getKeyChar() && e.getKeyChar()<='9')  {
			timeInp += String.valueOf(e.getKeyChar());
		} else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			timeInp = timeInp.substring(0, timeInp.length()-1);
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			timing = false;
		};
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
