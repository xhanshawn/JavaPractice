package SnakeGameV1_2;



public class Playsnake {
	final private int East=3;
	final private int South=6;
	final private int West=9;
	final private int North=12;
	final private int Snake_Length=10;
	final private int Snake_Speed=150;
	final private int PP_weight = Snake.PP_weight;
	final private int PP_height = Snake.PP_height;
	
	final private static int Blank=1;
	final private int Snake_Body=0;
	final private int Food=8;
	final private int Speed_up=30;
	final private int Speed_up_round=5;
	final private int Score_ratio=10;
	
	boolean over=false;
		
	public void play(Snakegui gui,int[] B){
		
		int[] s;
		int oldd=East;
		int length = Snake_Length;		
		int eat=Snake_Length;
		int speed=Snake_Speed;
		int round=0;
			
		s = new int[PP_weight*PP_height];
					
        while(length<900){
        	length = Snake_Length;
        	eat=Snake_Length;

        	for (int i=0; i<Snake_Length; i++){   //set for restart
				s[i]=length-1-i;			
			}
            
            while(!over){
				if(Snakegui.start){            //start
					
					gui.Print();
					int newd=oldd;		
				
					try{Thread.sleep(speed);    //move in a specific speed
					}
					catch(Exception ex){}						
					
					newd=gui.Input;			   //get action input
					
					oldd=turn(oldd,newd);
	            
					length=follow(oldd,s,length,B);
	            
					if(length>eat){             //set new food if snake 
						B[food(B)]=Food;
						eat=length;
						round++;
						if((round%Speed_up_round==0)&&(speed>-1)){
							speed-=(round%Speed_up_round+1)*Speed_up;
						}
					}
	            
				gui.Print();
				}
				try{Thread.sleep(50);
				}
				catch(Exception ex){}
			}
			over=false;
			Snakegui.start=false;
			gui.GameOver(round*Score_ratio);
		}
	}
		
	public static int food(int[]B){      //set food in the background
		boolean put=false;
		int p=0;
		
		while(!put){
			p=(int)(Math.random()*900);  //random location
			if(B[p]==Blank){
				put=true;				 //not in the location of snake
			}		
		}
		return p;
	}
		
		
	private int turn (int oldd, int newd){   //turn direction
		if(Math.abs(oldd-newd)==6){     //make sure direction is valid
				newd=oldd;
		}
				
	    switch(newd){      //turn
			case East: return East;	
			case South: return South;
			case West: return West;
			default: return North;						    
		}
			
	}
		
	private int follow (int dir, int[] s, int length, int[] b){
			//move the snake
		int former;    //head
		int end;       //end
			
		former=s[0];
		end=s[length-1]; 
			
		switch(dir){   //turn head based on specific direction
			case 3: {            
					if(((s[0]+1)%PP_weight==0)&&(former%PP_weight==PP_weight-1))
						s[0]-=PP_weight-1;
					else s[0]++;
					break;
				}
			
			case 9: {
					if(((s[0]-1)%PP_weight==PP_weight-1)&&(former%PP_weight==0))
						s[0]+=PP_weight-1;
					else s[0]--;	
					break;
				}
			case 6: {
					if((s[0]+PP_weight)>PP_weight*PP_height-1) s[0]-=PP_weight*(PP_height-1);
					else s[0]+=PP_weight;
					break;
				}
			case 12:{
					if((s[0]-PP_weight)<0)
						s[0]+=PP_weight*(PP_height-1);
					else s[0]-=PP_weight;
					break;
				}
				
		}
		    
		    
		if(b[s[0]]==0) over=true;     //eat itself
			
		if(b[s[0]]==8) {              //eat food
	        length++;
	        end=s[length-1];
	    }
			
		for (int i=1; i<length; i++){ //move the body
			int temp;
			temp=s[i];
			s[i]=former;
			former=temp;		
		}

		for(int i=0;i<length;i++){    
			b[s[i]]=Snake_Body;      
		}	
		
		b[end]=Blank;
			
		return length;     //update length
	}			
}
