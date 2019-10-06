package piotrholda.filerouter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileTransferServiceTest {

    private static final String FILE_NAME = "test.txt";

    @Value("${source.uri}")
    private String sourceUri;

    @Value("${destination.uri}")
    private String destinationUri;

    private String sourceDir;
    private String destinationDir;

    private File original;
    private File copy;

    @Before
    public void setUp() throws IOException {
        sourceDir = sourceUri.substring(5, sourceUri.indexOf("?"));
        destinationDir = destinationUri.substring(5);
    }

    @Test
    public void shouldCopyFile() throws IOException, InterruptedException {

        // given
        copy = new File(destinationDir, FILE_NAME);
        original = new File(sourceDir, FILE_NAME);

        // when
        original.createNewFile();
        try (Writer writer = new FileWriter(original)) {
            writer.write("abc");
        }
        Thread.sleep(1500);

        // then
        assertFalse(original.exists());
        assertTrue(copy.exists());

    }

    @After
    public void cleanUp() {
        copy.delete();
    }

}
