package new_bank;

public class pin_validation_transac extends atm_transactions{

	pin_validation_transac(String name1,int pin1){
		
		cust_name=name1;
		type="pin_valid";
		pin_num=pin1;
	}
}
