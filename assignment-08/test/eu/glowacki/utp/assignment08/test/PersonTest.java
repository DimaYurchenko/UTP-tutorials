package eu.glowacki.utp.assignment08.test;

import eu.glowacki.utp.assignment08.Assignment08Exception;
import eu.glowacki.utp.assignment08.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PersonTest {

	private final String filename = "person.bin";
	private Person person;
	private Person deserializedPerson;


	@Before
	public void before() {
		person = new Person("Dmytro", "Yurchenko",
				new GregorianCalendar(2002, Calendar.JUNE, 20).getTime());

		deserializedPerson = null;
	}

	@Test
	public void serializeAndDeserialize() {
		DataOutputStream out = null;

		try {
			out = new DataOutputStream(new FileOutputStream(filename));
			person.serialize(out);
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
			deserializedPerson = Person.deserialize(in);
		} catch (Assignment08Exception | FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Assert.assertNotNull(deserializedPerson);
		Assert.assertEquals(person, deserializedPerson);
	}
}