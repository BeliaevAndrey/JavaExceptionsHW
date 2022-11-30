package controller;

import model.PersonCard;

import java.io.*;
import java.nio.file.*;

public class CardFileWriter {

    private Path storagePath;

    public CardFileWriter(Path path) {
        storagePath = path;
    }

     void writeToDisk(PersonCard aCard) {
        Path cardFile = storagePath.resolve(aCard.getSurname() + ".txt");
        boolean newFlag = false;
        if (!Files.exists(cardFile)) {
            createCardFile(cardFile);
            newFlag = true;
        }

        try (FileWriter fw = new FileWriter(cardFile.toString(), true)) {
            fw.append(newFlag ? "" : "\n").append(aCard.toString());
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createCardFile(Path cardFile) {
        try {
            if (!Files.exists(cardFile.getParent()))
                Files.createDirectory(cardFile.getParent());
            Files.createFile(cardFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
