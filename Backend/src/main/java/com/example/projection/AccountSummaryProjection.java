package com.example.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AccountSummaryProjection {

    String getCustomerName();

    String getCustomerEmail();

    String getAccountNumber();

    BigDecimal getBalance();

    String getAccountType();

    LocalDateTime getOpeningDate();
}