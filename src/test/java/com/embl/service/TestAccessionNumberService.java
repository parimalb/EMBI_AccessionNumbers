package com.embl.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.embl.exception.AccessionNumbersExption;

public class TestAccessionNumberService {

	private AccessioNumbersService accessionService;

	@Before
	public void init() {
		accessionService = new AccessioNumbersService();
	}

	@Test(expected = AccessionNumbersExption.class)
	public void testEmptyList() throws AccessionNumbersExption {
		String inputList = "";
		accessionService.getSortedRangeList(inputList);
	}

	@Test(expected = AccessionNumbersExption.class)
	public void testInValidList() throws AccessionNumbersExption {
		String inputList = ",,,";
		accessionService.getSortedRangeList(inputList);
	}

	@Test
	public void testAllElementsInRange() {
		String inputList = "ERR000114,ERR000115,ERR000116";
		List<String> expected = Arrays.asList("ERR000114-ERR000116");
		try {
			List<String> sortedList = accessionService.getSortedRangeList(inputList);
			assertThat(sortedList, is(expected));
		} catch (AccessionNumbersExption e) {
			Assert.fail();
		}

	}

	@Test
	public void testNoElementsInRange() {
		String inputList = "ERR000111,ERR000115,ERR000117";
		List<String> expected = Arrays.asList("ERR000111", "ERR000115", "ERR000117");
		try {
			List<String> sortedList = accessionService.getSortedRangeList(inputList);
			assertThat(sortedList, is(expected));
		} catch (AccessionNumbersExption e) {
			Assert.fail();
		}

	}

	@Test
	public void testMixedElements() {
		String inputList = "ERR000121,ERR000115,ERR000116";
		List<String> expected = Arrays.asList("ERR000115-ERR000116", "ERR000121");
		try {
			List<String> sortedList = accessionService.getSortedRangeList(inputList);
			assertThat(sortedList, is(expected));
		} catch (AccessionNumbersExption e) {
			Assert.fail();
		}

	}

	@Test
	public void testMultipleRanges() {
		String inputList = "ERR000121,ERR000122,ERR000115,ERR000116";
		List<String> expected = Arrays.asList("ERR000115-ERR000116", "ERR000121-ERR000122");
		try {
			List<String> sortedList = accessionService.getSortedRangeList(inputList);
			assertThat(sortedList, is(expected));
		} catch (AccessionNumbersExption e) {
			Assert.fail();
		}

	}
}
