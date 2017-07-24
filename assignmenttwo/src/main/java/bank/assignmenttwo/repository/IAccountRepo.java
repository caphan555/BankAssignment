package bank.assignmenttwo.repository;

import java.util.ArrayList;

import bank.assignmenttwo.pojo.Account;

public interface IAccountRepo {
	public String saveAccount(Account account);
	public Account findAccount(int accountNumber);
	public void setAccounts(ArrayList<Account> accounts);
	public ArrayList<Account> getAccounts();
}
