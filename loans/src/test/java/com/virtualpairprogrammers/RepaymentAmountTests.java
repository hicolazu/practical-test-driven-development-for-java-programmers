package com.virtualpairprogrammers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RepaymentAmountTests {

    @Spy
    LoanApplication loanApplication;

    LoanCalculatorController controller;
    LoanRepository data;
    JavaMailSender mailSender;
    RestTemplate restTemplate;

    @Before
    public void setup() {
        loanApplication = spy(new LoanApplication());

        controller = new LoanCalculatorController();

        data = mock(LoanRepository.class);
        controller.setData(data);

        mailSender = mock(JavaMailSender.class);
        controller.setMailSender(mailSender);

        restTemplate = mock(RestTemplate.class);
        controller.setRestTemplate(restTemplate);
    }

    @Test
    public void test1YearLoanWholePounds() {
        loanApplication.setPrincipal(1200);
        loanApplication.setTermInMonths(12);
        doReturn(new BigDecimal(10)).when(loanApplication).getInterestRate();

        controller.processNewLoanApplication(loanApplication);

        assertEquals(new BigDecimal(110), loanApplication.getRepayment());
    }

    @Test
    public void test2YearLoanWholePounds() {
        loanApplication.setPrincipal(1200);
        loanApplication.setTermInMonths(24);
        doReturn(new BigDecimal(10)).when(loanApplication).getInterestRate();

        controller.processNewLoanApplication(loanApplication);

        assertEquals(new BigDecimal(60), loanApplication.getRepayment());
    }

    @Test
    public void test5YearLoanWholePounds() {
        loanApplication.setPrincipal(5000);
        loanApplication.setTermInMonths(60);
        doReturn(new BigDecimal("6.5")).when(loanApplication).getInterestRate();

        controller.processNewLoanApplication(loanApplication);

        assertEquals(new BigDecimal(111), loanApplication.getRepayment());
    }
}
