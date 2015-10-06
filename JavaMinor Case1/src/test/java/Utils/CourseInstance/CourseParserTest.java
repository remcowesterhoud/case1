package Utils.CourseInstance;

import CourseAdministration.Utils.CourseInstance.CourseParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by Remco on 6-10-2015.
 */
public class CourseParserTest {

    private CourseParser parser;

    @Before
    public void setup(){
        parser = new CourseParser();
    }

//    @Test
//    public void testReadCourseInstaceFileCorrect(){
//        String correct = "Titel: Test Course 1 Correct\n" +
//                "Cursuscode: TC1C\n" +
//                "Duur: 5 dagen\n" +
//                "Startdatum: 14/10/2013\n" +
//                "\n" +
//                "Titel: Test Course 2 Correct\n" +
//                "Cursuscode: TC2C\n" +
//                "Duur: 5 dagen\n" +
//                "Startdatum: 21/10/2013";
//        InputStream in = new ByteArrayInputStream(correct.getBytes(StandardCharsets.UTF_8));
//
//        String result = parser.readCourseInstanceFile(in);
//        Assert.assertThat("Data added succesfully", equalTo(result));
//    }
}
