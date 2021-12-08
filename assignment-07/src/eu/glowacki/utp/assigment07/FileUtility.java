package eu.glowacki.utp.assigment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileUtility implements IUtility{

    private final String path;

    public FileUtility(String path) {
        this.path = path;
    }

    @Override
    public File findByName(String name) throws FileNotFoundException {
        return Arrays.stream(new File(path).listFiles())
                .filter(file -> file.getName().equals(name))
                .findFirst()
                .orElseThrow(FileNotFoundException::new);
    }

    @Override
    public File findByContent(String content) throws FileNotFoundException {
        return Arrays.stream(new File(path).listFiles())
                .filter(file -> {
                    try {
                        Scanner scanner = new Scanner(file);

                        while (scanner.hasNextLine()) {
                            if (scanner.nextLine().contains(content)) {
                                return true;
                            }
                        }
                    } catch(FileNotFoundException e) {
                        return false;
                    }
                    return false;
                })
                .findFirst()
                .orElseThrow(FileNotFoundException::new);
    }
}
