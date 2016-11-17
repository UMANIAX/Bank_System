package new_bank;

public class deposit_transac extends atm_transactions{
	
	deposit_transac(String name1,int amt1){
		
		amount=amt1;
		cust_name=name1;
		type="deposit";
	}
}
