package com.example.bo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Account;
import com.example.entity.Customer;
import com.example.exception.*;
import com.example.projection.AccountSummaryProjection;
import com.example.repository.CustomerRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CustomerBo {

    private final CustomerRepository repo;

    public CustomerBo(CustomerRepository repo) {
        this.repo = repo;
    }

    // 1.1 SAVE CUSTOMER
    public boolean saveCustomer(Customer customer) {

        if (customer == null) {
            throw new InvalidCustomerDataException("Customer cannot be null");
        }

        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new InvalidCustomerDataException("Name cannot be empty");
        }

        if (customer.getDateOfBirth() == null) {
            throw new InvalidCustomerDataException("Date of birth cannot be null");
        }

        if (customer.getEmail() == null || !customer.getEmail().contains("@")
                || !customer.getEmail().endsWith(".com")) {
            throw new InvalidCustomerDataException("Invalid email");
        }

        if (customer.getPhone() == null || customer.getPhone().length() != 10) {
            throw new InvalidCustomerDataException("Phone must be 10 digits");
        }

        if (customer.getAadhaar() == null || customer.getAadhaar().length() != 12) {
            throw new InvalidCustomerDataException("Aadhaar must be 12 digits");
        }

        if (customer.getPan() == null || customer.getPan().length() != 10) {
            throw new InvalidCustomerDataException("PAN must be 10 characters");
        }

        if (customer.getKycStatus() == null) {
            throw new InvalidCustomerDataException("KYC status required");
        }

        customer.setCreatedAt(LocalDateTime.now());

        repo.save(customer);

        return true;
    }

    // 1.2 ADD ACCOUNT
    public boolean addAccount(Integer customerId, Account account) {

        if (customerId == null) {
            throw new InvalidCustomerDataException("Customer ID cannot be null");
        }

        Customer customer = repo.findById(customerId)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found with id: " + customerId));

        if (account == null) {
            throw new InvalidAccountDataException("Account cannot be null");
        }

        if (account.getAccountNumber() == null || account.getAccountNumber().length() != 8) {
            throw new InvalidAccountDataException("Account number must be 8 digits");
        }

        if (account.getBalance() == null || account.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAccountDataException("Balance cannot be negative");
        }

        if (account.getAccountType() == null) {
            throw new InvalidAccountDataException("Account type cannot be null");
        }

        // set opening date
        account.setOpeningDate(LocalDateTime.now());

        // use helper method from Customer entity
        customer.addAccount(account);

        repo.save(customer);

        return true;
    }

    // 2 FIND BY ID
    public Customer findCustomerById(Integer id) {

        if (id == null || id <= 0) {
            throw new InvalidCustomerDataException("Invalid Customer ID");
        }

        return repo.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found with id: " + id));
    }

    // 3 FIND ALL
    public List<Customer> findAllCustomers() {

        List<Customer> customers = repo.findAll();

        if (customers == null || customers.isEmpty()) {
            throw new CustomerNotFoundException("No customers found in the system");
        }

        return customers;
    }

    // 4 FIND BY EMAIL
    public Customer findCustomerByEmail(String email) {

        if (email == null || email.trim().isEmpty()) {
            throw new InvalidCustomerDataException("Email cannot be null or empty");
        }

        if (!email.contains("@") || !email.endsWith(".com")) {
            throw new InvalidCustomerDataException("Invalid email format");
        }

        Customer customer = repo.findByEmail(email);

        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found with email: " + email);
        }

        return customer;
    }

    // 5 FIND BY PHONE
    public Customer findCustomerByPhone(String phone) {

        if (phone == null || phone.trim().isEmpty()) {
            throw new InvalidCustomerDataException("Phone number cannot be null or empty");
        }

        if (phone.length() != 10) {
            throw new InvalidCustomerDataException("Phone number must be exactly 10 digits");
        }

        Customer customer = repo.findByPhone(phone);

        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found with phone: " + phone);
        }

        return customer;
    }

    // 6 ACCOUNT SUMMARY
    public AccountSummaryProjection accSummary(String accountNumber) {

        if (accountNumber == null || accountNumber.length() != 8) {
            throw new InvalidAccountDataException("Account number must be 8 digits");
        }

        AccountSummaryProjection summary =
                repo.findAccountSummaryByAccountNumber(accountNumber);

        if (summary == null) {
            throw new AccountNotFoundException(
                    "Account not found with number: " + accountNumber);
        }

        return summary;
    }

    // 7 MAX BALANCE
    public List<Customer> getCustomersWithMaxBalance() {

        List<Customer> customers = repo.maxBalance();

        if (customers == null || customers.isEmpty()) {
            throw new CustomerNotFoundException(
                    "No customers found with maximum account balance");
        }

        return customers;
    }

    // 8 COUNT BY KYC STATUS
    public Map<Boolean, Long> countCustomersByKycStatus() {

        List<Object[]> result = repo.countByKycStatus();

        if (result == null || result.isEmpty()) {
            throw new CustomerNotFoundException(
                    "No customer data found for KYC status grouping");
        }

        Map<Boolean, Long> kycCountMap = new HashMap<>();

        for (Object[] row : result) {

            Boolean kycStatus = (Boolean) row[0];
            Long count = (Long) row[1];

            kycCountMap.put(kycStatus, count);
        }

        return kycCountMap;
    }

    // 9 SORT BY NAME
    public List<Customer> getAllCustomersSortedByName() {

        List<Customer> customers = repo.findAllByOrderByNameAsc();

        if (customers == null || customers.isEmpty()) {
            throw new CustomerNotFoundException("No customers found in the system");
        }

        return customers;
    }

    // 10 HAVING QUERY
    public List<Customer> findCustomersByTypeHavingMoreThanAccounts(String type, Long count) {

        if (type == null || type.trim().isEmpty()) {
            throw new InvalidAccountDataException("Account type cannot be null or empty");
        }

        if (count == null || count < 0) {
            throw new InvalidAccountDataException("Account count must not be null or negative");
        }

        List<Customer> customers =
                repo.findCustomersByTypeHavingMoreThanAccounts(type, count);

        if (customers == null || customers.isEmpty()) {
            throw new CustomerNotFoundException(
                    "No customers found with more than " + count + " accounts of type " + type);
        }

        return customers;
    }
}