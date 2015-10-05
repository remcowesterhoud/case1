package CourseAdministration.Utils.CourseInstance;

import CourseAdministration.Models.Course;
import CourseAdministration.Models.CourseInstance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Remco on 5-10-2015.
 */
public class CourseParser {

    private ArrayList<CourseInstance> courseInstances;
    private int lineNumber;

    public CourseParser(){
        courseInstances = new ArrayList<CourseInstance>();
        lineNumber = 0;
    }

    public String readCourseInstanceFile(InputStream in){
        String content = generateFileString(in);
        String parseMessage = parseFileString(content);
        return parseMessage;
    }

    private String generateFileString(InputStream in){
        String fileContent = "";
        try {
            int content;
            while ((content = in.read()) != -1) {
                fileContent += (char) content;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    private String parseFileString(String content){
        BufferedReader reader = new BufferedReader(new StringReader(content));

        try {
            String line;
            while ((line = reader.readLine()) != null){
                lineNumber++;

                if (!line.isEmpty()) {
                    HashMap<String, String> instanceData = new HashMap<String, String>();

                    //Read all lines of one Course Instance and split them into keys and values
                    for (int i = 0; i < 4; i++) {
                        String[] parts = line.split(": ");
                        instanceData.put(parts[0], parts[1]);
                        line = reader.readLine();
                        lineNumber++;
                    }

                    CourseInstance instance = createCourseInstance(instanceData);

                    if (validateInstance(instance)){
                        courseInstances.add(instance);
                    }
                    else {
                        courseInstances.clear();
                        return "Data invalid at line: " + lineNumber + "\nNo data has been added";
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Data added succesfully";
    }

    private CourseInstance createCourseInstance(HashMap<String, String> instanceData){
        String cursuscode = instanceData.get("Cursuscode");
        String title = instanceData.get("Titel");
        Course course = new Course(cursuscode, title);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = null;
        try {
            startDate = df.parse(instanceData.get("Startdatum"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String durationString = instanceData.get("Duur");
        int duration = Integer.parseInt(durationString.substring(0, durationString.indexOf(" ")));
        CourseInstance instance = new CourseInstance(course, startDate, duration);
        return instance;
    }

    private boolean validateInstance(CourseInstance instance){
        CourseInstanceValidator validator = new CourseInstanceValidator();

        if (!validator.validateCourse(instance.getCourse())){
            lineNumber -= 4;
            return false;
        }
        else if (!validator.validateDuration(instance.getDuration())){
            lineNumber -= 2;
            return false;
        }
        else if(!validator.validateDate(instance.getStartDate(), instance.getDuration())){
            lineNumber -= 1;
            return false;
        }
        else{
            return true;
        }
    }

    public ArrayList<CourseInstance> getCourseInstances() {
        return courseInstances;
    }
}
