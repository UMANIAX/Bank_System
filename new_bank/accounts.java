package new_bank;

import java.util.*;
import java.io.*;

public class accounts extends customer{

	BufferedReader bucky=new BufferedReader(new InputStreamReader(System.in));
	
	int balance;
	
	int credit_money() throws IOException{
		
		System.out.print("Enter the amount of cash to be credited:");
		int amount=Integer.parseInt(bucky.readLine());
		balance+=amount;
		return amount;
	}
	
	int debit_money(int amount){
		
		if(balance>=amount){
			
			balance-=amount;
			return 0;
		}
		
		return 1;
	}
}
