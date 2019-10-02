package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;


public class Timer {
	
	double SetTime, ElapsedTime;
	int radius,width = 30,prevTime,paused,marker;
	int r;
	boolean active = false;
	String timeString,pausedString;
	Font font = new Font("Impact", Font.PLAIN, 50);
	Font font2 = new Font("Impact", Font.PLAIN, 20);
	Color time1 = Color.YELLOW,time2= Color.CYAN,pause1 = Color.RED ,pause2 = Color.ORANGE,inner = new Color(0x111111),done = Color.GREEN;
	
	public Timer() {
	}
	
	public void setTimer(double t) {
		if (t < 45) {
			radius = (int) (3.1*t)+140;
		} else {
			radius = 250;
			width = 10+(int)(1350/(1.5*t));
		}
		ElapsedTime = 0;
		SetTime = t*60000;
		prevTime = (int) System.currentTimeMillis();
		paused = 0;
		active = true;
	}
	
	public void render(Graphics2D g) {
		r = (int) (360*(ElapsedTime/SetTime));
		
		 if (ElapsedTime > SetTime ) {
			g.setColor(done);
			g.fillOval(500-radius,300-radius,radius*2,radius*2);
		 }
			
		if (!active || paused > 0) {
			if (ElapsedTime > SetTime ) {
				g.setColor(pause2); 
			} else {
				g.setColor(pause1); 
			}
			g.fillArc(500-(int)(radius*0.975),300-(int)(radius*0.975),(int)(radius*1.95),(int)(radius*1.95),90-marker,-(int)(360*(paused/SetTime)));
		}
		
		if (ElapsedTime < SetTime ) {
			g.setColor(time1);
			g.fillArc(500-radius,300-radius,radius*2,radius*2,90,-r);
		} if (ElapsedTime > SetTime ) {
			g.setColor(time2);
			g.fillArc(500-(int)(radius*1.05),300-(int)(radius*1.05),(int) (radius*2.1),(int)(radius*2.1),90,-(r-360));
		}
		g.setColor(Color.BLACK);
		g.fillOval(500-radius+width,300-radius+width,(radius-width)*2,(radius-width)*2);
		g.setColor(inner);
		g.fillOval(500-75,300-75,150,150);
		g.setColor(Color.WHITE);
		String s = String.valueOf((int)(ElapsedTime/10)%100);
		timeString = String.valueOf((int)(ElapsedTime/3600000)%60)+":"+String.valueOf((int)(ElapsedTime/60000)%60)+":"+String.valueOf((int)(ElapsedTime/1000)%60);
		FontMetrics metrics = g.getFontMetrics(font);
		int h = metrics.getHeight();
		int x = (1000 - metrics.stringWidth(timeString)) / 2;
		int y = ((600 - h) / 2) + metrics.getAscent();
		g.setFont(font);
		g.drawString(timeString, x, y);
		if (!active && ElapsedTime > 0) {
			if (ElapsedTime > SetTime ) {
				g.setColor(pause2); 
			} else {
				g.setColor(pause1); 
			}
			g.setFont(font2);
			pausedString = String.valueOf((int)(paused/3600000)%60)+":"+String.valueOf((int)(paused/60000)%60)+":"+String.valueOf((int)(paused/1000)%60);
			metrics = g.getFontMetrics(font2);
			x = (1000 - metrics.stringWidth(pausedString)) / 2;
			y = ((600 - metrics.getHeight()) / 2)+h+4;
			g.drawString(pausedString, x, y);
		}
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
