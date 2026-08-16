package com.example.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "account_type")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type_name", nullable = false, unique = true)
    private String typeName;

    // One AccountType → Many Accounts
    @OneToMany(mappedBy = "accountType", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Account> accounts = new ArrayList<>();


    
    public void addAccount(Account account) {
        accounts.add(account);
        account.setAccountType(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.setAccountType(null);
    }


    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}