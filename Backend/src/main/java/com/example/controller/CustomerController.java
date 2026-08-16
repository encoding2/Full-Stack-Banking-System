package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.AccountDTO;
import com.example.dto.CustomerDTO;
import com.example.response.ResponseObject;
import com.example.service.CustomerService;


@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    // 1️.1 Save Customer
    @PostMapping
    public ResponseObject<Boolean> saveCustomer(@RequestBody CustomerDTO dto) {

        return service.saveCustomer(dto);
    }

    // 1.2 Add Account for Customer
    @PostMapping("/{customerId}/accounts")
    public ResponseObject<Boolean> addAccount(@PathVariable Integer customerId,@RequestBody AccountDTO dto) {

        return service.addAccount(customerId, dto);
    }

    // 2 Find Customer by ID
    @GetMapping("/{id}")
    public ResponseObject<?> findCustomerById(@PathVariable Integer id) {

        return service.findCustomerById(id);
    }

    // 3 Get All Customers
    @GetMapping
    public ResponseObject<?> findAllCustomers() {

        return service.findAllCustomers();
    }

    // 4 Find Customer by Email
    @GetMapping("/email/{email}")
    public ResponseObject<?> findCustomerByEmail(@PathVariable String email) {

        return service.findCustomerByEmail(email);
    }

    // 5 Find Customer by Phone
    @GetMapping("/phone/{phone}")
    public ResponseObject<?> findCustomerByPhone(@PathVariable String phone) {

        return service.findCustomerByPhone(phone);
    }

    // 6 Account Summary
    @GetMapping("/account-summary/{accountNumber}")
    public ResponseObject<?> accSummary(@PathVariable String accountNumber) {

        return service.accSummary(accountNumber);
    }

    // 7 Customers With Maximum Balance
    @GetMapping("/max-balance")
    public ResponseObject<?> getCustomersWithMaxBalance() {

        return service.getCustomersWithMaxBalance();
    }

    // 8 Count Customers By KYC Status
    @GetMapping("/kyc-count")
    public ResponseObject<?> countCustomersByKycStatus() {

        return service.countCustomersByKycStatus();
    }

    // 9 Get Customers Sorted By Name
    @GetMapping("/sorted-by-name")
    public ResponseObject<?> getAllCustomersSortedByName() {

        return service.getAllCustomersSortedByName();
    }

    // 10 Customers By Account Type Having More Than Accounts
    @GetMapping("/account-type")
    public ResponseObject<?> findCustomersByTypeHavingMoreThanAccounts( @RequestParam String type, @RequestParam Long count) {

        return service.findCustomersByTypeHavingMoreThanAccounts(type, count);
    }
}