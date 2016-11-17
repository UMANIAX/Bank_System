package new_bank;

import java.util.*;

public class atm_info {

	String admin_id="UMANIAX",admin_password="imadmin";
	int cash_available=10000000;
	int max_limit=10000;
	
	static ArrayList<atm_transactions> atm_transac_obj=new ArrayList<atm_transactions>();
	
	static void show_transac(String temp_name){
		
		for(int i=0;i<atm_transac_obj.size();i++){
			
			if(atm_transac_obj.get(i).cust_name.equals(temp_name)){
				
				if(atm_transac_obj.get(i).type=="withdrawal"||atm_transac_obj.get(i).type=="deposit"||atm_transac_obj.get(i).type=="transfer")
					System.out.println("Type:"+atm_transac_obj.get(i).type+"Amount:"+atm_transac_obj.get(i).amount);
				
				if(atm_transac_obj.get(i).type=="pin_valid")
					System.out.println("Type:"+atm_transac_obj.get(i).type);
			}
					
		}
	}
}
