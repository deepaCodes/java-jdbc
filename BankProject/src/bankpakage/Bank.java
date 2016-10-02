package bankpakage;

import java.util.ArrayList;

public class Bank {
 
	private ArrayList<Account> accountList = new ArrayList<Account>();
	
	public Bank(){
		System.out.println("default constructor");
	}
	/*
	public Bank(ArrayList<String> names){
		for (String name : names) {
			Account a =new Account();
			a.setName(name);
			a.setAccno(name.hashCode());
			accountList.add(a);
						
		}
	}
   */
	
	public void changeName(int accountNumber,String name){
		for (Account account : accountList) {
			if(account.getAccno()==accountNumber){
				account.setName(name);
			}
			
		}
	}
	
	public void setBalance(int accountNumber,double balance){
		for (Account account : accountList) {
			if(account.getAccno()==accountNumber){
				account.setBalance(account.getBalance()+balance);
			}
			
		}
		
	}
	
	public void setAccount(String name,int number,double balance){
		Account a=new Account();
		a.setName(name);
		a.setAccno(number);
		a.setBalance(balance);
		accountList.add(a);
		
	}
	
	public boolean createAccount(String name){
		
		if(this.hasAccount(name)){
			return false;
		}else{
			Account a =new Account();
			a.setName(name);
			a.setAccno(name.hashCode());
			accountList.add(a);
			return true;
		}
	}
	
	public boolean hasAccount (String name){
		for (Account account : accountList) {
			if(account.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
	
	public double getBalance(String name){
		for (Account account : accountList) {
			if(account.getName().equals(name)){
				return account.getBalance();
			}
			
		}
		return 0;
		
	}
	
	public double getBalance(int accountNumber){
		
		for (Account account : accountList) {
			if(account.getAccno()==accountNumber){
				return account.getBalance();
			}
			
		}
		return 0;
	}
	public String getName(int accountNumber){
		for (Account account : accountList) {
			if(account.getAccno()==accountNumber){
				return account.getName();
			}
		}
		return null;
	}

	public void printAccount(){
		for (Account account : accountList) {
			System.out.println(account.getName()+ "\t" + account.getAccno()+ "\t"+ account.getBalance());
			
		}
	}
}
