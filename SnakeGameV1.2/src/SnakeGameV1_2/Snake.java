package SnakeGameV1_2;
import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.*;

public class Snake {
	
	private static int[] B;
	
	final static int PP_weight=30;
	final static int PP_height=30;
	final private static int Blank=1;
	
	public static void main(String[] args){
		try {                                  //use Nimbus
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			} catch (Exception e) {
			e.printStackTrace();
			}
		
		B = new int[PP_weight*PP_height];
		for (int i=0; i<PP_weight*PP_height; i++){		
			B[i]=Blank;
		}
		
	    Snakegui snakegui = new Snakegui(B);

		snakegui.gui();                        //GUI
	
		Playsnake playsnake = new Playsnake();
		
	    playsnake.play(snakegui, B);           //play
		
		
				
	}
	
}


class Snakegui extends JFrame{                 //class for GUI
	
	
	PlayPanel PlayPanel;
	JLabel message;
	final private int East=3;
	final private int South=6;
	final private int West=9;
	final private int North=12;
	final private int Frame_weight=400;
	final private int Frame_height=530;
	final private int PP_weight = Snake.PP_weight;
	final private int PP_height = Snake.PP_height;
	final private int MainPanel_height=80;
	final private int Message_height=30;
	
	final private int Blank=1;
	final private int Food=8;
	
	int Input=East;
    static boolean start=false;

    static int[] B;     //Grid Background position
    
    
    Snakegui(int[] BB){
   
    	B = BB;
    	
    }
    
    
    
	public void gui(){
        
		JPanel main = new JPanel();           //Control Panel
		
		main.setBackground(Color.white);
		main.setPreferredSize(new Dimension(Frame_weight,MainPanel_height));
		main.setBorder(BorderFactory.createTitledBorder("Control"));
		
		
		JButton Start = new JButton("Start"); //Buttons on main
		JButton About = new JButton("About");
		
		main.add(Start);
		main.add(About);
		Start.addActionListener(new ActionListener(){      //action listener for button start
			@Override
			public void actionPerformed(ActionEvent event){
				for (int i=10; i<PP_weight*PP_height; i++){		
	        		B[i]=Blank;
	        	}
				B[Playsnake.food(B)]=Food;
				Snakegui.start = true;
				message.setText("Game start!");
			}
		});
		
		About.addActionListener(new ActionListener(){      //action listener for button start
			@Override
			public void actionPerformed(ActionEvent event){
				JOptionPane.showMessageDialog(null,"Snake Game v1.1 beta\nDesigned by Shawn Han.\n"
						+ "If you find any bug or want to give me advices, please contact me, thank you!\n"
						+ "Email: shawnxhan@outlook.com\n"
						+ "Enjoy this game!", "Snake Game v1.1",JOptionPane.PLAIN_MESSAGE);
				message.setText("About");
			}
		});
		
		
		PlayPanel = new PlayPanel();      //Panel for playing
		
		this.getContentPane().add(PlayPanel,BorderLayout.CENTER);
		
	   	
		keylistener keylistener = new keylistener();
		this.addKeyListener(keylistener);
		Start.addKeyListener(keylistener);
		About.addKeyListener(keylistener);
		PlayPanel.addKeyListener(keylistener);
		main.addKeyListener(keylistener);
	
		JPanel MessagePanel = new JPanel();
		MessagePanel.setBackground(Color.white);
		MessagePanel.setPreferredSize(new Dimension(Frame_weight,Message_height));
		
		message = new JLabel();
		MessagePanel.add(message);
		
		this.getContentPane().add(MessagePanel,BorderLayout.SOUTH);
		
		this.getContentPane().add(main,BorderLayout.NORTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(Frame_weight,Frame_height);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void Print(){		

		PlayPanel.repaint();	     //update UI
		
	}
	public void GameOver(int score){
		JOptionPane.showMessageDialog(null,"Game over! Score is "+score, "Game Over!",JOptionPane.PLAIN_MESSAGE);
		message.setText("Game Over!");
	}
	
	
	private class PlayPanel extends JPanel{   //class for PlayPanel
		private String ImagePath="/image/background.png";
		@Override
		public void paintComponent(Graphics gs){
			Graphics2D g = (Graphics2D) gs;
			super.paintComponent(g);
			
			Image image=Toolkit.getDefaultToolkit().getImage(getClass().getResource(ImagePath));
			g.drawImage(image,0,0,400,420,this);
			
			for(int i=0; i<PP_height ; i++){
				for(int j=0; j<PP_weight; j++){				
				
					if(B[i*PP_weight+j]==1){         //space without snake and food
						
						
					}
					
					if((B[i*PP_weight+j]==0)|(B[i*PP_weight+j]==8)){   //space with snake and food
						g.setColor(Color.BLACK);
						g.fillRect(12*j+20, 12*i+20, 10, 10);
					}
				}
			}
			
		}	
	}
	

	
	private class keylistener implements KeyListener{
		String ainput;
		
		public void keyPressed(KeyEvent event){
			
		Map <String,Integer> input = new Hashtable<String, Integer>();
			input.put("W", North);
			input.put("D", East);
			input.put("S", South);
			input.put("A", West);
		ainput=String.format(KeyEvent.getKeyText(event.getKeyCode()));
		if(ainput.equals("W")|ainput.equals("A")|ainput.equals("S")|ainput.equals("D")){
	
	    Input=input.get(ainput);

		}
		}
		public void keyReleased(KeyEvent event){
		
		}
        public void keyTyped(KeyEvent event){
        
		}
	}
}
