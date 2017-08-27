import org.junit.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import webplang.domain.Word;
import webplang.repository.WordRepository;
import webplang.service.WordService;

import javax.script.ScriptException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

import static org.springframework.jdbc.datasource.init.ScriptUtils.executeSqlScript;

/**
 * Created by Michał on 2017-04-20.
 */
public class WordTest {

    private static EmbeddedDatabase db;
    private static WordService wordService;
    private static WordRepository wordRepository;

    /**
     * Sets up database for tests
     */
    @BeforeClass
    public static void setUpDB() {
        db = new EmbeddedDatabaseBuilder()
                .setName("test")
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("db-schema.sql")
                .build();

        wordRepository = new WordRepository(db);
        wordService = new WordService(wordRepository);
    }

    /**
     * Inserts data into test database
     *
     * @throws ScriptException
     */
    @Before
    public void setUp() throws ScriptException {
        try {
            ClassPathResource resource = new ClassPathResource("db-data.sql");
            executeSqlScript(db.getConnection(), new EncodedResource(resource, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks whether new word was added
     *
     * @throws Exception
     */
    @Test
    public void shouldAddWord() throws Exception {

        Word testWord = new Word("test jednostkowy", "unit test");
        Word assertedWord = null;

        wordService.addWord(testWord, null);


        Connection conn = db.getConnection();
        Statement stat = conn.createStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM words WHERE wordInPolish = 'test jednostkowy'");

        if (result.next()) {
            assertedWord = new Word(result.getString(2).trim(), result.getString(3).trim());
        }

        if (Optional.of(assertedWord).isPresent())
            Assert.assertTrue(assertedWord.equals(testWord));

    }

    @AfterClass
    public static void tearDown() {
        db.shutdown();
    }
}
