package new_bank;

public class withdrawal_transac extends atm_transactions{
	
	withdrawal_transac(String name1,int amt1){
		
		amount=amt1;
		cust_name=name1;
		type="withdrawal";
	}
}
