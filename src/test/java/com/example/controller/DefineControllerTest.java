package com.example.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class DefineControllerTest {

	@Test
	public void test() {
		DefineController d = new DefineController();
		int result = d.test(1, 1);
		assertEquals(2, result);
	}

}
