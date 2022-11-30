package controller;

import model.PersonCard;
import view.Display;
import java.nio.file.Path;

public class Controller {

    private final CardFileWriter cfw;
    ParseCardString parseCardString = new ParseCardString();

    public Controller(Path path) {
        this.cfw = new CardFileWriter(path);
    }

    /**
     * Calls StringReader and starts parsing process,
     * then writes card data to disk.
     * buildCard() returns two code values: 0 to keep working
     * and -1 to exit
     */
    public int buildCard() {
        StringReader stringReader = new StringReader();
        String personString = stringReader.getString();
        if (personString.equalsIgnoreCase("exit")) {
            Display.printText("Завершение.");
            return -1;
        } else if (!personString.equals(String.valueOf(Integer.MAX_VALUE))) {
            try {
                PersonCard aCard = parseCardString.parsePersonString(personString);
                Display.printCard(aCard);
                writeCard(aCard);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    private void writeCard(PersonCard card) {
        cfw.writeToDisk(card);
    }

}
