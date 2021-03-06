package bank.assignmenttwo.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import bank.assignmenttwo.exception.AccountDoesNotExistException;
import bank.assignmenttwo.exception.ExceedDailyWithdrawalAmountException;
import bank.assignmenttwo.exception.InsufficientAmountToDepositException;
import bank.assignmenttwo.exception.InsufficientFundsToTransferException;
import bank.assignmenttwo.exception.InsufficientFundsWithdrawalException;
import bank.assignmenttwo.exception.InvalidStartAccountAmountException;
import bank.assignmenttwo.pojo.Account;
import bank.assignmenttwo.pojo.Transaction;
import bank.assignmenttwo.pojo.User;
import bank.assignmenttwo.repository.AccountRepo;
import bank.assignmenttwo.repository.IAccountRepo;
import bank.assignmenttwo.repository.ITransactionRepo;
import bank.assignmenttwo.repository.IUserRepo;
import bank.assignmenttwo.repository.TransactionRepo;
import bank.assignmenttwo.repository.UserRepo;
import bank.assignmenttwo.service.BankService;
import bank.assignmenttwo.service.IBankService;
import bank.assignmenttwo.utilities.CreateUniqueNumber;

public class BankTesting {
	private final static String SUCCESS = "success";
	
	@Test
	public void testCreateUserSuccess() {
		ArrayList<User> users = new ArrayList<User>();
		IUserRepo urp = new UserRepo(users);

		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		ITransactionRepo trp = new TransactionRepo(transactions);

		ArrayList<Account> accounts = new ArrayList<Account>();
		IAccountRepo arp = new AccountRepo(accounts);

		IBankService bs = new BankService(arp, trp, urp);

		String result = bs.createUser("John");

		assertEquals("success", result);
	}

	@Test
	public void testCannotCreateEmptyUser() {
		ArrayList<User> users = new ArrayList<User>();
		IUserRepo urp = new UserRepo(users);

		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		ITransactionRepo trp = new TransactionRepo(transactions);

		ArrayList<Account> accounts = new ArrayList<Account>();
		IAccountRepo arp = new AccountRepo(accounts);

		IBankService bs = new BankService(arp, trp, urp);

		String result = bs.createUser("");

		assertEquals("Username provided cannot be an empty String!", result);
	}

	@Test(expected = java.lang.NullPointerException.class)
	public void testCannotCreateNullUser() throws NullPointerException {

			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			bs.createUser(null);
			// int answer = 1/0;
		

		// String str = null;
		// str.toUpperCase();

	}

	@Test
	public void testCreateAccountSuccess() throws InvalidStartAccountAmountException{

		
			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			String createUserResult = bs.createUser("John");

			User user = bs.getUserRepo().getUsers().get(0);

			CreateUniqueNumber uniqueNumberCreator = new CreateUniqueNumber();

			int uniqueAId = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount = new Account(0.00, new ArrayList<Transaction>(), uniqueAId, user);

			// set date
			Date myDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);
			cal.set(Calendar.DATE, 3);
			cal.set(Calendar.YEAR, 2017);
			myDate = cal.getTime();
			int uniqueTId = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction = new Transaction("Opening a new account.", "credit", 500.00, uniqueTId, myDate,
					newAccount.getBalance() + 500.00);
			newAccount.setBalance(newTransaction.getBalance());
			newAccount.getTransactions().add(newTransaction);

			String createAccountResult = bs.createAccount(newAccount);

			assertEquals("success", createAccountResult);
		
	}

	@Test(expected = NullPointerException.class)
	public void testCannotCreateNullAccount() throws NullPointerException, InvalidStartAccountAmountException{

		
			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			bs.createAccount(null);
		

	}

	@Test(expected = bank.assignmenttwo.exception.InvalidStartAccountAmountException.class)
	public void testInsufficientAmountToCreateAccount()
			throws NullPointerException, InvalidStartAccountAmountException {

		
			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			String createUserResult = bs.createUser("John");

			User user = bs.getUserRepo().getUsers().get(0);

			CreateUniqueNumber uniqueNumberCreator = new CreateUniqueNumber();

			int uniqueAId = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount = new Account(0.00, new ArrayList<Transaction>(), uniqueAId, user);

			// set date
			Date myDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);
			cal.set(Calendar.DATE, 3);
			cal.set(Calendar.YEAR, 2017);
			myDate = cal.getTime();
			int uniqueTId = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction = new Transaction("Opening a new account.", "credit", 500.00, uniqueTId, myDate,
					newAccount.getBalance() + 10.00);
			newAccount.setBalance(newTransaction.getBalance());
			newAccount.getTransactions().add(newTransaction);

			String createAccountResult = bs.createAccount(newAccount);

		

	}

	@Test
	public void testAccountShowBalanceSuccess() throws InvalidStartAccountAmountException, AccountDoesNotExistException{

		
			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			String createUserResult = bs.createUser("John");

			User user = bs.getUserRepo().getUsers().get(0);

			CreateUniqueNumber uniqueNumberCreator = new CreateUniqueNumber();

			int uniqueAId = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount = new Account(0.00, new ArrayList<Transaction>(), uniqueAId, user);

			// set date
			Date myDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);
			cal.set(Calendar.DATE, 3);
			cal.set(Calendar.YEAR, 2017);
			myDate = cal.getTime();
			int uniqueTId = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction = new Transaction("Opening a new account.", "credit", 500.00, uniqueTId, myDate,
					newAccount.getBalance() + 500.00);
			newAccount.setBalance(newTransaction.getBalance());
			newAccount.getTransactions().add(newTransaction);

			String createAccountResult = bs.createAccount(newAccount);
			Account retrievedAccount = bs.showBalance(1);
			
			String balanceResult;
			if(500.00 == retrievedAccount.getBalance()) {
				balanceResult = "success";
			} else {
				balanceResult = "failure";
			}
			assertEquals(SUCCESS, balanceResult);

	}

	@Test(expected = bank.assignmenttwo.exception.AccountDoesNotExistException.class)
	public void testAccountDoesNotExist() throws AccountDoesNotExistException, InvalidStartAccountAmountException{

		
			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			String createUserResult = bs.createUser("John");

			User user = bs.getUserRepo().getUsers().get(0);

			CreateUniqueNumber uniqueNumberCreator = new CreateUniqueNumber();

			int uniqueAId = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount = new Account(0.00, new ArrayList<Transaction>(), uniqueAId, user);

			// set date
			Date myDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);
			cal.set(Calendar.DATE, 3);
			cal.set(Calendar.YEAR, 2017);
			myDate = cal.getTime();
			int uniqueTId = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction = new Transaction("Opening a new account.", "credit", 500.00, uniqueTId, myDate,
					newAccount.getBalance() + 500.00);
			newAccount.setBalance(newTransaction.getBalance());
			newAccount.getTransactions().add(newTransaction);

			String createAccountResult = bs.createAccount(newAccount);
			Account retrievedAccount = bs.showBalance(5);

	}
	
	@Test
	public void testDepositSuccess() throws AccountDoesNotExistException, 
	InvalidStartAccountAmountException, InsufficientAmountToDepositException{

		
			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			String createUserResult = bs.createUser("John");

			User user = bs.getUserRepo().getUsers().get(0);

			CreateUniqueNumber uniqueNumberCreator = new CreateUniqueNumber();

			int uniqueAId = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount = new Account(0.00, new ArrayList<Transaction>(), uniqueAId, user);

			// set date
			Date myDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);
			cal.set(Calendar.DATE, 3);
			cal.set(Calendar.YEAR, 2017);
			myDate = cal.getTime();
			int uniqueTId = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction = new Transaction("Opening a new account.", "credit", 500.00, uniqueTId, myDate,
					newAccount.getBalance() + 500.00);
			
			newAccount.setBalance(newTransaction.getBalance());
			newAccount.getTransactions().add(newTransaction);
			
			
			String createAccountResult = bs.createAccount(newAccount);
			
			Account depositedAccount = bs.deposit(1, 300.00);
			double newBalance = depositedAccount.getBalance();
			String depositResult;
			if(newBalance == 800.00) {
				depositResult = "Money successfully deposited!";
			} else {
				depositResult = "Failed to deposit money!";
			}
			
			assertEquals("Money successfully deposited!", depositResult);
	}
	
	@Test(expected = bank.assignmenttwo.exception.InsufficientAmountToDepositException.class)
	public void testInsufficientAmountToDeposit() throws AccountDoesNotExistException, 
					InvalidStartAccountAmountException, InsufficientAmountToDepositException{

		
			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			String createUserResult = bs.createUser("John");

			User user = bs.getUserRepo().getUsers().get(0);

			CreateUniqueNumber uniqueNumberCreator = new CreateUniqueNumber();

			int uniqueAId = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount = new Account(0.00, new ArrayList<Transaction>(), uniqueAId, user);

			// set date
			Date myDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);
			cal.set(Calendar.DATE, 3);
			cal.set(Calendar.YEAR, 2017);
			myDate = cal.getTime();
			int uniqueTId = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction = new Transaction("Opening a new account.", "credit", 500.00, uniqueTId, myDate,
					newAccount.getBalance() + 500.00);
			
			newAccount.setBalance(newTransaction.getBalance());
			newAccount.getTransactions().add(newTransaction);
			
			
			String createAccountResult = bs.createAccount(newAccount);
			
			Account depositedAccount = bs.deposit(1, -100.00);
	}
	
	@Test(expected = bank.assignmenttwo.exception.AccountDoesNotExistException.class)
	public void testInvalidAccountToDeposit() throws AccountDoesNotExistException, 
					InvalidStartAccountAmountException, InsufficientAmountToDepositException{

		
			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			String createUserResult = bs.createUser("John");

			User user = bs.getUserRepo().getUsers().get(0);

			CreateUniqueNumber uniqueNumberCreator = new CreateUniqueNumber();

			int uniqueAId = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount = new Account(0.00, new ArrayList<Transaction>(), uniqueAId, user);

			// set date
			Date myDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);
			cal.set(Calendar.DATE, 3);
			cal.set(Calendar.YEAR, 2017);
			myDate = cal.getTime();
			int uniqueTId = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction = new Transaction("Opening a new account.", "credit", 500.00, uniqueTId, myDate,
					newAccount.getBalance() + 500.00);
			
			newAccount.setBalance(newTransaction.getBalance());
			newAccount.getTransactions().add(newTransaction);
			
			
			String createAccountResult = bs.createAccount(newAccount);
			
			Account depositedAccount = bs.deposit(40, 400.00);
	}
	
	@Test
	public void testWithdrawalSuccess() throws AccountDoesNotExistException, 
			InvalidStartAccountAmountException, ExceedDailyWithdrawalAmountException, 
			InsufficientFundsWithdrawalException{

		
			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			String createUserResult = bs.createUser("John");

			User user = bs.getUserRepo().getUsers().get(0);

			CreateUniqueNumber uniqueNumberCreator = new CreateUniqueNumber();

			int uniqueAId = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount = new Account(0.00, new ArrayList<Transaction>(), uniqueAId, user);

			// set date
			Date myDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);
			cal.set(Calendar.DATE, 3);
			cal.set(Calendar.YEAR, 2017);
			myDate = cal.getTime();
			int uniqueTId = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction = new Transaction("Opening a new account.", "credit", 500.00, uniqueTId, myDate,
					newAccount.getBalance() + 500.00);
			
			newAccount.setBalance(newTransaction.getBalance());
			newAccount.getTransactions().add(newTransaction);
			
			
			String createAccountResult = bs.createAccount(newAccount);
			
			Account withdrawAccount = bs.withdraw(1, 300.00);
			double newBalance = withdrawAccount.getBalance();
			String depositResult;
			if(newBalance == 200.00) {
				depositResult = "Money successfully withdrew!";
			} else {
				depositResult = "Failed to withdraw money!";
			}
			
			assertEquals("Money successfully withdrew!", depositResult);
	}
	
	@Test(expected = bank.assignmenttwo.exception.ExceedDailyWithdrawalAmountException.class)
	public void testExceedDailyLimitWithdrawal() throws AccountDoesNotExistException, 
			InvalidStartAccountAmountException, ExceedDailyWithdrawalAmountException, 
			InsufficientFundsWithdrawalException{

		
			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			String createUserResult = bs.createUser("John");

			User user = bs.getUserRepo().getUsers().get(0);

			CreateUniqueNumber uniqueNumberCreator = new CreateUniqueNumber();

			int uniqueAId = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount = new Account(0.00, new ArrayList<Transaction>(), uniqueAId, user);

			// set date
			Date myDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);
			cal.set(Calendar.DATE, 3);
			cal.set(Calendar.YEAR, 2017);
			myDate = cal.getTime();
			int uniqueTId = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction = new Transaction("Opening a new account.", "credit", 500.00, uniqueTId, myDate,
					newAccount.getBalance() + 500.00);
			
			newAccount.setBalance(newTransaction.getBalance());
			newAccount.getTransactions().add(newTransaction);
			
			
			String createAccountResult = bs.createAccount(newAccount);
			
			Account withdrawAccount = bs.withdraw(1, 2000.00);
			
	}
	
	@Test(expected = bank.assignmenttwo.exception.InsufficientFundsWithdrawalException.class)
	public void testInsufficientFundsWithdrawal() throws AccountDoesNotExistException, 
			InvalidStartAccountAmountException, ExceedDailyWithdrawalAmountException, 
			InsufficientFundsWithdrawalException{

		
			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			String createUserResult = bs.createUser("John");

			User user = bs.getUserRepo().getUsers().get(0);

			CreateUniqueNumber uniqueNumberCreator = new CreateUniqueNumber();

			int uniqueAId = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount = new Account(0.00, new ArrayList<Transaction>(), uniqueAId, user);

			// set date
			Date myDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);
			cal.set(Calendar.DATE, 3);
			cal.set(Calendar.YEAR, 2017);
			myDate = cal.getTime();
			int uniqueTId = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction = new Transaction("Opening a new account.", "credit", 500.00, uniqueTId, myDate,
					newAccount.getBalance() + 500.00);
			
			newAccount.setBalance(newTransaction.getBalance());
			newAccount.getTransactions().add(newTransaction);
			
			
			String createAccountResult = bs.createAccount(newAccount);
			
			Account withdrawAccount = bs.withdraw(1, 800.00);
			
	}
	
	@Test(expected = bank.assignmenttwo.exception.AccountDoesNotExistException.class)
	public void testInvalidAccountWithdrawal() throws AccountDoesNotExistException, 
			InvalidStartAccountAmountException, ExceedDailyWithdrawalAmountException, 
			InsufficientFundsWithdrawalException{

		
			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			String createUserResult = bs.createUser("John");

			User user = bs.getUserRepo().getUsers().get(0);

			CreateUniqueNumber uniqueNumberCreator = new CreateUniqueNumber();

			int uniqueAId = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount = new Account(0.00, new ArrayList<Transaction>(), uniqueAId, user);

			// set date
			Date myDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);
			cal.set(Calendar.DATE, 3);
			cal.set(Calendar.YEAR, 2017);
			myDate = cal.getTime();
			int uniqueTId = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction = new Transaction("Opening a new account.", "credit", 500.00, uniqueTId, myDate,
					newAccount.getBalance() + 500.00);
			
			newAccount.setBalance(newTransaction.getBalance());
			newAccount.getTransactions().add(newTransaction);
			
			
			String createAccountResult = bs.createAccount(newAccount);
			
			Account withdrawAccount = bs.withdraw(89, 100.00);
			
	}
	
	@Test
	public void testTransferSuccess() throws AccountDoesNotExistException, 
			InvalidStartAccountAmountException, InsufficientFundsToTransferException {

		
			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			String createUserResult = bs.createUser("John");

			User user = bs.getUserRepo().getUsers().get(0);

			CreateUniqueNumber uniqueNumberCreator = new CreateUniqueNumber();

			int uniqueAId = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount = new Account(0.00, new ArrayList<Transaction>(), uniqueAId, user);

			// set date
			Date myDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);
			cal.set(Calendar.DATE, 3);
			cal.set(Calendar.YEAR, 2017);
			myDate = cal.getTime();
			int uniqueTId = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction = new Transaction("Opening a new account.", "credit", 500.00, uniqueTId, myDate,
					newAccount.getBalance() + 500.00);
			
			newAccount.setBalance(newTransaction.getBalance());
			newAccount.getTransactions().add(newTransaction);
			
			//2nd user & account
			String createUserResult2 = bs.createUser("Ramal");

			User secondUser = bs.getUserRepo().getUsers().get(1);


			int uniqueAId2 = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount2 = new Account(0.00, new ArrayList<Transaction>(), uniqueAId2, secondUser);

			// set date
			Date myDate2 = new Date();
			Calendar cal2 = Calendar.getInstance();
			cal.set(Calendar.MONTH, 9);
			cal.set(Calendar.DATE, 28);
			cal.set(Calendar.YEAR, 2017);
			myDate2 = cal.getTime();
			int uniqueTId2 = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction2 = new Transaction("Opening a new account.", "credit", 200.00, uniqueTId2, myDate2,
					newAccount2.getBalance() + 200.00);
			
			newAccount2.setBalance(newTransaction2.getBalance());
			newAccount2.getTransactions().add(newTransaction2);
			
			String createAccountResult = bs.createAccount(newAccount);
			String createAccountResul2t = bs.createAccount(newAccount2);
			
			Date transferDate = new Date();
			Calendar cal3 = Calendar.getInstance();
			cal3.set(Calendar.MONTH, 10);
			cal3.set(Calendar.DATE, 14);
			cal3.set(Calendar.YEAR, 2017);
			transferDate = cal3.getTime();
			
			Account transferringAccount = bs.fundTransfer(150.00, transferDate, newAccount2.getAccountNo(), newAccount.getAccountNo());
			
			String transferResult;
			if(newAccount.getBalance() == 350.00) {
				transferResult = "Funds successfully transferred!";
			} else {
				transferResult = "Funds transfer failed!";
			}
			
			assertEquals("Funds successfully transferred!", transferResult);
			
	}
	
	@Test(expected = bank.assignmenttwo.exception.InsufficientFundsToTransferException.class)
	public void testInsufficientFundsTransfer() throws AccountDoesNotExistException, 
			InvalidStartAccountAmountException, InsufficientFundsToTransferException {

		
			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			String createUserResult = bs.createUser("John");

			User user = bs.getUserRepo().getUsers().get(0);

			CreateUniqueNumber uniqueNumberCreator = new CreateUniqueNumber();

			int uniqueAId = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount = new Account(0.00, new ArrayList<Transaction>(), uniqueAId, user);

			// set date
			Date myDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);
			cal.set(Calendar.DATE, 3);
			cal.set(Calendar.YEAR, 2017);
			myDate = cal.getTime();
			int uniqueTId = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction = new Transaction("Opening a new account.", "credit", 500.00, uniqueTId, myDate,
					newAccount.getBalance() + 500.00);
			
			newAccount.setBalance(newTransaction.getBalance());
			newAccount.getTransactions().add(newTransaction);
			
			//2nd user & account
			String createUserResult2 = bs.createUser("Ramal");

			User secondUser = bs.getUserRepo().getUsers().get(1);


			int uniqueAId2 = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount2 = new Account(0.00, new ArrayList<Transaction>(), uniqueAId2, secondUser);

			// set date
			Date myDate2 = new Date();
			Calendar cal2 = Calendar.getInstance();
			cal.set(Calendar.MONTH, 9);
			cal.set(Calendar.DATE, 28);
			cal.set(Calendar.YEAR, 2017);
			myDate2 = cal.getTime();
			int uniqueTId2 = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction2 = new Transaction("Opening a new account.", "credit", 200.00, uniqueTId2, myDate2,
					newAccount2.getBalance() + 200.00);
			
			newAccount2.setBalance(newTransaction2.getBalance());
			newAccount2.getTransactions().add(newTransaction2);
			
			String createAccountResult = bs.createAccount(newAccount);
			String createAccountResul2t = bs.createAccount(newAccount2);
			
			Date transferDate = new Date();
			Calendar cal3 = Calendar.getInstance();
			cal3.set(Calendar.MONTH, 10);
			cal3.set(Calendar.DATE, 14);
			cal3.set(Calendar.YEAR, 2017);
			transferDate = cal3.getTime();
			
			Account transferringAccount = bs.fundTransfer(600.00, transferDate, newAccount2.getAccountNo(), newAccount.getAccountNo());
			
			
			
	}
	
	@Test(expected = bank.assignmenttwo.exception.AccountDoesNotExistException.class)
	public void testAccountDoesNotExistForTransfer() throws AccountDoesNotExistException, 
			InvalidStartAccountAmountException, InsufficientFundsToTransferException {

		
			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			String createUserResult = bs.createUser("John");

			User user = bs.getUserRepo().getUsers().get(0);

			CreateUniqueNumber uniqueNumberCreator = new CreateUniqueNumber();

			int uniqueAId = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount = new Account(0.00, new ArrayList<Transaction>(), uniqueAId, user);

			// set date
			Date myDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);
			cal.set(Calendar.DATE, 3);
			cal.set(Calendar.YEAR, 2017);
			myDate = cal.getTime();
			int uniqueTId = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction = new Transaction("Opening a new account.", "credit", 500.00, uniqueTId, myDate,
					newAccount.getBalance() + 500.00);
			
			newAccount.setBalance(newTransaction.getBalance());
			newAccount.getTransactions().add(newTransaction);
			
			//2nd user & account
			String createUserResult2 = bs.createUser("Ramal");

			User secondUser = bs.getUserRepo().getUsers().get(1);


			int uniqueAId2 = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount2 = new Account(0.00, new ArrayList<Transaction>(), uniqueAId2, secondUser);

			// set date
			Date myDate2 = new Date();
			Calendar cal2 = Calendar.getInstance();
			cal.set(Calendar.MONTH, 9);
			cal.set(Calendar.DATE, 28);
			cal.set(Calendar.YEAR, 2017);
			myDate2 = cal.getTime();
			int uniqueTId2 = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction2 = new Transaction("Opening a new account.", "credit", 200.00, uniqueTId2, myDate2,
					newAccount2.getBalance() + 200.00);
			
			newAccount2.setBalance(newTransaction2.getBalance());
			newAccount2.getTransactions().add(newTransaction2);
			
			String createAccountResult = bs.createAccount(newAccount);
			String createAccountResul2t = bs.createAccount(newAccount2);
			
			Date transferDate = new Date();
			Calendar cal3 = Calendar.getInstance();
			cal3.set(Calendar.MONTH, 10);
			cal3.set(Calendar.DATE, 14);
			cal3.set(Calendar.YEAR, 2017);
			transferDate = cal3.getTime();
			
			Account transferringAccount = bs.fundTransfer(100.00, transferDate, 9, 10);
	}
	
	@Test
	public void testPrintTransactionLastTenSuccess() throws AccountDoesNotExistException, 
	InvalidStartAccountAmountException, InsufficientAmountToDepositException{
		
			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			String createUserResult = bs.createUser("John");

			User user = bs.getUserRepo().getUsers().get(0);

			CreateUniqueNumber uniqueNumberCreator = new CreateUniqueNumber();

			int uniqueAId = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount = new Account(0.00, new ArrayList<Transaction>(), uniqueAId, user);

			// set date
			Date myDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);
			cal.set(Calendar.DATE, 3);
			cal.set(Calendar.YEAR, 2017);
			myDate = cal.getTime();
			int uniqueTId = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction = new Transaction("Opening a new account.", "credit", 500.00, uniqueTId, myDate,
					newAccount.getBalance() + 500.00);
			
			newAccount.setBalance(newTransaction.getBalance());
			newAccount.getTransactions().add(newTransaction);
			
			int month = 0;
			int day = 13;
			double deposit = 200.00;
			for(int i=0; i<=5; i++) {
				Date depositDates = new Date();
				Calendar depositCal = Calendar.getInstance();
				depositCal.set(Calendar.MONTH, month);
				depositCal.set(Calendar.DATE, day);
				depositCal.set(Calendar.YEAR, 2017);
				depositDates = depositCal.getTime();
				
				int uniqueDepositId = uniqueNumberCreator.generateUniqueTransactionId();
				Transaction deposits = new Transaction("Deposit", "debit", deposit, uniqueDepositId, depositDates, newAccount.getBalance() + deposit);
				double newBalance = newAccount.getBalance() + deposit;
				newAccount.setBalance(newBalance);
				newAccount.getTransactions().add(deposits);
				++month;
				++day;
				++deposit;
				bs.getTransactionRepo().saveTransaction(deposits);
			}
			
			int month2 = 7;
			int day2 = 23;
			double withdraw = 300.00;
			for(int i=0; i<=4; i++) {
				Date withdrawDates = new Date();
				Calendar withdrawCal = Calendar.getInstance();
				withdrawCal.set(Calendar.MONTH, month2);
				withdrawCal.set(Calendar.DATE, day2);
				withdrawCal.set(Calendar.YEAR, 2017);
				withdrawDates = withdrawCal.getTime();
				
				int uniqueWithdrawId = uniqueNumberCreator.generateUniqueTransactionId();
				Transaction withdrawals = new Transaction("Withdraw", "debit", withdraw, uniqueWithdrawId, withdrawDates, newAccount.getBalance() - withdraw);
				double newBalance = newAccount.getBalance() - withdraw;
				newAccount.setBalance(newBalance);
				newAccount.getTransactions().add(withdrawals);
				++month;
				++day;
				--withdraw;
				bs.getTransactionRepo().saveTransaction(withdrawals);
			}
			
			bs.getAccountRepo().getAccounts().add(newAccount);
			
			Account retrievalAccount = bs.printTransaction(1);
			
			int t = 11;
			for(int i=0; i<10; i++) {
				//System.out.println("t: "+t);
				//System.out.println("newAccount transaction: "+newAccount.getTransactions().get(t).getDate().toString());
				//System.out.println("retrievalAccount transaction: "+retrievalAccount.getTransactions().get(i).getDate().toString());
				assertEquals(newAccount.getTransactions().get(t), retrievalAccount.getTransactions().get(i));
				--t;
			}
			
	}
	
	@Test(expected = bank.assignmenttwo.exception.AccountDoesNotExistException.class)
	public void testAccountDoesNotExistPrintTransactionLastTen() throws 
				AccountDoesNotExistException, InvalidStartAccountAmountException,
				InsufficientAmountToDepositException{
		
			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			String createUserResult = bs.createUser("John");

			User user = bs.getUserRepo().getUsers().get(0);

			CreateUniqueNumber uniqueNumberCreator = new CreateUniqueNumber();

			int uniqueAId = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount = new Account(0.00, new ArrayList<Transaction>(), uniqueAId, user);

			// set date
			Date myDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);
			cal.set(Calendar.DATE, 3);
			cal.set(Calendar.YEAR, 2017);
			myDate = cal.getTime();
			int uniqueTId = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction = new Transaction("Opening a new account.", "credit", 500.00, uniqueTId, myDate,
					newAccount.getBalance() + 500.00);
			
			newAccount.setBalance(newTransaction.getBalance());
			newAccount.getTransactions().add(newTransaction);
			
			int month = 0;
			int day = 13;
			double deposit = 200.00;
			for(int i=0; i<=5; i++) {
				Date depositDates = new Date();
				Calendar depositCal = Calendar.getInstance();
				depositCal.set(Calendar.MONTH, month);
				depositCal.set(Calendar.DATE, day);
				depositCal.set(Calendar.YEAR, 2017);
				depositDates = depositCal.getTime();
				
				int uniqueDepositId = uniqueNumberCreator.generateUniqueTransactionId();
				Transaction deposits = new Transaction("Deposit", "debit", deposit, uniqueDepositId, depositDates, newAccount.getBalance() + deposit);
				double newBalance = newAccount.getBalance() + deposit;
				newAccount.setBalance(newBalance);
				newAccount.getTransactions().add(deposits);
				++month;
				++day;
				++deposit;
				bs.getTransactionRepo().saveTransaction(deposits);
			}
			
			int month2 = 7;
			int day2 = 23;
			double withdraw = 300.00;
			for(int i=0; i<=4; i++) {
				Date withdrawDates = new Date();
				Calendar withdrawCal = Calendar.getInstance();
				withdrawCal.set(Calendar.MONTH, month2);
				withdrawCal.set(Calendar.DATE, day2);
				withdrawCal.set(Calendar.YEAR, 2017);
				withdrawDates = withdrawCal.getTime();
				
				int uniqueWithdrawId = uniqueNumberCreator.generateUniqueTransactionId();
				Transaction withdrawals = new Transaction("Withdraw", "debit", withdraw, uniqueWithdrawId, withdrawDates, newAccount.getBalance() - withdraw);
				double newBalance = newAccount.getBalance() - withdraw;
				newAccount.setBalance(newBalance);
				newAccount.getTransactions().add(withdrawals);
				++month;
				++day;
				--withdraw;
				bs.getTransactionRepo().saveTransaction(withdrawals);
			}
			
			bs.getAccountRepo().getAccounts().add(newAccount);
			
			Account retrievalAccount = bs.printTransaction(10);
			
	}
	
	@Test
	public void testPrintTransactionPeriodSuccess() throws AccountDoesNotExistException, 
	InvalidStartAccountAmountException, InsufficientAmountToDepositException{
		
			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			String createUserResult = bs.createUser("John");

			User user = bs.getUserRepo().getUsers().get(0);

			CreateUniqueNumber uniqueNumberCreator = new CreateUniqueNumber();

			int uniqueAId = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount = new Account(0.00, new ArrayList<Transaction>(), uniqueAId, user);

			// set date
			Date myDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);
			cal.set(Calendar.DATE, 3);
			cal.set(Calendar.YEAR, 2017);
			myDate = cal.getTime();
			int uniqueTId = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction = new Transaction("Opening a new account.", "credit", 500.00, uniqueTId, myDate,
					newAccount.getBalance() + 500.00);
			
			newAccount.setBalance(newTransaction.getBalance());
			newAccount.getTransactions().add(newTransaction);
			
			int month = 0;
			int day = 13;
			double deposit = 200.00;
			for(int i=0; i<=5; i++) {
				Date depositDates = new Date();
				Calendar depositCal = Calendar.getInstance();
				depositCal.set(Calendar.MONTH, month);
				depositCal.set(Calendar.DATE, day);
				depositCal.set(Calendar.YEAR, 2017);
				depositDates = depositCal.getTime();
				
				int uniqueDepositId = uniqueNumberCreator.generateUniqueTransactionId();
				Transaction deposits = new Transaction("Deposit", "debit", deposit, uniqueDepositId, depositDates, newAccount.getBalance() + deposit);
				double newBalance = newAccount.getBalance() + deposit;
				newAccount.setBalance(newBalance);
				newAccount.getTransactions().add(deposits);
				++month;
				++day;
				++deposit;
				bs.getTransactionRepo().saveTransaction(deposits);
			}
			
			int month2 = 7;
			int day2 = 23;
			double withdraw = 300.00;
			for(int i=0; i<=4; i++) {
				Date withdrawDates = new Date();
				Calendar withdrawCal = Calendar.getInstance();
				withdrawCal.set(Calendar.MONTH, month2);
				withdrawCal.set(Calendar.DATE, day2);
				withdrawCal.set(Calendar.YEAR, 2017);
				withdrawDates = withdrawCal.getTime();
				
				int uniqueWithdrawId = uniqueNumberCreator.generateUniqueTransactionId();
				Transaction withdrawals = new Transaction("Withdraw", "debit", withdraw, uniqueWithdrawId, withdrawDates, newAccount.getBalance() - withdraw);
				double newBalance = newAccount.getBalance() - withdraw;
				newAccount.setBalance(newBalance);
				newAccount.getTransactions().add(withdrawals);
				++month;
				++day;
				--withdraw;
				bs.getTransactionRepo().saveTransaction(withdrawals);
			}
			
			bs.getAccountRepo().getAccounts().add(newAccount);
			
			Date fromDate = new Date();
			Calendar fromCal = Calendar.getInstance();
			fromCal.set(Calendar.MONTH, 4);
			fromCal.set(Calendar.DATE, 3);
			fromCal.set(Calendar.YEAR, 2017);
			fromDate = fromCal.getTime();
			
			Date toDate = new Date();
			Calendar toCal = Calendar.getInstance();
			toCal.set(Calendar.MONTH, 10);
			toCal.set(Calendar.DATE, 30);
			toCal.set(Calendar.YEAR, 2017);
			toDate = toCal.getTime();
			
			Account retrievalAccount = bs.printTransaction(1, fromDate, toDate);
			
			ArrayList<Transaction> comparingTransactions = new ArrayList<Transaction>();
			comparingTransactions.add(newAccount.getTransactions().get(0));
		
			for(int r=5; r<=11; r++) {
				comparingTransactions.add(newAccount.getTransactions().get(r));
			}
			
			
			for(int i=0; i<8; i++) {
				assertEquals(comparingTransactions.get(i), retrievalAccount.getTransactions().get(i));
			}
			
	}
	
	@Test(expected = bank.assignmenttwo.exception.AccountDoesNotExistException.class)
	public void testInvalidAccountPrintTransactionPeriod() throws AccountDoesNotExistException, 
	InvalidStartAccountAmountException, InsufficientAmountToDepositException{
		
			ArrayList<User> users = new ArrayList<User>();
			IUserRepo urp = new UserRepo(users);

			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ITransactionRepo trp = new TransactionRepo(transactions);

			ArrayList<Account> accounts = new ArrayList<Account>();
			IAccountRepo arp = new AccountRepo(accounts);

			IBankService bs = new BankService(arp, trp, urp);

			String createUserResult = bs.createUser("John");

			User user = bs.getUserRepo().getUsers().get(0);

			CreateUniqueNumber uniqueNumberCreator = new CreateUniqueNumber();

			int uniqueAId = uniqueNumberCreator.generateUniqueAccNum();
			Account newAccount = new Account(0.00, new ArrayList<Transaction>(), uniqueAId, user);

			// set date
			Date myDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);
			cal.set(Calendar.DATE, 3);
			cal.set(Calendar.YEAR, 2017);
			myDate = cal.getTime();
			int uniqueTId = uniqueNumberCreator.generateUniqueTransactionId();
			Transaction newTransaction = new Transaction("Opening a new account.", "credit", 500.00, uniqueTId, myDate,
					newAccount.getBalance() + 500.00);
			
			newAccount.setBalance(newTransaction.getBalance());
			newAccount.getTransactions().add(newTransaction);
			
			int month = 0;
			int day = 13;
			double deposit = 200.00;
			for(int i=0; i<=5; i++) {
				Date depositDates = new Date();
				Calendar depositCal = Calendar.getInstance();
				depositCal.set(Calendar.MONTH, month);
				depositCal.set(Calendar.DATE, day);
				depositCal.set(Calendar.YEAR, 2017);
				depositDates = depositCal.getTime();
				
				int uniqueDepositId = uniqueNumberCreator.generateUniqueTransactionId();
				Transaction deposits = new Transaction("Deposit", "debit", deposit, uniqueDepositId, depositDates, newAccount.getBalance() + deposit);
				double newBalance = newAccount.getBalance() + deposit;
				newAccount.setBalance(newBalance);
				newAccount.getTransactions().add(deposits);
				++month;
				++day;
				++deposit;
				bs.getTransactionRepo().saveTransaction(deposits);
			}
			
			int month2 = 7;
			int day2 = 23;
			double withdraw = 300.00;
			for(int i=0; i<=4; i++) {
				Date withdrawDates = new Date();
				Calendar withdrawCal = Calendar.getInstance();
				withdrawCal.set(Calendar.MONTH, month2);
				withdrawCal.set(Calendar.DATE, day2);
				withdrawCal.set(Calendar.YEAR, 2017);
				withdrawDates = withdrawCal.getTime();
				
				int uniqueWithdrawId = uniqueNumberCreator.generateUniqueTransactionId();
				Transaction withdrawals = new Transaction("Withdraw", "debit", withdraw, uniqueWithdrawId, withdrawDates, newAccount.getBalance() - withdraw);
				double newBalance = newAccount.getBalance() - withdraw;
				newAccount.setBalance(newBalance);
				newAccount.getTransactions().add(withdrawals);
				++month;
				++day;
				--withdraw;
				bs.getTransactionRepo().saveTransaction(withdrawals);
			}
			
			bs.getAccountRepo().getAccounts().add(newAccount);
			
			Date fromDate = new Date();
			Calendar fromCal = Calendar.getInstance();
			fromCal.set(Calendar.MONTH, 4);
			fromCal.set(Calendar.DATE, 3);
			fromCal.set(Calendar.YEAR, 2017);
			fromDate = fromCal.getTime();
			
			Date toDate = new Date();
			Calendar toCal = Calendar.getInstance();
			toCal.set(Calendar.MONTH, 10);
			toCal.set(Calendar.DATE, 30);
			toCal.set(Calendar.YEAR, 2017);
			toDate = toCal.getTime();
			
			Account retrievalAccount = bs.printTransaction(21, fromDate, toDate);
			
	}
	
	
}
