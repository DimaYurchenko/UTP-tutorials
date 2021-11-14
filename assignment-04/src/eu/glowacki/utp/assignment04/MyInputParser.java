package eu.glowacki.utp.assignment04;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public final class MyInputParser {
	
	// 1. Use regular expresssions (Pattern) for validating input data
	//
	// 2. Convert input string representing date using SimpleDateFormat "yyyy-MM-dd"

	private static final String regex = "[A-Z]\\w+ [A-Z]\\w+ \\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";
	private static final Pattern pattern = Pattern.compile(regex);

	public static List<Person> parse(File file) {

		List<Person> people = new LinkedList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line;
			while ((line = br.readLine()) != null) {
				if (pattern.matcher(line).matches()) {
					String[] data = line.split(" ");
					people.add(new Person(data[0], data[1], simpleDateFormat.parse(data[2])));
				}
			}
		}
		catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return people;
	}
}