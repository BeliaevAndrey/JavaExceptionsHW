package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class PersonCard {
    private final String surname;
    private final String name;
    private final String patronymic;
    private final LocalDate birthday;
    private final String gender;
    private final long phone;


    public PersonCard(String surname, String name, String patronymic, LocalDate birthday, long phone, String gender) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.phone = phone;
        this.gender = gender;
    }

    public String getSurname() {
        return surname;
    }

    private String formatDateOfBirth() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return pattern.format(this.birthday);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %d %s",
                this.surname, this.name, this.patronymic,
                formatDateOfBirth(), this.phone, this.gender);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonCard other = (PersonCard) o;
        return phone == other.phone &&
                Objects.equals(surname, other.surname) &&
                Objects.equals(name, other.name) &&
                Objects.equals(patronymic, other.patronymic) &&
                Objects.equals(birthday, other.birthday) &&
                Objects.equals(gender, other.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, patronymic, birthday, gender, phone);
    }
}
