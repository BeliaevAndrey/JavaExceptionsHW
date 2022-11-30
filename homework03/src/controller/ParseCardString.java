package controller;

import model.PersonCard;
import ownExceptions.FullNameParseException;
import ownExceptions.GenParseException;
import ownExceptions.PhoneNotFoundException;
import ownExceptions.UnbornPersonException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParseCardString {

// Иванов Иван Иванович 01.01.1970 81234567890 m
// 0      1    2        3          4           5

    PersonCard parsePersonString(String cardString) {
        String nameString = "", gender = null;
        LocalDate birthday = null;
        long phone = 0;
        cardString = cardString.replaceAll(" +", " ");
        String[] cardFields = cardString.split(" ");
        for (String field : cardFields) {
            if (field.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                try {
                    birthday = parseDate(field);
                } catch (DateTimeException | UnbornPersonException e) {
                    throw e;
                }
            } else if (field.matches("\\d{6,11}")) {
                phone = Long.parseLong(field);
            } else if (field.length() == 1 && field.matches("\\D")) {
                if (field.matches("[fm]")) {
                    gender = field;
                } else {
                    throw new GenParseException();
                }

            } else if (field.matches("[А-Яа-я]+")) {
                nameString += (field + " ");
            }
        }
        String[] fullName;
        try {
            fullName = parseFullName(nameString);
        } catch (FullNameParseException e) {
            throw e;
        }
        if (birthday == null) throw new IllegalStateException("Date of birth not found.");
        if (phone == 0) throw new PhoneNotFoundException();
        if (gender == null) throw new GenParseException();

        return new PersonCard(fullName[0], fullName[1], fullName[2], birthday, phone, gender);

    }


    private LocalDate parseDate(String dateString) throws DateTimeException, UnbornPersonException {
        String[] check = dateString.split("\\.");
        if(Integer.parseInt(check[1]) == 2 && Integer.parseInt(check[0]) > 29)
            throw new DateTimeException(String.format("February %s does not exist.", check[0]));
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate birthday;
        birthday = LocalDate.parse(dateString, pattern);

        if (birthday.isAfter(LocalDate.now())) {
            throw new UnbornPersonException();
        }
        return birthday;
    }

    private String[] parseFullName(String fullNameString) throws FullNameParseException {
        String[] fullName;
        // [surname 0, name 1, patronymic 2]
        String[] suffixes = {"евич", "ович", "ич", "евна", "овна", "ична"};
        fullNameString = fullNameString.replaceAll(" +", " ").strip();
        int count = 0;
        fullName = fullNameString.split(" ");
        if (fullName.length < 3) throw new FullNameParseException("Cyrillic only.");
        for (String suf : suffixes) {
            if (fullName[1].endsWith(suffixes[0]) || fullName[1].endsWith(suffixes[1]) &&
                    (fullName[2].endsWith(suffixes[0]) || fullName[2].endsWith(suffixes[1]))) {
                count++;
                shiftParts(fullName);
                break;
            } else if ((fullName[0].endsWith(suffixes[0]) || fullName[0].endsWith(suffixes[1])) &&
                    (fullName[2].endsWith(suffixes[0]) || fullName[2].endsWith(suffixes[1]))) {
                count++;
                break;
            }else if ((fullName[0].endsWith(suf) &&
                    (fullName[2].endsWith(suffixes[0]) || fullName[2].endsWith(suffixes[1])))) {
                count++;
                shiftParts(fullName);
                break;
            } else if (fullName[1].endsWith(suf)) {
                fullName = fullNameString.split(" ");
                count++;
                shiftParts(fullName);
                break;
            } else if (fullName[2].endsWith(suf)) {
                fullName = fullNameString.split(" ");
                count++;
                break;
            }

        }
        if (count == 0) throw new FullNameParseException(fullNameString);
        return fullName;
    }

    private void shiftParts(String[] fullName) {
        String tmp0 = fullName[0];
        String tmp1 = fullName[1];
        String tmp2 = fullName[2];
        fullName[1] = tmp0;     // shifting by 1 position
        fullName[2] = tmp1;
        fullName[0] = tmp2;
    }

}
