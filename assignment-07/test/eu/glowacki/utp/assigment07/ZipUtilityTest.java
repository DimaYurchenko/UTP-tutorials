package eu.glowacki.utp.assigment07;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.zip.ZipEntry;

public class ZipUtilityTest {

    private ZipUtility zipUtility;

    @Before
    public void before() {
        zipUtility = new ZipUtility("test/eu/glowacki/utp/assigment07/testZip.zip");
    }

    @Test
    public void findByNameTest() {
        final String fileName = "testZip/testFile.txt";
        ZipEntry file = zipUtility.findByName(fileName);
        Assert.assertNotNull(file);
        Assert.assertEquals(fileName, file.getName());
    }

    @Test
    public void findByContentTest() {
        final String content = "Lorem ipsum dolor sit";
        ZipEntry file = zipUtility.findByContent(content);
        Assert.assertNotNull(file);
        Assert.assertEquals("testZip/testFile.txt", file.getName());
    }
}
