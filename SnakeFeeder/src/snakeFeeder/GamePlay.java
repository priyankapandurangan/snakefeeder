package snakeFeeder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
	
	private  int[] snakeXlength= new int[750];
	private int[] snakeYlength= new int[750];
	
	private boolean left= false;
	private boolean right= false;
	private boolean up= false;
	private boolean down= false;
	
	private ImageIcon head;
	private ImageIcon tail;
	
	private int lengthOfSnake= 3;
	private int moves= 0;
	private int score= 0;
	
	private int[] fruitXPos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,
			600,625,650,675,700,725,750,775,800,825};
	private int[] fruitYPos= {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,
			600,625};
	private ImageIcon fruit;
	private Random random= new Random();
	private int xpos= random.nextInt(32);
	private int ypos= random.nextInt(22);
	
	private Timer timer;
	private int delay= 100;
	
	private ImageIcon titleImage;
	
	public GamePlay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		timer= new Timer(delay,this);
		timer.start();
		
	}
	
	public void paint (Graphics g) {
		if(moves ==0) {
			snakeXlength[0]=100;
			snakeXlength[1]=75;
			snakeXlength[2]=50;
			
			snakeYlength[0]=100;
			snakeYlength[1]=100;
			snakeYlength[2]=100;
		}
		
		titleImage= new ImageIcon("Title.png");
		titleImage.paintIcon(this, g, 16, 5);
		
		g.setColor(Color.DARK_GRAY);
		g.drawRect(25, 75, 850, 575);
		
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		g.setColor(Color.white);
		g.setFont(new Font("areal", Font.PLAIN, 14));
		g.drawString("Score:"+score, 780, 30);
		
		head= new ImageIcon("Head.png");
		head.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
		
		for(int i=0;i< lengthOfSnake;i++) {
			if(i==0) {
				head= new ImageIcon("Head.png");
				head.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
			} else {
				tail= new ImageIcon("Body.png");
				tail.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
				
			}
		}
		
		fruit= new ImageIcon("Fruit.png");
		
		if(fruitXPos[xpos]== snakeXlength[0] && fruitYPos[ypos]== snakeYlength[0]) {
			score+=5;
			lengthOfSnake++;
			xpos= random.nextInt(32);
			ypos= random.nextInt(22);
		}
		fruit.paintIcon(this, g, fruitXPos[xpos],fruitYPos[ypos]);
		
		for(int i=1;i<lengthOfSnake;i++) {
			if(snakeXlength[i]==snakeXlength[0] && snakeYlength[i]==snakeYlength[0]) {
				up= false; 
				down= false;
				right= false;
				left= false;
				
				g.setColor(Color.RED);
				g.setFont(new Font("areal",Font.BOLD,40));
				g.drawString("Game Over! Score:"+score, 250, 300);
				
				g.setColor(Color.RED);
				g.setFont(new Font("areal",Font.BOLD,20));
				g.drawString("Press enter to restart", 350, 340);
				
			}
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
		timer.restart();
		if(right) {
			for(int n= lengthOfSnake-1;n>=0;n--) {
				snakeYlength[n+1]= snakeYlength[n];
			}
			for(int n=lengthOfSnake-1;n>=0;n--) {
				if(n==0) {
					snakeXlength[0]=snakeXlength[0]+25;
				} else {
					snakeXlength[n]= snakeXlength[n-1];
				}
				if(snakeXlength[n]>850) {
					snakeXlength[n]=25;
				}
			}
			repaint();
		} else if(left) {
			for(int n= lengthOfSnake-1;n>=0;n--) {
				snakeYlength[n+1]= snakeYlength[n];
			}
			for(int n=lengthOfSnake-1;n>=0;n--) {
				if(n==0) {
					snakeXlength[0]=snakeXlength[0]-25;
				} else {
					snakeXlength[n]= snakeXlength[n-1];
				}
				if(snakeXlength[n]<25) {
					snakeXlength[n]=850;
				}
			}
			repaint();
		} else if(up) {
			for(int n= lengthOfSnake-1;n>=0;n--) {
				snakeXlength[n+1]= snakeXlength[n];
			}
			for(int n=lengthOfSnake-1;n>=0;n--) {
				if(n==0) {
					snakeYlength[0]=snakeYlength[0]-25;
				} else {
					snakeYlength[n]= snakeYlength[n-1];
				}
				if(snakeYlength[n]<75) {
					snakeYlength[n]=625;
				}
			}
			repaint();
		} else if(down) {
			for(int n= lengthOfSnake-1;n>=0;n--) {
				snakeXlength[n+1]= snakeXlength[n];
			}
			for(int n=lengthOfSnake-1;n>=0;n--) {
				if(n==0) {
					snakeYlength[0]=snakeYlength[0]+25;
				} else {
					snakeYlength[n]= snakeYlength[n-1];
				}
				if(snakeYlength[n]> 625) {
					snakeYlength[n]=75;
				}
			}
			repaint();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
			moves++;
			right= true;
			if(left) {
				right=false;
			}
			up= false;
			down= false;
		} else if(e.getKeyCode()== KeyEvent.VK_LEFT) {
			moves++;
			left= true;
			if(right) {
				left=false;
			}
			up= false;
			down= false;
		} else if(e.getKeyCode()== KeyEvent.VK_UP) {
			moves++;
			up= true;
			if(down) {
				up=false;
			}
			right= false;
			left= false;
		} else if(e.getKeyCode()== KeyEvent.VK_DOWN) {
			moves++;
			down= true;
			if(up) {
				down =false;
			}
			right= false;
			left= false;
		} 
		
		if(e.getKeyCode()== KeyEvent.VK_ENTER) {
			moves=0;
			score=0;
			lengthOfSnake=3;
			
			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
