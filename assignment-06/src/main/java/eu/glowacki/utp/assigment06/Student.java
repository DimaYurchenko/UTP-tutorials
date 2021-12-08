package eu.glowacki.utp.assigment06;

import java.time.LocalDate;
import java.util.Objects;

public class Student extends Person implements Comparable<Student> {

    private final Integer bookNumber;
    private static int counter = 0;

    public Student(String firstName, String surname, LocalDate birthDate, Nationality nationality) {
        super(firstName, surname, birthDate, nationality);
        this.bookNumber = 0;
    }

    @Override
    public String toString() {
        return "Student{" +
                super.toString() +
                "bookNumber=" + bookNumber +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(),
                getSurname(),
                getBirthDate(),
                getPesel(),
                getNationality(),
                bookNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return bookNumber == student.getBookNumber();
    }

    @Override
    public int compareTo(Student o) {
        int surnameComp = getSurname().compareTo(o.getSurname());

        if (surnameComp == 0) {
            int nameComp = getFirstName().compareTo(o.getFirstName());

            if (nameComp == 0) {
                int birthDateComp = getBirthDate().compareTo(o.getBirthDate());

                if (birthDateComp == 0) {
                    return bookNumber.compareTo(o.getBookNumber());
                }

                return birthDateComp;
            }
            return nameComp;
        }
        return surnameComp;
    }

    public int getBookNumber() {
        return bookNumber;
    }


}
