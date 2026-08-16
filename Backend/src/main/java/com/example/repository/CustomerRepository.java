package com.example.repository;

import com.example.entity.Customer;
import com.example.projection.AccountSummaryProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	// 4 Derived Query - Find by Email
	Customer findByEmail(String email);

	// 5 Derived Query - Find by Phone
	Customer findByPhone(String phone);

	// 6 Projection - Account Summary by Account number
	@Query("""
			SELECT c.name AS customerName,
			       c.email AS customerEmail,
			       a.accountNumber AS accountNumber,
			       a.balance AS balance,
			       t.typeName AS accountType,
			       a.openingDate AS openingDate
			FROM Account a
			JOIN a.customer c
			JOIN a.accountType t
			WHERE a.accountNumber = :accountNumber
			""")
	AccountSummaryProjection findAccountSummaryByAccountNumber(String accountNumber);

	// 7 Aggregate - Customers having Account with Max Balance
	@Query("""
			SELECT DISTINCT c
			FROM Customer c
			JOIN c.accounts a
			WHERE a.balance = (SELECT MAX(a2.balance) FROM Account a2)
			""")
	List<Customer> maxBalance();

	// 8 Group By - Count Customers by KYC Status
	@Query("""
			SELECT c.kycStatus, COUNT(c)
			FROM Customer c
			GROUP BY c.kycStatus
			""")
	List<Object[]> countByKycStatus();

	// 9 Order By - Derived Query
	List<Customer> findAllByOrderByNameAsc();

	// 10 HAVING - Customers Having Specific Type AND More Than N Accounts
	@Query("""
			SELECT c
			FROM Customer c
			JOIN c.accounts a
			JOIN a.accountType at
			WHERE at.typeName = :type
			GROUP BY c
			HAVING COUNT(a) > :count
			""")
	List<Customer> findCustomersByTypeHavingMoreThanAccounts(@Param("type") String type, @Param("count") long count);
}