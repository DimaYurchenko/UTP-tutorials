package eu.glowacki.utp.assigment07;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class FileUtilityTest {

    private FileUtility fileUtility;

    @Before
    public void before() {
        fileUtility = new FileUtility("test/eu/glowacki/utp/assigment07/testDirectory");
    }

    @Test
    public void findByNameTest() throws FileNotFoundException {
        final String fileName = "testFile.txt";
        File file = fileUtility.findByName(fileName);
        Assert.assertNotNull(file);
        Assert.assertEquals(fileName, file.getName());
    }

    @Test
    public void findByContentTest() throws FileNotFoundException{
        final String content = "Lorem ipsum dolor sit";
        File file = fileUtility.findByContent(content);
        Assert.assertNotNull(file);
        Assert.assertEquals("testFile.txt", file.getName());
    }
}
