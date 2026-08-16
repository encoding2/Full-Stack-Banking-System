package com.example;

import com.example.entity.Account;
import com.example.entity.AccountType;
import com.example.entity.Customer;
import com.example.response.ResponseObject;
import com.example.service.CustomerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.example")
public class Main {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Main.class, args);

        CustomerService service = context.getBean(CustomerService.class);

      //1
//        Customer customer = new Customer();
//        customer.setName("Komali");
//        customer.setEmail("komali@gmail.com");
//        customer.setPhone("9876543210");
//        customer.setDateOfBirth(LocalDate.of(1998, 5, 10));
//        customer.setAadhaar("123456789012");
//        customer.setPan("ABCDE1234F");
//        customer.setKycStatus(true);
//
//        ResponseObject<Boolean> saveResponse = service.saveCustomer(customer);
//
//        System.out.println(saveResponse.getSuccessMessage());
//        
//        
       //2
//        ResponseObject<Customer> customerResponse = service.findCustomerById(1);
//
//        Customer fetchedCustomer = customerResponse.getData();
//
//        System.out.println("Customer Name: " + fetchedCustomer.getName());
//
//        //3
//        ResponseObject<List<Customer>> allCustomersResponse = service.findAllCustomers();
//
//        List<Customer> customers = allCustomersResponse.getData();
//
//        for (Customer c : customers) {
//            System.out.println("Customer ID: " + c.getId());
//            System.out.println("Name: " + c.getName());
//        }
//        
        
          //4
//        Account account = new Account();
//
//        account.setAccountNumber("10011002");
//        account.setBalance(new BigDecimal("6000"));
//
//        AccountType accountType = new AccountType();
//        accountType.setId(1);
//
//        account.setAccountType(accountType);
//
//        ResponseObject<Boolean> response = service.addAccount(1, account);
//
//        System.out.println("Result: " + response.getData());
//        System.out.println("Message: " + response.getSuccessMessage());
     
        // 5 Find customer by email
//        System.out.println(service.findCustomerByEmail("komali@gmail.com").getData());

        // 6 Find customer by phone
//        System.out.println(service.findCustomerByPhone("9876543210").getData());

        // 7 Account summary
//        System.out.println(service.accSummary("10011002").getData());

        // 8 Customer with max balance
//        System.out.println(service.getCustomersWithMaxBalance().getData());

        // 9 Count KYC verified customers
//        System.out.println(service.countCustomersByKycStatus().getData());

        // 10 Sorted customers
//        service.getAllCustomersSortedByName().getData().forEach(c -> System.out.println(c.getName()));

        // Customers by account type with minimum accounts
//        service.findCustomersByTypeHavingMoreThanAccounts("SAVINGS", 1l).getData().forEach(c -> System.out.println(c.getName()));
       

    }
}