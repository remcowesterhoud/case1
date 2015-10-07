package CourseAdministrationTests.Utils.CourseInstance;

import CourseAdministration.Utils.CourseInstance.CourseInstanceParser;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Remco on 6-10-2015.
 */
public class CourseInstanceParserTest {

    private CourseInstanceParser parser;

    @Before
    public void setup(){
        parser = new CourseInstanceParser();
    }

    @Test
    public void testReadCourseInstanceFileCorrect(){
        String correct = "Titel: Test Course 1 Correct\n" +
                "Cursuscode: TC1C\n" +
                "Duur: 5 dagen\n" +
                "Startdatum: 14/10/2013\n" +
                "\n" +
                "Titel: Test Course 2 Correct\n" +
                "Cursuscode: TC2C\n" +
                "Duur: 5 dagen\n" +
                "Startdatum: 21/10/2013";
        InputStream in = new ByteArrayInputStream(correct.getBytes(StandardCharsets.UTF_8));

        String result = parser.parse(in);
        assertThat(result, equalTo("Data added succesfully"));
    }

    @Test
    public void testReadCourseInstanceFileDurationInvalid(){
        String correct = "Titel: Test Course 1 Correct\n" +
                "Cursuscode: TC1C\n" +
                "Duur: 8 dagen\n" +
                "Startdatum: 14/10/2013\n" +
                "\n" +
                "Titel: Test Course 2 Correct\n" +
                "Cursuscode: TC2C\n" +
                "Duur: 5 dagen\n" +
                "Startdatum: 21/10/2013";
        InputStream in = new ByteArrayInputStream(correct.getBytes(StandardCharsets.UTF_8));

        String result = parser.parse(in);
        assertThat(result, equalTo("Data invalid at line: 3\nno data has been added"));
    }

    @Test
    public void testReadCourseInstanceFileStartDateInvalid(){
        String correct = "Titel: Test Course 1 Correct\n" +
                "Cursuscode: TC1C\n" +
                "Duur: 5 dagen\n" +
                "Startdatum: 08/10/2015\n" +
                "\n" +
                "Titel: Test Course 2 Correct\n" +
                "Cursuscode: TC2C\n" +
                "Duur: 5 dagen\n" +
                "Startdatum: 21/10/2013";
        InputStream in = new ByteArrayInputStream(correct.getBytes(StandardCharsets.UTF_8));

        String result = parser.parse(in);
        assertThat(result, equalTo("Data invalid at line: 4\nno data has been added"));
    }

    @Test
    public void testReadCourseInstanceFileFormatInvalidMissingLine(){
        String correct = "Titel: Test Course 1 Correct\n" +
                "Cursuscode: TC1C\n" +
                "Duur: 5 dagen\n" +
                "Startdatum: 14/10/2013\n" +
                "Titel: Test Course 2 Correct\n" +
                "Cursuscode: TC2C\n" +
                "Duur: 5 dagen\n" +
                "Startdatum: 21/10/2013";
        InputStream in = new ByteArrayInputStream(correct.getBytes(StandardCharsets.UTF_8));

        String result = parser.parse(in);
        assertThat(result, equalTo("Invalid file format at line: 5"));
    }

    @Test
    public void testReadCourseInstanceFileFormatInvalidNoData(){
        String correct = "Titel:\n" +
                "Cursuscode: TC1C\n" +
                "Duur: 5 dagen\n" +
                "Startdatum: 14/10/2013\n" +
                "\n" +
                "Titel: Test Course 2 Correct\n" +
                "Cursuscode: TC2C\n" +
                "Duur: 5 dagen\n" +
                "Startdatum: 21/10/2013";
        InputStream in = new ByteArrayInputStream(correct.getBytes(StandardCharsets.UTF_8));

        String result = parser.parse(in);
        assertThat(result, equalTo("Invalid file format at line: 1"));
    }
}
