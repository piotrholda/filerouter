package piotrholda.filerouter;

import org.junit.*;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.model.InitializationError;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;

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
        Thread.sleep(1000);

        // then
        assertFalse(original.exists());
        assertTrue(copy.exists());

    }

    @After
    public void cleanUp() {
copy.delete();
    }

}
