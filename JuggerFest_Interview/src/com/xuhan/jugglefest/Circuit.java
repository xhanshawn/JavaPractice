package com.xuhan.jugglefest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class Circuit {
	
	private int key;
	private int hand_to_eye;
	private int endurance;
	private int pizzazz;
	private ArrayList<Juggler> jugglers = new ArrayList<>();
	
	public Circuit(int _key, int _hand, int _end , int _pizz){
		
		key = _key;
		hand_to_eye = _hand;
		endurance = _end;
		pizzazz = _pizz;
		
	}
	
	public void addJugglers (Juggler _jug){
		
		jugglers.add(_jug);
		
	}
	
	public int getKey(){
		return key;
	}
	
	public int getH(){
		return hand_to_eye;
		
	}
	public int getE(){
		return endurance;
	}
	public int getP(){
		return pizzazz;
	}
	
	
	public void sortJugglers(){
		
		Collections.sort( jugglers , new Comparator<Juggler>(){

			@Override
			public int compare(Juggler j1, Juggler j2) {
				// TODO Auto-generated method stub
				
				int diff = j1.getValue(key) - j2.getValue(key);
					
				return diff * (-1) ;

			}
	
		});
		
	}
	
	public ArrayList<Juggler> getJugglers(){
		return jugglers;
	}
	
	public String getOutput(){
		
		StringBuffer output = new StringBuffer();
		output.append("C" + key + " "); 
		for (int i=0; i<5; i++){
			output.append("J" + jugglers.get(i).getKey() + " ");
			output.append(jugglers.get(i).getCircuitsValue() + ", ");
		}
		output.append("J" + jugglers.get(5).getKey() + " ");
		output.append(jugglers.get(5).getCircuitsValue() );
		
		
		return output.toString();
	}
	
	public int getEmailNumber(){
		int email_number = 0;
		for (int i=0; i<6; i++){
			email_number += jugglers.get(i).getKey();
		}

		return email_number;
	}
	
}
