package com.example.service;

import com.example.dto.AccountDTO;
import com.example.dto.CustomerDTO;
import com.example.entity.Account;
import com.example.entity.AccountType;
import com.example.entity.Customer;
import com.example.facade.CustomerFacade;
import com.example.projection.AccountSummaryProjection;
import com.example.response.ResponseObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerService {

	Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerFacade facade;
    
    public CustomerService(CustomerFacade facade) {
        this.facade = facade;
        logger.info("CustomerService started");
    }

    // 1.1 Save Customer
    public ResponseObject<Boolean> saveCustomer(CustomerDTO dto) {

        logger.info("Service: Adding customer with email {}", dto.getEmail());

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setDateOfBirth(dto.getDateOfBirth());
        customer.setAadhaar(dto.getAadhaar());
        customer.setPan(dto.getPan());
        customer.setKycStatus(dto.getKycStatus());

        boolean result = facade.saveCustomer(customer);

        logger.info("Service: Customer added successfully");

        return new ResponseObject<>(200, "Customer saved successfully", result);
    }


    // 1.2 Add Account
    public ResponseObject<Boolean> addAccount(Integer customerId, AccountDTO dto) {

        logger.info("Service: Adding account for customer ID {}", customerId);

        Account account = new Account();
        account.setAccountNumber(dto.getAccountNumber());
        account.setBalance(dto.getBalance());

        AccountType type = new AccountType();
        type.setId(dto.getAccountTypeId());

        account.setAccountType(type);

        boolean result = facade.addAccount(customerId, account);

        logger.info("Service: Account added successfully");

        return new ResponseObject<>(200, "Account added successfully", result);
    }


    // 2 Find By ID
    public ResponseObject<Customer> findCustomerById(Integer id) {

        logger.info("Service: Fetching customer by ID {}", id);

        Customer customer = facade.findCustomerById(id);

        return new ResponseObject<>(200, "Customer retrieved successfully", customer);
    }


    // 3 Find All
    public ResponseObject<List<Customer>> findAllCustomers() {

        logger.info("Service: Fetching all customers");

        List<Customer> customers = facade.findAllCustomers();

        return new ResponseObject<>(200, "Customers retrieved successfully", customers);
    }


    // 4 Find By Email
    public ResponseObject<Customer> findCustomerByEmail(String email) {

        logger.info("Service: Fetching customer by email {}", email);

        Customer customer = facade.findCustomerByEmail(email);

        return new ResponseObject<>(200, "Customer retrieved successfully", customer);
    }


    // 5 Find By Phone
    public ResponseObject<Customer> findCustomerByPhone(String phone) {

        logger.info("Service: Fetching customer by phone {}", phone);

        Customer customer = facade.findCustomerByPhone(phone);

        return new ResponseObject<>(200, "Customer retrieved successfully", customer);
    }


    // 6 Account Summary
    public ResponseObject<AccountSummaryProjection> accSummary(String accountNumber) {

        logger.info("Service: Fetching account summary for {}", accountNumber);

        AccountSummaryProjection summary = facade.accSummary(accountNumber);

        return new ResponseObject<>(200, "Account summary retrieved successfully", summary);
    }


    // 7 Customers With Max Balance
    public ResponseObject<List<Customer>> getCustomersWithMaxBalance() {

        logger.info("Service: Fetching customers with max balance");

        List<Customer> customers = facade.getCustomersWithMaxBalance();

        return new ResponseObject<>(200, "Customers with max balance retrieved", customers);
    }


    // 8 Count By KYC Status
    public ResponseObject<Map<Boolean, Long>> countCustomersByKycStatus() {

        logger.info("Service: Counting customers by KYC status");

        Map<Boolean, Long> result = facade.countCustomersByKycStatus();

        return new ResponseObject<>(200, "KYC count retrieved successfully", result);
    }


    // 9 Sorted By Name
    public ResponseObject<List<Customer>> getAllCustomersSortedByName() {

        logger.info("Service: Fetching customers sorted by name");

        List<Customer> customers = facade.getAllCustomersSortedByName();

        return new ResponseObject<>(200, "Sorted customers retrieved", customers);
    }


    // 10 Customers By Account Type Having More Than Accounts
    public ResponseObject<List<Customer>> findCustomersByTypeHavingMoreThanAccounts(String type, Long count) {

        logger.info("Service: Fetching customers with more than {} {} accounts", count, type);

        List<Customer> customers = facade.findCustomersByTypeHavingMoreThanAccounts(type, count);

        return new ResponseObject<>(200, "Filtered customers retrieved", customers);
    }
}