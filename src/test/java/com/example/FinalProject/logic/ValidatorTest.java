package com.example.FinalProject.logic;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {

    Validator validator = new Validator();

    @Before
    public void setUp() {
        final Date date = Mockito.mock(Date.class);
        final SimpleDateFormat sdf = Mockito.mock(SimpleDateFormat.class);
        Mockito.when(new SimpleDateFormat("MMyy").format(date)).thenReturn("1125");
    }

    @Test
    void validateCreditCard_WrongDate_Return_False() {
        assertFalse(validator.validateCreditCard("9804400030608294", "10/25"));
    }

    @Test
    void validateCreditCard_Return_True() {
        assertTrue(validator.validateCreditCard("9804400030608294", "12/25"));
    }

    @Test
    void validateCreditCard_WrongNumber_Return_False() {
        assertFalse(validator.validateCreditCard("1111111111111111", "12/25"));
    }




}