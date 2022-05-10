package gameTesting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class gameTesting extends JPanel implements ActionListener, KeyListener, MouseListener{
	Timer timer = new Timer(5, this);
	Random random=new Random();
	double playerXPos=10; double playerYPos=440.0; //player location
	double vely=0.0; double Gravity=0.01; int playerHeight=7; int playerWidth=8; double playerArms=playerYPos; //player bounds
	int jumpingTimer=0; //counter for time jumping
	int mouseY=0; int mouseX=0;//int BorderCount=0;
	int mode3Count=0; int mode4Count=0; //mode counters
	int beforeCount=0; int allPointCount=5;
	int m41=0; int m42=1; int m43=2; int m44=3; int m45=6;//calls for array list
	static boolean jumped=false; static boolean pressed=false; //booleans for the jumping animation
	private boolean left=false; private boolean right=false; //movement booleans
	static boolean mode41=false; static boolean mode42=false; static boolean mode43=false; static boolean mode44=false; static boolean mode45=false;
	static boolean betweenLevel=false; int betweenCount=0;
	static boolean canQuit=false; static boolean map2=false;
	static boolean mode5=false; static boolean inLava=false;
	static boolean beforeGame=true; static boolean reset=false;
	static boolean lvl7=false; static boolean start=false;
	double tempPlayXPos=0; static boolean controlScreen=false;
	static boolean lvl8PressL=false; static boolean lvl8PressR=false;
	static boolean wasd=true;
	
	int[] pointx={28,455,892,294,1010,945,40};
	int[] pointy={125,325,85,125,1010,167,49};
	int level=0;
	int pointR=random.nextInt(4);
	
	//Activates all the action listeners
	public gameTesting() {
		timer.start();
		addKeyListener(this);
		addMouseListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	public void paintComponent(Graphics g) {
		
		if (beforeGame==true) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1000, 500);
			
			g.setColor(new Color(76,0,88));
			g.fillRect(87,110, 20, 90);
			g.fillRect(187,110, 20, 90);
			g.setColor(new Color(152,0,175));
			g.fillRect(107,110, 80, 70);
			g.fillRect(157,150, 30, 80);
			g.fillRect(107,150, 30, 80);
			g.fillRect(127,70, 40, 70);
			
			borders(g,739,197,120,40,1);
			borders(g,635,311,120,40,2);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Serif", Font.PLAIN, 40));
			g.drawRect(413, 244, 120, 40);
			g.drawString("Start", 435, 276);
			g.drawRect(413, 310, 120, 40);
			g.setFont(new Font("Serif", Font.PLAIN, 30));
			g.drawString("Controls", 423, 343);
			g.setFont(new Font("Serif", Font.PLAIN, 80));
			g.drawString("Level 1?", 346, 159);
			
			if ((mouseX>=413&&mouseX<=533)&&(mouseY>=244&&mouseY<=284)) {
				g.setColor(Color.GREEN);
				g.setFont(new Font("Serif", Font.PLAIN, 40));
				g.drawRect(413, 244, 120, 40);
				g.drawString("Start", 435, 276);
				if(beforeCount<=30) {
					beforeCount++;
					if (beforeCount>30) {
					beforeGame=false;
					beforeCount=0;
					}
				}
				
			}	
			else if ((mouseX>=413&&mouseX<=533)&&(mouseY>=310&&mouseY<=350)) {
				g.setColor(Color.GREEN);
				g.drawRect(413, 310, 120, 40);
				g.setFont(new Font("Serif", Font.PLAIN, 30));
				g.drawString("Controls", 423, 343);
				if(beforeCount<=30) {
					beforeCount++;
					if (beforeCount>30) {
						controlScreen=true;
						beforeCount=0;
					}
				}
			}
			if (controlScreen) {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, 1000, 500);
				
				g.setColor(Color.WHITE);
				g.drawRect(413, 61, 120, 40);
				g.setFont(new Font("Serif", Font.PLAIN, 30));
				g.drawString("Controls", 423, 94);
				g.setFont(new Font("Serif", Font.PLAIN, 40));
				g.drawRect(413, 375, 120, 40);
				g.drawString("Back", 434, 407);
				g.setFont(new Font("Serif", Font.PLAIN, 20));
				g.drawString("WASD", 348, 172);
				g.drawString("Arrows", 535, 172);
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(424, 160, 100, 15);
				if (wasd) {
					g.setColor(Color.DARK_GRAY);
					g.fillRect(424, 160, 40, 15);
					g.setColor(Color.BLACK);
					g.fillRect(432, 162, 2, 11);
					g.fillRect(442, 162, 2, 11);
					g.fillRect(452, 162, 2, 11);
				}
				else if (wasd==false) {
					g.setColor(Color.DARK_GRAY);
					g.fillRect(484, 160, 40, 15);
					g.setColor(Color.BLACK);
					g.fillRect(492, 162, 2, 11);
					g.fillRect(502, 162, 2, 11);
					g.fillRect(512, 162, 2, 11);
				}
				if ((mouseX>=424&&mouseX<=524)&&(mouseY>=160&&mouseY<=175)) {
					if (wasd) {
						wasd=false;
						mouseX=0;
					}
					else if (wasd==false) {
						wasd=true;
						mouseX=0;
					}
				}
				if ((mouseX>=413&&mouseX<=533)&&(mouseY>=375&&mouseY<=415)) {
					g.setColor(Color.GREEN);
					g.setFont(new Font("Serif", Font.PLAIN, 40));
					g.drawRect(413, 375, 120, 40);
					g.drawString("Back", 434, 407);
					if(beforeCount<=30) {
						beforeCount++;
						if (beforeCount>30) {
							controlScreen=false;
							beforeCount=0;
						}
					}
				}
			}
		}
		
		
		
	if (beforeGame==false)	{
		if (map2==false) {
			
		if (start==false) {
			playerYPos=438;
			start=true;
		}
			
		//background
		g.setColor(new Color(74,134,232));
		g.fillRect(0, 0, 1000, 500);
				
		//tips on how to beat level
		g.setColor(Color.BLACK);
		if (level==0&&wasd) {
			g.setFont(new Font("Serif", Font.PLAIN, 20));
			g.drawString("Move with WASD",217,248);
		}
		else if (level==0&&wasd==false) {
			g.setFont(new Font("Serif", Font.PLAIN, 20));
			g.drawString("Move with the arrow keys",217,248);
		}
		else if (level==1) {
			g.setFont(new Font("Serif", Font.PLAIN, 20));
			g.drawString("Inside out",217,248);
		}
		else if (level==2) {
			g.setFont(new Font("Serif", Font.PLAIN, 20));
			g.drawString("Alternate controls",217,248);
		}
		else if (level==3) {
			g.setFont(new Font("Serif", Font.PLAIN, 20));
			g.drawString("Moving target",217,248);
		}
		else if (level==4) {
			g.setFont(new Font("Serif", Font.PLAIN, 20));
			g.drawString("All together now",217,248);
			g.drawString(allPointCount+"/5",60,64);
		}
		

		//player
		g.setColor(new Color(76,0,88));
		g.fillRect((int)playerXPos-2,(int) (playerYPos+playerArms), playerWidth-6, playerHeight+2);
		g.fillRect((int)playerXPos+8,(int) (playerYPos+playerArms), playerWidth-6, playerHeight+2);
		g.setColor(new Color(152,0,175));
		g.fillRect((int)playerXPos,(int) playerYPos, playerWidth, playerHeight);
		g.fillRect((int)playerXPos+5,(int) playerYPos+4, playerWidth-5, playerHeight+1);
		g.fillRect((int)playerXPos,(int) playerYPos+4, playerWidth-5, playerHeight+1);
		g.fillRect((int)playerXPos+2,(int) playerYPos-4, playerWidth-4, playerHeight);
		
		//platforms
		borders(g,170,435,80,15,1);
		borders(g,110,385,60,15,1);
		borders(g,200,335,60,15,1);
		borders(g,110,285,60,15,1);
		borders(g,19,235,60,15,1);
		borders(g,171,185,313,15,1);
		borders(g,4,135,60,15,1);
		borders(g,530,235,60,15,1);
		borders(g,400,335,250,15,1);
		borders(g,677,285,60,15,1);
		borders(g,634,185,60,15,1);
		borders(g,753,145,60,15,1);
		borders(g,863,95,60,15,1);
		borders(g,775,234,60,15,1);
		borders(g,685,390,60,15,1);
		borders(g,270,135,60,15,1);
		borders(g,0,450,1000,50,1);

		
		//stretches player to look like moving slow
		if (vely<=0.0) {
			playerArms=0;
		}
				
		//stretches out player to look like moving fast
		if (vely>=1.0||jumped) {
			playerArms=-8;
		}
		
		//point squares
		if (level!=4) {
		g.setColor(new Color(225,225,1));
		g.fillRect(pointx[pointR], pointy[pointR], 10, 10);
		}
		
		//point square mechanics
		if ((playerXPos>=pointx[pointR]-8&&playerXPos<=pointx[pointR]+10)&&(playerYPos>=pointy[pointR]-2&&playerYPos<=pointy[pointR]+10)&&level!=4) {
			level++;
			playerXPos=10.0; playerYPos=440.0;
			pointR=random.nextInt(4);
			betweenLevel=true;
		}
		
		//third gamemode
		if (level==3) {
			mode3Count++;
			if(mode3Count>=100) {
				pointR=random.nextInt(4);
				mode3Count=0;
			}
		}
		
		//gamemode 4
		if (level==4) {
			g.setColor(new Color(225,225,1));
			g.fillRect(pointx[m41], pointy[m41], 10, 10);
			g.fillRect(pointx[m42], pointy[m42], 10, 10);
			g.fillRect(pointx[m43], pointy[m43], 10, 10);
			g.fillRect(pointx[m44], pointy[m44], 10, 10);
			g.fillRect(pointx[m45], pointy[m45], 15, 15);
			if ((playerXPos>=pointx[m41]&&playerXPos<=pointx[m41]+10)&&(playerYPos>=pointy[m41]-2&&playerYPos<=pointy[m41]+10)&&mode41==false){
				mode41=true;
				m41=4;
				allPointCount--;
			}
			else if ((playerXPos>=pointx[m42]&&playerXPos<=pointx[m42]+10)&&(playerYPos>=pointy[m42]-2&&playerYPos<=pointy[m42]+10)&&mode42==false){
				mode42=true;
				m42=4;
				allPointCount--;
			}
			else if ((playerXPos>=pointx[m43]&&playerXPos<=pointx[m43]+10)&&(playerYPos>=pointy[m43]-2&&playerYPos<=pointy[m43]+10)&&mode43==false){
				mode43=true;
				m43=4;
				allPointCount--;
			}
			else if ((playerXPos>=pointx[m44]&&playerXPos<=pointx[m44]+10)&&(playerYPos>=pointy[m44]-2&&playerYPos<=pointy[m44]+10)&&mode44==false){
				mode44=true;
				m44=4;
				allPointCount--;
			}
			else if ((playerXPos>=pointx[m45]&&playerXPos<=pointx[m45]+15)&&(playerYPos>=pointy[m45]-2&&playerYPos<=pointy[m45]+15)&&mode45==false){
				mode45=true;
				m45=4;
				allPointCount--;
			}
			if (mode44&&mode43&&mode42&&mode41&&mode45) {
				level++;
				betweenLevel=true;
			}
		}
		
		//prevents player leaving screen
		if (playerXPos<=0.0) {
			playerXPos=0.0;
		}		
		if (playerXPos>=990.0) {
			playerXPos=990.0;
		}
		
		//checks for win
		if (level>4) {
			map2=true;
		}
		}
		
		
		if (map2==true) {
			//spawns player in right spot
			if (mode5==false) {
				playerXPos=26;
				playerYPos=274;
				mode5=true;
			}
			
			//background
			g.setColor(new Color(169,169,169));
			g.fillRect(0, 0, 1000, 500);
			
			//player
			g.setColor(new Color(76,0,88));
			g.fillRect((int)playerXPos-2,(int) (playerYPos+playerArms), playerWidth-6, playerHeight+2);
			g.fillRect((int)playerXPos+8,(int) (playerYPos+playerArms), playerWidth-6, playerHeight+2);
			g.setColor(new Color(152,0,175));
			g.fillRect((int)playerXPos,(int) playerYPos, playerWidth, playerHeight);
			g.fillRect((int)playerXPos+5,(int) playerYPos+4, playerWidth-5, playerHeight+1);
			g.fillRect((int)playerXPos,(int) playerYPos+4, playerWidth-5, playerHeight+1);
			g.fillRect((int)playerXPos+2,(int) playerYPos-4, playerWidth-4, playerHeight);
					
			//stretches out player to look like moving fast
			if (vely>=1.0) {
				playerArms=-8;
			}
			if (vely<=0.01) {
				playerArms=0;
			}
			
			//prevents player leaving screen
			if (playerXPos<=0.0) {
				playerXPos=0.0;
			}		
			if (playerXPos>=990.0) {
				playerXPos=990.0;
			}
			
			//point square
			g.setColor(new Color(225,225,1));
			g.fillRect(945, 167, 10, 10);
			if ((playerXPos>=945&&playerXPos<=955)&&(playerYPos>=165&&playerYPos<=177)) {				
				level++;
				mode5=false;
				betweenLevel=true;
			}
			
			//changes borders to invisble
			int i=3;			
			if (lvl7==true) {
				i=2;
			}
			
			if (level!=7) {
			//platforms
			borders(g,5,284,60,15,2);
			borders(g,136,284,120,15,2);
			borders(g,314,430,10,15,2);
			borders(g,414,430,10,15,2);
			borders(g,514,430,10,15,2);
			borders(g,614,430,10,15,2);
			borders(g,714,430,10,15,2);
			borders(g,801,384,60,15,2);
			borders(g,875,349,60,15,2);
			borders(g,800,300,60,15,2);
			borders(g,874,254,60,15,2);
			borders(g,803,215,60,15,2);
			borders(g,898,177,120,15,2);
			}
			else if (level==7) {
			//invisble platforms
			borders(g,5,284,60,15,i);
			borders(g,136,284,120,15,i);
			borders(g,314,430,10,15,i);
			borders(g,414,430,10,15,i);
			borders(g,514,430,10,15,i);
			borders(g,614,430,10,15,i);
			borders(g,714,430,10,15,i);
			borders(g,801,384,60,15,i);
			borders(g,875,349,60,15,i);
			borders(g,800,300,60,15,i);
			borders(g,874,254,60,15,i);
			borders(g,803,215,60,15,i);
			borders(g,898,177,120,15,i);
			}
			
			//creates lava
			lava (g,0,440,1000,40);		
			
			//level tips
			g.setColor(Color.BLACK);
			if (level==5&&inLava==false&&wasd) {
				g.setFont(new Font("Serif", Font.PLAIN, 20));
				g.drawString("Move with WASD",412,157);
			}
			else if (level==5&&inLava==false&&wasd==false) {
				g.setFont(new Font("Serif", Font.PLAIN, 20));
				g.drawString("Move with arrow keys",412,157);
			}
			else if (level==6&&inLava==false) {
				g.setFont(new Font("Serif", Font.PLAIN, 20));
				g.drawString("Inside out... (Again?)",412,157);
			}
			else if (level==7&&reset==false&&inLava==false) {
				g.setFont(new Font("Serif", Font.PLAIN, 20));
				g.drawString("Good Luck",412,157);
			}			
			else if (level==7&&reset==true&&inLava==false) {
				g.setFont(new Font("Serif", Font.PLAIN, 20));
				g.drawString("Good Luck (maybe 2 buttons pressed will reveal the platforms)",250,157);
			}
			else if (level==8&&inLava==false) {
				g.setFont(new Font("Serif", Font.PLAIN, 20));
				g.drawString("Now seriously GOOD LUCK!",412,157);
			}
			
			//win
			if (level>8) {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, 1000, 500);
				g.setColor(Color.WHITE);
				g.setFont(new Font("Serif", Font.PLAIN, 20));
				g.drawString("You Win", 420, 172);
				g.drawString("press 'Q' to quit", 395, 250);
				g.setColor(Color.YELLOW);
				g.fillOval(428, 298, 25, 25);
				g.setColor(Color.BLACK);
				g.fillOval(431, 301, 20, 20);
				g.setColor(Color.YELLOW);
				g.fillOval(467, 298, 25, 25);
				g.setColor(Color.BLACK);
				g.fillOval(469, 301, 20, 20);
				g.setColor(Color.YELLOW);
				g.fillOval(440, 295, 40, 40);
				g.fillRect(441, 295, 39, 20);
				g.fillRect(456, 335, 8, 10);
				g.fillRect(447, 345, 26, 7);
				canQuit=true;
			}
			tempPlayXPos=playerXPos;
		}
		//between level screen
				if (betweenLevel==true&&level<=7) {
					if (betweenCount<=200) {
					g.setColor(Color.BLACK);
					g.fillRect(0, 0, 1000, 500);
					g.setColor(Color.WHITE);
					g.setFont(new Font("Serif", Font.PLAIN, 20));
					g.drawString("Level 1... (again?)", 420, 250);
					betweenCount++;
					}
					if (betweenCount>=200) {
						betweenCount=0;
						betweenLevel=false;
					}
				}
		}
	}

	
	
	
	//class to make borders
	public void borders(Graphics g, int borderX, int borderY, int borderWid, int borderHeight, int mode) {
		if (mode==1) {
		g.setColor(new Color(104,76,0));
		g.fillRect(borderX, borderY, borderWid, borderHeight);
		// A simple triangle.
		int T1x[]= {(borderX+(borderWid/24)),(borderX+(borderWid/2)),(borderX+(borderWid/4))};
		int T1y[]= {(borderY+borderHeight),(borderY+borderHeight),(borderY+borderHeight+10)};
		int T2x[]= {(borderX+(borderWid/2)),(borderX+borderWid),(borderX+((borderWid/6)*5))};
		int T2y[]= {(borderY+borderHeight),(borderY+borderHeight),(borderY+borderHeight+16)};
		int n = 3;
		Polygon p1 = new Polygon(T1x, T1y, n);
		Polygon p2 = new Polygon(T2x, T2y, n);
		g.fillPolygon(p1); 
		g.fillPolygon(p2); 
		g.setColor(new Color(12,149,44));
		g.fillRect(borderX, borderY, borderWid, 7);
		}
		else if (mode==2) {
			g.setColor(new Color(164,55,0));
			g.fillRect(borderX, borderY, borderWid, borderHeight);
			g.setColor(new Color(212,212,212));
			g.drawLine(borderX,(borderY+(borderHeight/2)),(borderX+borderWid-1),(borderY+(borderHeight/2)));
			g.drawLine((borderX+(borderWid/3)),borderY,(borderX+(borderWid/3)),(borderY+(borderHeight/2)));
			g.drawLine((borderX+((borderWid/3)*2)),borderY,(borderX+((borderWid/3)*2)),(borderY+(borderHeight/2)));
			g.drawLine((borderX+(borderWid/6)),(borderY+(borderHeight/2)),(borderX+(borderWid/6)),(borderHeight+borderY)-1);
			g.drawLine((borderX+((borderWid/6)*3)),(borderY+(borderHeight/2)),(borderX+((borderWid/6)*3)),(borderHeight+borderY)-1);
			g.drawLine((borderX+((borderWid/6)*5)),(borderY+(borderHeight/2)),(borderX+((borderWid/6)*5)),(borderHeight+borderY)-1);
		}
		else if (mode==3) {}
		if ((playerXPos>=(borderX-playerWidth)&&playerXPos<=(borderX+borderWid))&&(playerYPos<=(borderY+borderHeight-12)&&playerYPos>=borderY-12)){
			vely=0.0;
			Gravity=0.0;
			playerYPos=borderY-12;
			jumpingTimer=0;
			jumped=false;
			pressed=false;
		}	
		else if ((playerXPos>=(borderX-playerWidth)&&playerXPos<=(borderX+borderWid))&&(playerYPos<=(borderY+borderHeight)&&playerYPos>=borderY+5)) {
			Gravity=0.01;
			vely=0.0;
			playerYPos=(borderY+borderHeight+3);
			jumpingTimer=0;
			jumped=false;
		}
		else {
			Gravity=0.01;
		}
	}
	
	public void lava(Graphics g ,int lavaX,int lavaY, int lavaWid,int lavaHeight) {
		g.setColor(Color.RED);
		g.fillRect(lavaX, lavaY, lavaWid, lavaHeight);
		if ((playerXPos>=(lavaX-playerWidth)&&playerXPos<=(lavaX+lavaWid))&&(playerYPos<=(lavaY+lavaHeight-12)&&playerYPos>=lavaY-12)){
			Gravity=0.01;
			vely=0.0;
			pressed=true;
			inLava=true;
		}
		if ((playerYPos<=(lavaY)&&playerYPos>=lavaY+lavaHeight)) {
			vely=0.0;
			Gravity=0.0;
		}		
		if (inLava) {
			playerArms=-8;
			g.setColor(Color.BLACK);
			g.drawString("You Lose", 412, 157);
			g.drawString("press 'Q' to quit", 405, 172);
			g.drawString("press 'R' to try again", 395, 187);
			canQuit=true;
			playerXPos=tempPlayXPos;
		}
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		
		//rollover key movement
		if (left&&jumped) {
        	playerXPos-=0.3;
        }
		if (right&&jumped) {
        	playerXPos+=0.3;
        }		
		if (right==true) {
			playerXPos+=1.0;
		}
		if (left==true) {
			playerXPos-=1.0;
		}
		if (left==true&&right==true&&level==7&&betweenLevel==false) {
			lvl7=true;
		}
				
		//jumping animation
		if (jumped==true&&jumpingTimer<=60&&pressed==true) {	
			vely=1.0;
			Gravity=0.01;
			playerYPos=playerYPos-vely;
			vely=vely-Gravity;			
			jumpingTimer++;			
			if (jumpingTimer>=60) {
				vely=0.0;
				jumped=false;
				jumpingTimer=0;
			}
		}	
		if (jumped==false) {
		vely=vely+Gravity;
		playerYPos=playerYPos+vely;
		}
		
		repaint();
	}

	public void keyTyped(KeyEvent e) {}

	//checking user movement input
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if(wasd) {
		if (c == KeyEvent.VK_W&&level!=2&&pressed==false) {	
			Gravity=0.0;
			vely=0.0;
			pressed=true;
			jumped=true;
		}
		if (c == KeyEvent.VK_D) {
			if ((level==0||level==3||level==4||level==5||level==7)&&inLava==false) {
			right=true;
			}
			else if ((level==1||level==6)&&inLava==false) {
				left=true;
			}
			else if (level==8&&inLava==false) {
				if (lvl8PressR) {
					left = true;
				}
				else if (lvl8PressR==false) {
					right=true;
				}
			}
		}
		
		else if (c == KeyEvent.VK_A) {
			if ((level==0||level==3||level==4||level==5||level==7)&&inLava==false) {
			left = true;
			}
			else if ((level==1||level==6)&&inLava==false) {
				right=true;
			}
			else if (level==8&&inLava==false) {
				if (lvl8PressL) {
					right = true;
				}
				else if (lvl8PressL==false) {
					left=true;
				}
			}
		}
		}
		if(wasd==false) {
			if (c == KeyEvent.VK_UP&&level!=2&&pressed==false) {	
				Gravity=0.0;
				vely=0.0;
				pressed=true;
				jumped=true;
			}
			if (c == KeyEvent.VK_RIGHT) {
				if ((level==0||level==3||level==4||level==5||level==7)&&inLava==false) {
				right=true;
				}
				else if ((level==1||level==6)&&inLava==false) {
					left=true;
				}
				else if (level==8&&inLava==false) {
					if (lvl8PressR) {
						left = true;
					}
					else if (lvl8PressR==false) {
						right=true;
					}
				}
			}			
			else if (c == KeyEvent.VK_LEFT) {
				if ((level==0||level==3||level==4||level==5||level==7)&&inLava==false) {
				left = true;
				}
				else if ((level==1||level==6)&&inLava==false) {
					right=true;
				}
				else if (level==8&&inLava==false) {
					if (lvl8PressL) {
						right = true;
					}
					else if (lvl8PressL==false) {
						left=true;
					}
				}
			}
		}
		if (c == KeyEvent.VK_Q&&canQuit==true) {	
			System.exit(0);
		}
//		else if (c == KeyEvent.VK_P) {	
//			level=5;
//		}	
//		else if (c == KeyEvent.VK_L) {	
//			level++;
//		}
		else if (c == KeyEvent.VK_R&&canQuit==true) {	
			canQuit=false;
			inLava=false;
			mode5=false;
			lvl7=false;
			if (level==7) {
			reset=true;
			}
			lvl8PressL=false;
			lvl8PressR=false;
		}
	}
	
	
	//checking if player stops moving
	public void keyReleased(KeyEvent e) {
		if (wasd) {
		if (e.getKeyCode() == KeyEvent.VK_A) {
			left = false;
			right = false;
			if (level==8) {
			if (lvl8PressL) {
				lvl8PressL=false;
			}
			else if (lvl8PressL==false) {
				lvl8PressL=true;
			}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			right = false;
			left = false;
			if (level==8) {
			if (lvl8PressR) {
				lvl8PressR=false;
			}
			else if (lvl8PressR==false) {
				lvl8PressR=true;
			}
			}
		}
		}
		else if (wasd==false) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				left = false;
				right = false;
				if (level==8) {
				if (lvl8PressL) {
					lvl8PressL=false;
				}
				else if (lvl8PressL==false) {
					lvl8PressL=true;
				}
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				right = false;
				left = false;
				if (level==8) {
				if (lvl8PressR) {
					lvl8PressR=false;
				}
				else if (lvl8PressR==false) {
					lvl8PressR=true;
				}
				}
			}
		}
	}

	public void mousePressed(MouseEvent e) {
		mouseX=e.getX();
		mouseY=e.getY();
		System.out.println("cords: "+mouseX+" , "+mouseY);
		if (level==2) {
			playerXPos=e.getX();
			playerYPos=e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
	
	public void mouseClicked(MouseEvent e) {}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame application = new JFrame();
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		application.setLocation(214, 250);

		gameTesting myPanel = new gameTesting();

		application.add(myPanel);

		application.setSize(1000, 500);

		application.setVisible(true);
	}
}