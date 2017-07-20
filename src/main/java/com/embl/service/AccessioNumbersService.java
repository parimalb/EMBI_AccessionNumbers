package com.embl.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.embl.exception.AccessionNumbersExption;

public class AccessioNumbersService {

	final static Log logger = LogFactory.getLog(AccessioNumbersService.class);

	public List<String> getSortedRangeList(String inputList) throws AccessionNumbersExption {

		// TODO Auto-generated method stub
		List<String> sortedElementList = validateInputAndGetSortedList(inputList);
		List<String> rangeList = sortedElementList.stream().collect(RangeCollector.collector());
		return rangeList;
	}

	private List<String> validateInputAndGetSortedList(String inputList) throws AccessionNumbersExption {
		// Check for a blank | Null input list
		if (StringUtils.isBlank(inputList)) {
			throw new AccessionNumbersExption("Input list passed is invalid");
		}
		// check if input list contains empty elements

		String[] elements = StringUtils.split(inputList, ",");
		List<String> elementList = Arrays.stream(elements).sorted().filter(element -> {
			return StringUtils.isNotBlank(element) && element.matches("[a-zA-Z]*[0-9]*");
		}).collect(Collectors.toList());
		if (elementList.isEmpty()) {
			throw new AccessionNumbersExption("Please try passing valid accession elements to input, For e.g. A00000,A0001,ERR000111,ERR000112");
		}
		return elementList;
	}

	private static final class RangeCollector {

		private List<String> results = new ArrayList<String>();

		private String startRange, prevElemnet;

		public void accept(String element) {

			if (prevElemnet != null) {

				if (!(Integer.parseInt(prevElemnet.replaceAll("[^0-9]", ""))
						- Integer.parseInt(element.replaceAll("[^0-9]", "")) == -1

						&& (prevElemnet.length() == element.length()))) {
					endRangeAndAddElement(startRange, prevElemnet);

					startRange = null;
				} else {
					startRange = startRange == null ? prevElemnet : startRange;
				}
			}
			prevElemnet = element;

		}

		private void endRangeAndAddElement(String startRange, String elementToAdd) {
			if (startRange != null) {
				results.add(startRange + "-" + elementToAdd);
			} else {
				results.add(elementToAdd);
			}
		}

		public RangeCollector combine(RangeCollector other) {
			throw new UnsupportedOperationException("Parallel Stream not supported");
		}

		public List<String> finish() {
			endRangeAndAddElement(startRange, prevElemnet);
			return results;
		}

		public static Collector<String, RangeCollector, List<String>> collector() {
			return Collector.of(RangeCollector::new, RangeCollector::accept, RangeCollector::combine,
					RangeCollector::finish);
		}

	}

}
