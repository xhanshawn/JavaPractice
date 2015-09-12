package com.xuhan.jugglefest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JuggleFest {
	public static void main(String[] args){
		
		JuggleFest fest = new JuggleFest();
		fest.assign();
	}	
	
	private void assign(){

		ArrayList <Circuit> circuit_list = new ArrayList<>();
		ArrayList <Juggler> juggler_list = new ArrayList<>();
		
		try {
			InputStream is = new FileInputStream ("input.txt");
			BufferedReader reader = new BufferedReader ( new InputStreamReader(is) );
			StringBuffer out = new StringBuffer();
			
			String line = "";
			
			

			
			
			while((line = reader.readLine()) != null){

				
				String[] str = line.split(" ");
				
				if(str[0].equals("C")){
					int key = Integer.parseInt(str[1].substring(1, str[1].length()));
					int h = Integer.parseInt(str[2].substring(2, str[2].length()));
					int e = Integer.parseInt(str[3].substring(2, str[3].length()));
					int p = Integer.parseInt(str[4].substring(2, str[4].length()));
					Circuit new_circuit = new Circuit( key, h, e, p);
					
					circuit_list.add(new_circuit);
					
					
				}
				if(str[0].equals("J")){
					
					int key = Integer.parseInt(str[1].substring(1, str[1].length()));
					int h = Integer.parseInt(str[2].substring(2, str[2].length()));
					int e = Integer.parseInt(str[3].substring(2, str[3].length()));
					int p = Integer.parseInt(str[4].substring(2, str[4].length()));
					
					Juggler new_juggler = new Juggler(key, h, e, p);
					
					String[] c_list = str[5].split(",");
					
					for (int i=0; i<c_list.length; i++ ){
						
						int num = Integer.parseInt(c_list[i].substring(1, c_list[i].length()));
						
						new_juggler.addCircuits(circuit_list.get(num));
						
						circuit_list.get(num).addJugglers(new_juggler);
						
					
					}
					juggler_list.add(new_juggler);
					
					
				}
				
			}
			

			

			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuffer output = new StringBuffer();
		for (int i=0; i< circuit_list.size(); i++){
			circuit_list.get(i).sortJugglers();
			String line = circuit_list.get(i).getOutput();
			output.append(line + "\n");
		}
		
		int email_number = circuit_list.get(1970).getEmailNumber();
		
		System.out.print(""+ email_number);
		
		try {
			BufferedWriter writer = new BufferedWriter( new FileWriter("/Users/hanxu/Documents/output.txt"));
			writer.write(output.toString());
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
