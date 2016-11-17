package new_bank;

//Amount getting credited not for the same account. e.g. if i have to credit 500 rs in savings then its getting credited in current account.
//create admin login

import java.io.*;
import java.util.*;

public class bank_branch {

	static BufferedReader bucky=new BufferedReader(new InputStreamReader(System.in));
	
	static ArrayList<debit_cards> debit_card_obj=new ArrayList<debit_cards>();
	static ArrayList<savings_acount> savings_account_obj=new ArrayList<savings_acount>();
	static ArrayList<current_account> current_account_obj=new ArrayList<current_account>();
	static atm_info atm_info_obj=new atm_info();
	static String temp_name;
	
	static void create_account() throws IOException{
		
		System.out.print("Your Good name:");
		temp_name=bucky.readLine();
		System.out.print("Enter your residential address:");
		String temp_address=bucky.readLine();
		System.out.print("Enter date of birth (DD/MM/YY):");
		String temp_dob=bucky.readLine();
		
		int temp_cardnum;
		
		while(true){
		
			int check=0;
			
			temp_cardnum=(int) (Math.random()*99999999);
		
			for(int i=0;i<debit_card_obj.size();i++){
		
				if(debit_card_obj.get(i).card_num==temp_cardnum)
					check=1;
			}
			
			if(check==0)
				break;
		}
		
		System.out.println("Your Debit Card number is:"+temp_cardnum);
		
		System.out.print("Enter your pin:");
		int temp_pin=Integer.parseInt(bucky.readLine());
		
		savings_account_obj.add(new savings_acount(temp_name,temp_address,temp_dob,temp_cardnum));
		current_account_obj.add(new current_account(temp_name,temp_address,temp_dob,temp_cardnum));
		debit_card_obj.add(new debit_cards(temp_name,temp_cardnum,temp_pin));
	}
	
	public static void main(String [] args) throws IOException{
		
		BufferedReader bucky_main=new BufferedReader(new InputStreamReader(System.in)); 
		
		while(true){
		
			System.out.print("Select an option:\n-Enter '1' to login.\n-Enter '2' to create account.\nChoose your option:");
			int the_ultimate_choice=Integer.parseInt(bucky_main.readLine());
		
			switch(the_ultimate_choice){
			
				case 1:	
			
					int considered_index=0; //the index of the customer in the corresponding arraylist that we are concerned about.
					
					System.out.print("Enter card number:");
					int temp_cnum=Integer.parseInt(bucky_main.readLine());
					
					System.out.print("Enter pin");
					int temp_cpin=Integer.parseInt(bucky_main.readLine());
					
					int check_pin=0;
					
					for(int i=0;i<debit_card_obj.size();i++){
						
						if(temp_cnum==debit_card_obj.get(i).card_num)
							if(temp_cpin==debit_card_obj.get(i).pin){
								considered_index=i;
								check_pin=1;
							}
					}
					
					if(check_pin==0){
						
						System.out.println("The card number or pin number entered is wrong.\n");
						break;
					}
					
					while(true){
			
						int looping=0;
						
						System.out.print("Select from following operations:\n-Enter '1' to Debit Money.\n-Enter '2' to Credit Money.\n-Enter '3' to Show your transactions.\n-Enter '4' to Change Pin.\n-Enter '5' to Transfer money amongst accounts.\n-Enter '6' to Show Balance.\n-Enter '7' to Sing Out.\nChoose your option:");
						int main_choice=Integer.parseInt(bucky_main.readLine());
						
						switch(main_choice){
						
							case 2:
								System.out.print("Choose an account for crediting your money into:\n-Enter '1' to choose Current Account.\n-Enter '2' to choose Savings Account.\nChoose your option:");
								
								int credit_option=Integer.parseInt(bucky_main.readLine());
								
								switch(credit_option){
								
									case 1:
										int temp_amount=current_account_obj.get(considered_index).credit_money();
										atm_info_obj.atm_transac_obj.add(new deposit_transac(temp_name,temp_amount));
										break;
										
									case 2:
										temp_amount=current_account_obj.get(considered_index).credit_money();
										atm_info_obj.atm_transac_obj.add(new deposit_transac(temp_name,temp_amount));
										break;
										
									default:
										
										System.out.println("Wrong option, Select Again.\n\n\n\n\n");
								}
								
								break;
								
								
							case 1:
								
								System.out.print("Choose an account for withdrawing your money from:\n-Enter '1' to choose Current Account.\n-Enter '2' to choose Savings Account.\nChoose your option:");
								
								int debit_option=Integer.parseInt(bucky_main.readLine());
								
								System.out.println("Enter the amount of withdrawal");
								int temp_amount=Integer.parseInt(bucky_main.readLine());
								
								if(temp_amount<=atm_info_obj.cash_available&&temp_amount<=atm_info_obj.max_limit){
									
									switch(debit_option){
									
										case 1:
											int if_trans=current_account_obj.get(considered_index).debit_money(temp_amount);
											if(if_trans==0)
												atm_info_obj.atm_transac_obj.add(new withdrawal_transac(temp_name,temp_amount));
											else
												System.out.println("This transaction cannot be completed because you dont have enough balance");
											break;
											
										case 2:
											if_trans=current_account_obj.get(considered_index).debit_money(temp_amount);
											if(if_trans==0)
												atm_info_obj.atm_transac_obj.add(new withdrawal_transac(temp_name,temp_amount));
											else
												System.out.println("This transaction cannot be completed because you dont have enough balance");
											break;
											
										default:
											
											System.out.println("Wrong option, Select Again.\n\n\n\n\n");
									}
								}
								
								else
									System.out.println("This transactiom cannot be completed because either the atm machine in out of cash or the requested amount is more than the maximum limit");
								
								break;
								
							case 3:
								
								atm_info.show_transac(savings_account_obj.get(considered_index).name);
								
								break;
								
							case 4:
								
								int tran_pin=debit_card_obj.get(considered_index).change_pin();
								atm_info_obj.atm_transac_obj.add(new pin_validation_transac(savings_account_obj.get(considered_index).name,tran_pin));
								
								break;
								
							case 5:
								
								System.out.print("Choose an account from which money would be transferred:\n-Enter '1' to choose Current Account.\n-Enter '2' to choose Savings Account.\nChoose your option:");
								
								int trans_option=Integer.parseInt(bucky_main.readLine());
								
								switch(trans_option){
								
									case 1:
										System.out.print("Enter amount:");
										int amt=Integer.parseInt(bucky_main.readLine());
										int if_trans=current_account_obj.get(considered_index).debit_money(amt);
										
										if(if_trans==0){
											
											savings_account_obj.get(considered_index).balance+=amt;
											atm_info_obj.atm_transac_obj.add(new transfer_transac(temp_name,amt));
										}
										else
											System.out.println("This transaction is not possible since the amount to be transfered is less than expected.");
										
										break;
										
									case 2:
										System.out.print("Enter amount:");
										amt=Integer.parseInt(bucky_main.readLine());
										if_trans=savings_account_obj.get(considered_index).debit_money(amt);
										
										if(if_trans==0){
											
											current_account_obj.get(considered_index).balance+=amt;
											atm_info_obj.atm_transac_obj.add(new transfer_transac(temp_name,amt));
										}
										else
											System.out.println("This transaction is not possible since the amount to be transfered is less than expected.");
										
										break;
										
										default:
											
											System.out.println("Wrong option, Select Again.\n\n\n\n\n");
								}
								
								
								break;
								
							case 6:
								
								System.out.print("Select Account:\n-Enter '1' for Current.\n-Enter '2' for Savings.\nChoose your option:");
								int opt=Integer.parseInt(bucky_main.readLine());
								
								switch(opt){
								
									case 1:
										
										System.out.println(current_account_obj.get(considered_index).balance);
										break;
										
									case 2:
										
										System.out.println(savings_account_obj.get(considered_index).balance);
										break;
										
									default:
										
										System.out.println("Wrong option, Select Again.\n\n\n\n\n");
								}
								
								break;
								
							case 7:
								
								looping=1;
								
								break;
								
							default:
								
								System.out.println("Wrong option, Select Again.\n\n\n\n\n");
						}
						
						if(looping==1)
							break;
					}
					
					break;
					
					case 2:
						
						create_account();
						break;
						
					default:
						
						System.out.println("Wrong option select again.\n\n");
				}
		}
	}
}
	