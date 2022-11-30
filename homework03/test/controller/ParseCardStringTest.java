package controller;

import model.PersonCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ownExceptions.FullNameParseException;
import ownExceptions.GenParseException;
import ownExceptions.PhoneNotFoundException;
import ownExceptions.UnbornPersonException;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ParseCardStringTest {

    ParseCardString pCS;


    @BeforeEach
    void setUp() {
        pCS = new ParseCardString();

    }

    @Test
    void parsePersonStringFstSuccessTest() {
        PersonCard expected = new PersonCard(
                "Иванов", "Иван", "Иванович",
                LocalDate.of(2020, 1, 1),
                81234567890L,  "m");

        PersonCard actual = pCS.parsePersonString("Иванов  Иван Иванович  01.01.2020 81234567890 m");
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void parsePersonStringSecSuccessTest() {
        PersonCard expected = new PersonCard(
                "Иванова", "Иванна", "Ивановна",
                LocalDate.of(970, 2, 28),
                1234567L,  "f");

        PersonCard actual = pCS.parsePersonString("f Иванна Ивановна Иванова  28.02.0970 1234567");
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void parsePersonStringTrdSuccessTest() {
        PersonCard expected = new PersonCard(
                "Юзефович", "Аарон", "Израилевич",
                LocalDate.of(2020, 1, 7),
                8121343L,  "m");

        PersonCard actual = pCS.parsePersonString("Юзефович Аарон Израилевич 07.01.2020 8121343 m");
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void parsePersonStringFrtSuccessTest() {
        PersonCard expected = new PersonCard(
                "Юзефович", "Аарон", "Израилевич",
                LocalDate.of(2020, 1, 7),
                8121343L,  "m");

        // surname moved to last position
        PersonCard actual = pCS.parsePersonString("Аарон Израилевич Юзефович 07.01.2020 8121343 m");
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void parsePersonStringFstFailTest() {

        IllegalStateException exc =  assertThrows(UnbornPersonException.class,
                () -> pCS.parsePersonString("Иванов  Иван Иванович  01.01.2028 81234567890 m"));

        assertEquals("Future Birthday Exception", exc.getMessage());


    }

    @Test
    void parsePersonStringSecFailTest() {

        DateTimeException exc =  assertThrows(DateTimeException.class,
                () -> pCS.parsePersonString("Иванов  Иван Иванович  31.02.2020 81234567890 m"));

        assertEquals("February 31 does not exist.", exc.getMessage());
    }


    @Test
    void parsePersonStringTrdFailTest() {

        IllegalStateException exc =  assertThrows(IllegalStateException.class,
                () -> pCS.parsePersonString("Иванов  Иван Иванович  31.2.2020 81234567890 m"));

        assertEquals("Date of birth not found.", exc.getMessage());
    }

    @Test
    void parsePersonStringFrtFailTest() {

        IllegalStateException exc =  assertThrows(PhoneNotFoundException.class,
                () -> pCS.parsePersonString("Иванов  Иван Иванович  02.02.2020 812 m"));

        assertEquals("Phone number filed parse exception.", exc.getMessage());
    }

    @Test
    void parsePersonStringFveFailTest() {

        IllegalStateException exc =  assertThrows(FullNameParseException.class,
                () -> pCS.parsePersonString("Иванов  Иван Иванов  02.02.2020 81281281281 m"));

        assertEquals("Full-name validation exception: Иванов Иван Иванов", exc.getMessage());
    }

   @Test
    void parsePersonStringSixFailTest() {

        IllegalStateException exc =  assertThrows(FullNameParseException.class,
                () -> pCS.parsePersonString("Ivanov Иван Иванович  02.02.2020 81281281281 m"));

        assertEquals("Full-name validation exception: Cyrillic only.", exc.getMessage());
    }

    @Test
    void parsePersonStringSvnFailTest() {

        IllegalStateException exc =  assertThrows(GenParseException.class,
                () -> pCS.parsePersonString("Иванов Иван Иванович  02.02.2020 81281281281 mf"));

        assertEquals("Gender validation exception", exc.getMessage());
    }


}