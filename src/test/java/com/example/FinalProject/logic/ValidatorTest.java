package com.example.FinalProject.logic;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ValidatorTest {
    Validator validator;

    @BeforeEach
    void setUp() {
        validator = mock(Validator.class);
    }

    @AfterEach
    void tearDown() {
        validator = null;
    }

    @Test
    void validateCreditCard_Return_False() {
        assertFalse(validator.validateCreditCard("1111111111111111", "11/21"));
    }

    @Test
    void validateCreditCard_Return_True() {
        assertTrue(validator.validateCreditCard("4188373028005806", "12/21"));
    }


}