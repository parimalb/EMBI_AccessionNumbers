package com.embl.main;

import java.util.List;
import java.util.Scanner;

import com.embl.exception.AccessionNumbersExption;
import com.embl.service.AccessioNumbersService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AccessioNumbersMain {
	final static Log logger = LogFactory.getLog(AccessioNumbersMain.class);

	public static void main(String[] args) {
		logger.info(
				"Please input list a comma separated list of Accession Numbers. For e.g. A00000,A0001,ERR000111,ERR000112 and so on. ");
		Scanner scanner = new Scanner(System.in);
		try {
			String input = scanner.nextLine();
			List<String> sortedList = new AccessioNumbersService().getSortedRangeList(input);
			logger.info("The sorted list is as follow:");
			logger.info(sortedList.toString());

		} catch (AccessionNumbersExption ex) {
			logger.error("Exception while calculating sorted range list:" + ex.getMessage());
		} finally {
			scanner.close();
		}
	}

}
