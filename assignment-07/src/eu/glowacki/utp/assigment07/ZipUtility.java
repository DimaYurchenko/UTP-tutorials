package eu.glowacki.utp.assigment07;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtility {

    private final String path;

    public ZipUtility(String path) {
        this.path = path;
    }


    public ZipEntry findByName(String name) {

        ZipFile zipFile = null;


        try {
            zipFile = new ZipFile(this.path);

            Enumeration<? extends ZipEntry> e = zipFile.entries();

            while (e.hasMoreElements()) {
                ZipEntry entry = e.nextElement();

                if (entry.getName().equals(name)) {
                    return entry;
                }
            }
        }
        catch (IOException ioe) {
            System.out.println("Error opening zip file" + ioe);
        }
        finally {
            try {
                if (zipFile!=null) {
                    zipFile.close();
                }
            }
            catch (IOException ioe) {
                System.out.println("Error while closing zip file" + ioe);
            }
        }

        return null;
    }

    public ZipEntry findByContent(String content) {
        ZipFile zipFile = null;

        try {
            zipFile = new ZipFile(this.path);

            Enumeration<? extends ZipEntry> e = zipFile.entries();

            while (e.hasMoreElements()) {
                ZipEntry entry = e.nextElement();

                InputStream stream = zipFile.getInputStream(entry);
                String text = new BufferedReader(
                        new InputStreamReader(stream, StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining("\n"));

                if (text.contains(content)) {
                    return entry;
                }
            }
        }
        catch (IOException ioe) {
            System.out.println("Error opening zip file" + ioe);
        }
        finally {
            try {
                if (zipFile!=null) {
                    zipFile.close();
                }
            }
            catch (IOException ioe) {
                System.out.println("Error while closing zip file" + ioe);
            }
        }

        return null;
    }
}
