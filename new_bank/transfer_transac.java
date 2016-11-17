package new_bank;

public class transfer_transac extends atm_transactions{

	transfer_transac(String name1,int amt1){
		
		amount=amt1;
		cust_name=name1;
		type="transfer";
	}
}
