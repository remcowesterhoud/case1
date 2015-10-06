package CourseAdministration.Models;

import CourseAdministration.Utils.Paths;

/**
 * Created by Remco on 4-10-2015.
 */
public class BVStudent extends Student {

    private String self;
    private String company, department;
    private int quotationNumber;

    public BVStudent(int id) {
        super(id);
        this.self = Paths.URL.getURI() + Paths.STUDENT.getURI() + id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getQuotationNumber() {
        return quotationNumber;
    }

    public void setQuotationNumber(int quotationNumber) {
        this.quotationNumber = quotationNumber;
    }
}
