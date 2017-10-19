import org.junit.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import webplang.domain.Exercise;
import webplang.domain.Word;
import webplang.repository.WordRepository;
import webplang.service.ExerciseService;

import javax.script.ScriptException;

import java.util.Random;

import static org.springframework.jdbc.datasource.init.ScriptUtils.executeSqlScript;

/**
 * Created by Michał on 2017-04-19.
 */
public class ExerciseTest {

    private static EmbeddedDatabase db;
    private static ExerciseService exerciseService;
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
        exerciseService = new ExerciseService(wordRepository);
    }

    /**
     * Inserts data into test database
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
     * Checks whether exercise is initialized
     * @throws Exception
     */
    @Test
    public void shouldInitialize() throws Exception {

        Exercise testExercise = new Exercise();

        exerciseService.initializeFirstExercise(testExercise);
        Assert.assertTrue(testExercise.getWords().size() == 20);
    }

    /**
     * Checks whether wordInPolish was found
     * @throws Exception
     */
    @Test
    public void shouldFindWordInPolish() throws Exception {

        Exercise testExercise = new Exercise();

        exerciseService.initializeFirstExercise(testExercise);

        Random random = new Random();
        Word randomWord = testExercise.getWords().get(random.nextInt(20));

        String testWordInPolish = testExercise.findWordInPolish(randomWord.getWordInEnglish());

        Assert.assertTrue(testWordInPolish.equals(randomWord.getWordInPolish()));
    }

    /**
     * Checks whether wordInEnglish was found
     * @throws Exception
     */
    @Test
    public void shouldFindWordInEnglish() throws Exception {

        Exercise testExercise = new Exercise();

        exerciseService.initializeFirstExercise(testExercise);

        Random random = new Random();
        Word randomWord = testExercise.getWords().get(random.nextInt(20));

        String testWordInEnglish = testExercise.findWordInEnglish(randomWord.getWordInPolish());

        Assert.assertTrue(testWordInEnglish.equals(randomWord.getWordInEnglish()));
    }


    @AfterClass
    public static void tearDown() {
        db.shutdown();
    }
}
