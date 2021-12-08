package eu.glowacki.utp.assigment07;

import java.io.File;
import java.io.FileNotFoundException;

public interface IUtility {

    File findByName(String name) throws FileNotFoundException;
    File findByContent(String content)throws FileNotFoundException;
}
