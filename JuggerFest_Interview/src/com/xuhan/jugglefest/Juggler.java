package com.xuhan.jugglefest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Juggler {
	
	private int key;
	private int hand_to_eye;
	private int endurance;
	private int pizzazz;
	private ArrayList<Circuit> circuits = new ArrayList<>();
	private Map<Integer,Circuit> circuits_table = new HashMap<>();
	
	
	public Juggler(int _key, int _hand, int _end , int _pizz){
		
		
		key = _key;
		hand_to_eye = _hand;
		endurance = _end;
		pizzazz = _pizz;
		
	}
	
	public void addCircuits (Circuit _cir){
		
		circuits_table.put(_cir.getKey(),_cir);
		circuits.add(_cir);
	}
	
	
	public int getKey(){
		return key;
	}
	
	public int getValue(int _key){
		
		Circuit circuit = circuits_table.get(_key);
		
		return circuit.getH() * hand_to_eye + circuit.getE() * endurance + circuit.getP() * pizzazz;
	}
	

	public String getCircuitsValue(){
		
		StringBuffer output = new StringBuffer();
		
		for(int i=0; i<circuits.size()-1;i++){
			int current_key = circuits.get(i).getKey();
			output.append("C" + current_key + ":"+this.getValue(current_key) + " ");
		}
		int current_key = circuits.get(circuits.size()-1).getKey();
		output.append("C" + current_key + ":"+this.getValue(current_key));
		
		return output.toString();
		
	}
}
