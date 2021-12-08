package eu.glowacki.utp.assigment06;

import java.time.LocalDate;
import java.util.Objects;

public class Teacher extends Person implements Comparable<Teacher> {

    private final AcademicDegree academicDegree;
    private final LocalDate hiredate;

    public Teacher(String firstName, String surname, LocalDate birthDate, Nationality nationality, LocalDate hiredate, AcademicDegree academicDegree) {
        super(firstName, surname, birthDate, nationality);
        this.hiredate = hiredate;
        this.academicDegree = academicDegree;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                super.toString() +
                "academicDegree=" + academicDegree +
                ", hiredate=" + hiredate +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(),
                getSurname(),
                getBirthDate(),
                getPesel(),
                getNationality(),
                academicDegree,
                hiredate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return academicDegree == teacher.academicDegree && Objects.equals(hiredate, teacher.hiredate);
    }

    @Override
    public int compareTo(Teacher o) {
        int surnameComp = getSurname().compareTo(o.getSurname());

        if (surnameComp == 0) {
            int nameComp = getFirstName().compareTo(o.getFirstName());

            if (nameComp == 0) {
                int birthDateComp = getBirthDate().compareTo(o.getBirthDate());

                if (birthDateComp == 0) {
                    int degreeComp = academicDegree.compareTo(o.getAcademicDegree());

                    if (degreeComp == 0) {
                        return hiredate.compareTo(o.getHiredate());
                    }
                    return degreeComp;
                }

                return birthDateComp;
            }
            return nameComp;
        }
        return surnameComp;
    }

    public AcademicDegree getAcademicDegree() {
        return academicDegree;
    }

    public LocalDate getHiredate() {
        return hiredate;
    }



}
