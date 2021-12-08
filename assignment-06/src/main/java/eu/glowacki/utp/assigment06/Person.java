package eu.glowacki.utp.assigment06;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Person {

    private final String firstName;
    private final String surname;
    private final LocalDate birthDate;
    private final Pesel pesel;
    private final Nationality nationality;

    public Person(String firstName, String surname, LocalDate birthDate, Nationality nationality) {
        this.firstName = firstName;
        this.surname = surname;
        this.birthDate = birthDate;
        this.pesel = new Pesel(birthDate);
        this.nationality = nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName)
                && Objects.equals(surname, person.surname)
                && Objects.equals(birthDate, person.birthDate)
                && Objects.equals(pesel, person.pesel)
                && nationality == person.nationality;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", pesel=" + pesel +
                ", nationality=" + nationality +
                '}';
    }



    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Pesel getPesel() {
        return pesel;
    }

    public Nationality getNationality() {
        return nationality;
    }
}
