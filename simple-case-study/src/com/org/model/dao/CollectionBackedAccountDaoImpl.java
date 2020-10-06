package com.org.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountNotFoundException;

import com.org.model.beans.Account;

public class CollectionBackedAccountDaoImpl implements AccountDao {

	private static List<Account> database = new ArrayList<>();
	@Override
	public Account createAccount(Account account) {
		database.add(account);
		return account;
	}
/*
	@Override
	public Account updateBalance(int accountNumber, double amount) {
		List<Account> temp = database.stream().filter(item -> item.getAccountNumber()==accountNumber).collect(Collectors.toList());
		for (Account account : temp) {
			account.setBalance(amount);
			return account;
		}
		return null;
	}
*/
	@Override
	public Account getAccount(int accountNumber) throws AccountNotFoundException {
		List<Account> temp =database.stream().filter(item -> item.getAccountNumber()==accountNumber).collect(Collectors.toList());
		for(Account a:temp)
			return a;
		throw new AccountNotFoundException("Account not found");
	}

	@Override
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return database;
	}

	@Override
	public Account debit(int accountNumber, double amount) {
		List<Account>t=database.stream().filter(i -> i.getAccountNumber()==accountNumber).collect(Collectors.toList());
		for (Account account : t) {
			account.setBalance(account.getBalance()-amount);
			return account;
		}
		return null;
	}

	@Override
	public Account credit(int accountNumber, double amount) {
		List<Account>t=database.stream().filter(i -> i.getAccountNumber()==accountNumber).collect(Collectors.toList());
		for (Account account : t) {
			account.setBalance(account.getBalance()+amount);
			return account;
		}
		return null;
	}

	@Override
	public void deleteAccount(int accountNumber) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		List<Account> t =database.stream().filter(i -> i.getAccountNumber()==accountNumber).collect(Collectors.toList());
		if(t.size()==0)
			throw new AccountNotFoundException("Account not found");
		else {
			database.removeIf(i -> i.getAccountNumber()==accountNumber);
		}
		//database.stream().filter(i -> i.getAccountNumber()==accountNumber).collect(Collectors.toList()).clear();
	}

}
