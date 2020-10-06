package com.org.model.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.naming.InsufficientResourcesException;
import javax.security.auth.login.AccountNotFoundException;

import com.org.model.beans.Account;
import com.org.model.dao.AccountDao;
import com.org.model.util.ObjectFactory;

public class AccountServiceImpl implements AccountService {

	
	private AccountDao accountDao = null;
	public AccountServiceImpl() {
		accountDao = ObjectFactory.getAccountDaoInstance();
	}
	
	
	@Override
	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
		return accountDao.createAccount(account);
	}

	@Override
	public double getBalance(int accountNumber) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return accountDao.getAccount(accountNumber).getBalance();
	}
/*
	@Override
	public void transfer(int sourceAccount, int destincationAccount, double amount) throws InsufficientResourcesException, AccountNotFoundException {
		int flag1=0;
		int flag2=0;
		List<Account> accounts = accountDao.getAccounts();
		Account src = null,dest = null;
		for (Account account : accounts) {
			if(account.getAccountNumber()==sourceAccount) {
				 flag1=1;
				 src = account;
			}
			if(account.getAccountNumber()==destincationAccount) {
				 flag2=1;
				 dest = account;
			}
		}
		if(flag1!=1) {
			throw new AccountNotFoundException("Source Account doesnot exist");
		}
		else if(flag2!=1) 
			throw new AccountNotFoundException("Destination Account doesnot exist");
		
		else {
			if(src.getBalance()<amount) {
				throw new InsufficientResourcesException("Insufficient Balance");
			}
			else {
				System.out.println("Updated src balance:"+accountDao.updateBalance(sourceAccount, src.getBalance()-amount).getBalance());
				System.out.println("Updated dest balance:"+accountDao.updateBalance(destincationAccount, dest.getBalance()+amount).getBalance());
			}
		}
	}
*/
	@Override
	public List<Account> getAccountsSortedByName() throws AccountNotFoundException {
		List<Account> accounts = accountDao.getAccounts();
		List<Account> sortedAccount = accounts.stream()
		.sorted((account1, account2) -> account1.getCustomerName().compareTo(account2.getCustomerName()))
		.collect(Collectors.toList());
		if(sortedAccount.size()==0)
			throw new AccountNotFoundException("Accounts doesnot exist");
		else
		return sortedAccount;
	}

	@Override
	public List<Account> getAccountsSortedByAccountNumber() throws AccountNotFoundException {
		List<Account> accounts = accountDao.getAccounts();
		List<Account> sortedAccount = accounts.stream()
		.sorted((account1, account2) -> account1.getAccountNumber()-account2.getAccountNumber())
		.collect(Collectors.toList());
		if(sortedAccount.size()==0)
			throw new AccountNotFoundException("Accounts not found");
		return sortedAccount;
	}


	@Override
	public void transfer(int sourceAccount, int destinationAccount, double amount) throws InsufficientResourcesException, AccountNotFoundException{
		int flag1=0;
		int flag2=0;
		List<Account> accounts = accountDao.getAccounts();
		Account src = null,dest = null;
		for (Account account : accounts) {
			if(account.getAccountNumber()==sourceAccount) {
				 flag1=1;
				 src = account;
			}
			if(account.getAccountNumber()==destinationAccount) {
				 flag2=1;
				 dest = account;
			}
		}
		if(flag1!=1) {
			throw new AccountNotFoundException("Source Account doesnot exist");
		}
		else if(flag2!=1) {
			throw new AccountNotFoundException("Destination Account doesnot exist");
		}
		
		else {
			if(src.getBalance()<amount) {
				throw new InsufficientResourcesException("Insufficient Balance");
			}
			else {
				System.out.println("Source balance:"+accountDao.debit(sourceAccount, amount).getBalance());
				System.out.println("Destination Balance:"+accountDao.credit(destinationAccount, amount));
			//	System.out.println("Updated src balance:"+accountDao.updateBalance(sourceAccount, src.getBalance()-amount).getBalance());
			//	System.out.println("Updated dest balance:"+accountDao.updateBalance(destincationAccount, dest.getBalance()+amount).getBalance());
			}
		}
		
	}


	@Override
	public void deleteAccount(int accountNumber) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		accountDao.deleteAccount(accountNumber);
	}
}
