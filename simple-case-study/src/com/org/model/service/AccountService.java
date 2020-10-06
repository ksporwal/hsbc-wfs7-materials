package com.org.model.service;

import java.util.List;

import javax.naming.InsufficientResourcesException;
import javax.security.auth.login.AccountNotFoundException;

import com.org.model.beans.Account;

//use throws clause on transfer method : AccountNotFoundException, InsufficientBalanceException 
public interface AccountService {
	public Account createAccount(Account account);
	public double getBalance(int accountNumber) throws AccountNotFoundException;
	// call debit() and credit() on source & destination account
	public void transfer(int sourceAccount, int destinationAccount, double amount) throws InsufficientResourcesException,AccountNotFoundException;
	public List<Account> getAccountsSortedByName() throws AccountNotFoundException;
	public List<Account> getAccountsSortedByAccountNumber() throws AccountNotFoundException;
	public void deleteAccount(int accountNumber) throws AccountNotFoundException;

}
