package com.example.huwt.oldmanassistant;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void test() {
        assertEquals(User.checkAccountAndPwd("123", "123"), false);
    }
}