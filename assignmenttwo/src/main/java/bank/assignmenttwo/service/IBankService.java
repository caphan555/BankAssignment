package bank.assignmenttwo.service;

import java.util.Date;

import bank.assignmenttwo.exception.AccountDoesNotExistException;
import bank.assignmenttwo.exception.ExceedDailyWithdrawalAmountException;
import bank.assignmenttwo.exception.InsufficientAmountToDepositException;
import bank.assignmenttwo.exception.InsufficientFundsToTransferException;
import bank.assignmenttwo.exception.InsufficientFundsWithdrawalException;
import bank.assignmenttwo.exception.InvalidStartAccountAmountException;
import bank.assignmenttwo.pojo.Account;

import bank.assignmenttwo.repository.IAccountRepo;
import bank.assignmenttwo.repository.ITransactionRepo;
import bank.assignmenttwo.repository.IUserRepo;

public interface IBankService {

	public String createUser(String name);
	public String createAccount(Account newAccount) throws InvalidStartAccountAmountException;
	public Account showBalance(int accountNumber) throws AccountDoesNotExistException;
	public Account withdraw(int accountNumber, double amount) throws ExceedDailyWithdrawalAmountException, InsufficientFundsWithdrawalException, AccountDoesNotExistException;
	public Account deposit(int accountNumber, double amount) throws InsufficientAmountToDepositException, AccountDoesNotExistException;
	public Account fundTransfer(double amount, Date date, int toAccountNo, int fromAccount) throws AccountDoesNotExistException, InsufficientFundsToTransferException;
	public Account printTransaction(int accountNumber, Date fromDate, Date toDate) throws AccountDoesNotExistException;
	public Account printTransaction(int accountNumber) throws AccountDoesNotExistException;
	public IAccountRepo getAccountRepo();
	public void setAccountRepo(IAccountRepo accountRepo);
	public ITransactionRepo getTransactionRepo();
	public void setTransactionRepo(ITransactionRepo transactionRepo);
	public IUserRepo getUserRepo();
	public void setUserRepo(IUserRepo userRepo);
}
