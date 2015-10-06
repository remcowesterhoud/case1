package CourseAdministration.Controllers;

import CourseAdministration.Utils.DB.CourseDataHandler;

/**
 * Created by Remco on 6-10-2015.
 */
public class CourseInstanceController {

    public boolean courseInstanceExists(int id){
        CourseDataHandler dataHandler = new CourseDataHandler();
        return dataHandler.courseInstanceExists(id);
    }
}
