//package CourseAdministration.RESTListeners;
//
//import CourseAdministration.Models.CourseInstance;
//import CourseAdministration.Models.CourseInstance;
//import com.google.gson.Gson;
//import com.sun.jersey.core.header.FormDataContentDisposition;
//import com.sun.jersey.multipart.FormDataParam;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import java.io.*;
//import java.text.DateFormat;
//import java.text.FieldPosition;
//import java.text.ParsePosition;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
///**
// * Created by Remco on 4-10-2015.
// */
//
//@Path("/file")
//public class Test {
//
//    @GET
//    public Response test(){
//        return Response.status(200).entity("Test123").build();
//    }
//
//    @POST
//    @Path("/upload")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream, @FormDataParam("file") FormDataContentDisposition fileDetail){
//
//        String content = readFile(uploadedInputStream);
//
//        BufferedReader reader = new BufferedReader(new StringReader(content));
//        String line;
//        ArrayList<CourseInstance> instances = new ArrayList<CourseInstance>();
//        try {
//            while ((line = reader.readLine()) != null){
//                if (line.contains(": ")){
//                    CourseInstance course = new CourseInstance(null, null);
//                    CourseInstance instance = new CourseInstance(null, null, 0);
//                    String[] parts = line.split(": ");
//                    course.setTitle(parts[1]);
//                    line = reader.readLine();
//                    parts = line.split(": ");
//                    course.setCode(parts[1]);
//                    instance.setCourse(course);
//
//                    line = reader.readLine();
//                    parts = line.split(": ");
//                    parts = parts[1].split(" ");
//                    instance.setDuration(Integer.parseInt(parts[0]));
//                    line = reader.readLine();
//                    parts = line.split(": ");
//                    instance.setStartDate(new Date(parts[1]));
//                    instances.add(instance);
//                }
//                else {
//                    continue;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Gson gson = new Gson();
//        return Response.ok(gson.toJson(instances)).build();
//    }
//
//    private String readFile(InputStream uploadedInputStream){
//        try {
//            System.out.println("Total file size to read (in bytes): " + uploadedInputStream.available());
//
//            String fileContent = "";
//            int content;
//            while ((content = uploadedInputStream.read()) != -1){
//                fileContent += (char) content;
//            }
//
//            return fileContent;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "Something went wrong";
//        }
//    }
//}
