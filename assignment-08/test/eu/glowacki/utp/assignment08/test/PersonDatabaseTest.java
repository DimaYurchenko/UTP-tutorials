package eu.glowacki.utp.assignment08.test;
import eu.glowacki.utp.assignment08.Assignment08Exception;
import eu.glowacki.utp.assignment08.MyInputParser;
import eu.glowacki.utp.assignment08.Person;
import eu.glowacki.utp.assignment08.PersonDatabase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class PersonDatabaseTest {

	private final String filename = "personDataBase.bin";
	private PersonDatabase database;
	private PersonDatabase deserializedDatabase;

	@Before
	public void before() {
		database = new PersonDatabase(
				MyInputParser.parse(new File("test/eu/glowacki/utp/assignment08/test/data.txt"))
		);

		deserializedDatabase = null;
	}


	@Test
	public void serializeAndDeserialize() {

		DataOutputStream out = null;

		try {
			out = new DataOutputStream(new FileOutputStream(filename));
			database.serialize(out);
		} catch (Assignment08Exception | FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		DataInputStream in = null;

		try {
			in = new DataInputStream(new FileInputStream(filename));
			deserializedDatabase = PersonDatabase.deserialize(in);
		} catch (Assignment08Exception | FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Assert.assertNotNull(deserializedDatabase);

		List<Person> people = database.getPeople();
		List<Person> deserializedPeople = deserializedDatabase.getPeople();

		Assert.assertTrue(people.size() == deserializedPeople.size()
				&& people.containsAll(deserializedPeople)
		);
	}
}