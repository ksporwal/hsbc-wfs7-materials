package com.org.model.dao;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import com.org.model.beans.Account;

// use throws clause on methods like getAccount, deleteAccount : AccountNotFoundException, 
// InsufficientBalanceException on debit() method
public interface AccountDao {
	public Account createAccount(Account account);
	//public Account updateBalance(int accountNumber, double amount);
	public Account debit(int accountNumber, double amount);
	public Account credit(int accountNumber, double amount);
	public Account getAccount(int accountNumber) throws AccountNotFoundException;
	public List<Account> getAccounts();
	public void deleteAccount(int accountNumber) throws AccountNotFoundException;
}
