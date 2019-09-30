package main;

import java.awt.Color;
import java.awt.Graphics;


public class Timer {
	
	double SetTime, ElapsedTime;
	int radius,width,prevTime,paused,marker;
	int r;
	boolean active = false;
	
	public Timer(int r,int w) {
		radius = r;
		width = w;
	}
	
	public void setTimer(double t) {
		ElapsedTime = 0;
		SetTime = t*60000;
		prevTime = (int) System.currentTimeMillis();
		paused = 0;
	}
	
	public void render(Graphics g) {
		r = (int) (360*(ElapsedTime/SetTime));
		
		 if (ElapsedTime > SetTime ) {
			g.setColor(Color.GREEN);
			g.fillOval(500-radius,300-radius,radius*2,radius*2);
		 }
			
		if (!active || paused > 0) {
			g.setColor(Color.RED); 
			g.fillArc(500-(int)(radius*0.975),300-(int)(radius*0.975),(int)(radius*1.95),(int)(radius*1.95),90-marker,-(int)(360*(paused/SetTime)));
		}
		
		if (ElapsedTime < SetTime ) {
			g.setColor(Color.YELLOW);
			g.fillArc(500-radius,300-radius,radius*2,radius*2,90,-r);
		} if (ElapsedTime > SetTime ) {
			g.setColor(Color.CYAN);
			g.fillArc(500-(int)(radius*1.1),300-(int)(radius*1.1),(int) (radius*2.2),(int)(radius*2.2),90,-(r-360));
		}
		g.setColor(Color.BLACK);
		g.fillOval(500-radius+width,300-radius+width,(radius-width)*2,(radius-width)*2);
	}
	
	public void run() {
		if (active) {
			ElapsedTime += (int) System.currentTimeMillis() - prevTime;
			if (paused > 0) {
				paused -= 50*(SetTime/60000);
			}else if (paused < 0) {
				paused = 0;
			}
		} else if (ElapsedTime > 0) {
			paused += (int) System.currentTimeMillis() - prevTime;
		}
		prevTime = (int) System.currentTimeMillis();
	}
	
	public void toggle() {
		if (active) {
			marker = r;
		}
		active = !active;
	}
	
}
