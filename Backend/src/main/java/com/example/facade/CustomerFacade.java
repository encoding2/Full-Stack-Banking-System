package com.example.facade;

import com.example.bo.CustomerBo;
import com.example.entity.Account;
import com.example.entity.Customer;
import com.example.projection.AccountSummaryProjection;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CustomerFacade {

    private final CustomerBo bo;

    public CustomerFacade(CustomerBo bo) {
        this.bo = bo;
    }

    // 1.1 Save Customer
    public boolean saveCustomer(Customer customer) {
        return bo.saveCustomer(customer);
    }

    // 1.2 Add Account
    public boolean addAccount(Integer customerId, Account account) {
        return bo.addAccount(customerId, account);
    }

    // 2 Find By Id
    public Customer findCustomerById(Integer id) {
        return bo.findCustomerById(id);
    }

    // 3 Find All
    public List<Customer> findAllCustomers() {
        return bo.findAllCustomers();
    }

    // 4 Find By Email
    public Customer findCustomerByEmail(String email) {
        return bo.findCustomerByEmail(email);
    }

    // 5 Find By Phone
    public Customer findCustomerByPhone(String phone) {
        return bo.findCustomerByPhone(phone);
    }

    // 6 Account Summary
    public AccountSummaryProjection accSummary(String accountNumber) {
        return bo.accSummary(accountNumber);
    }

    // 7 Max Balance
    public List<Customer> getCustomersWithMaxBalance() {
        return bo.getCustomersWithMaxBalance();
    }

    // 8 Count By KYC Status
    public Map<Boolean, Long> countCustomersByKycStatus() {
        return bo.countCustomersByKycStatus();
    }

    // 9 Sorted By Name
    public List<Customer> getAllCustomersSortedByName() {
        return bo.getAllCustomersSortedByName();
    }

    // 10 Customers by Account Type with Count
    public List<Customer> findCustomersByTypeHavingMoreThanAccounts(String type, Long count) {
        return bo.findCustomersByTypeHavingMoreThanAccounts(type, count);
    }
}