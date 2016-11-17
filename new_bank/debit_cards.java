package new_bank;

import java.util.*;
import java.io.*;

public class debit_cards extends customer{

	int pin;
	
	debit_cards(String temp_name,int cardnum,int temp_pin){
		
		name=temp_name;
		pin=temp_pin;
		card_num=cardnum;
	}
	
	int change_pin() throws IOException{
		
		System.out.print("Enter new pin");
		BufferedReader bucky=new BufferedReader(new InputStreamReader(System.in));
		int pin=Integer.parseInt(bucky.readLine());
		this.pin=pin;
		return pin;
	}
}
