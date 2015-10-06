package CourseAdministration.Models;

import CourseAdministration.Utils.Paths;

/**
 * Created by Remco on 4-10-2015.
 */
public class BVStudent implements Student {

    private final int id;
    private final String self, firstName, lastName, company, department;
    private final int quotationNumber;

    public BVStudent(BVStudentBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.company = builder.company;
        this.department = builder.department;
        this.quotationNumber = builder.quotationNumber;
        this.self = Paths.URL.getURI() + Paths.STUDENT.getURI() + id;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getDepartment() {
        return department;
    }

    public int getQuotationNumber() {
        return quotationNumber;
    }

    public static class BVStudentBuilder {
        private final int id;
        private String firstName, lastName, company, department;
        private int quotationNumber;

        public BVStudentBuilder(){
            this.id = 0;
        }

        public BVStudentBuilder(int id){
            this.id = id;
        }

        public BVStudentBuilder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public BVStudentBuilder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public BVStudentBuilder company(String company){
            this.company = company;
            return this;
        }

        public BVStudentBuilder department(String department){
            this.department = department;
            return this;
        }

        public BVStudentBuilder quotationNumber(int quotationNumber){
            this.quotationNumber = quotationNumber;
            return this;
        }

        public BVStudent build(){
            return new BVStudent(this);
        }

    }
}
