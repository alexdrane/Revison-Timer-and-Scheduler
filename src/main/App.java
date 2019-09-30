package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings("unused")

public class App extends JPanel implements MouseMotionListener, KeyListener{
	
	private static final long serialVersionUID = -616365487435413161L;
	static int W = 1000, H = 600;
	static JFrame frame;
	Timer clock = new Timer(250,20);
	
	public App() {
		setSize(new Dimension(W,H));
		setPreferredSize(new Dimension(W,H));
		addKeyListener(this);
		addMouseMotionListener((MouseMotionListener) this);
		setFocusable(true);
		clock.setTimer(10);
	}
	public static void main(String[] args) {	
		App screen = new App();	
		frame = new JFrame("My Drawing");
		frame.add(screen);
		frame.pack();
    	frame.setVisible(true);
    	System.out.println("Running");
	};
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, W, H);
		clock.run();
		clock.render(g);
		//System.out.println(System.currentTimeMillis());
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
		     clock.toggle();
		  }
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
