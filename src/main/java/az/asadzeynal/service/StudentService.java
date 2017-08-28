package az.asadzeynal.service;

import az.asadzeynal.ejb.StudentFacade;
import az.asadzeynal.entity.Student;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author asadzeynal
 */
@WebService(serviceName = "StudentService")
public class StudentService {

    @EJB
    private StudentFacade ejbRef;
    /**
     * Web service operation
     * @param name
     * @param surname
     * @param day
     * @param month
     * @param year
     * @param gpa
     * @return 
     */
    @WebMethod(operationName = "createStudent")
    public String createStudent(String name, String surname, int day, int month, int year, double gpa) {
        LocalDate d = LocalDate.of(year, month, day);
        Student s = new Student(name, surname, d);
        s.setGPA(gpa);
        ejbRef.create(s);
        return "Student " + name + " " + surname +  " created";
    }
    
    /**
     * Web service operation
     * @param name
     * @return 
     */
    @WebMethod(operationName = "findStudentsByName")
    public List<Student> findStudentsByName(String name) {
      return ejbRef.findStudents(name, "name");
    }

    /**
     * Web service operation
     * @param surname
     * @return 
     */
    @WebMethod(operationName = "findStudentBySurname")
    public List<Student> findStudentBySurname(String surname) {
        return ejbRef.findStudents(surname, "surname");
    }

    /**
     * Web service operation
     * @param day
     * @param month
     * @param year
     * @return 
     */
    @WebMethod(operationName = "findStudentByDateOfBirth")
    public List<Student> findStudentByDateOfBirth(int day, int month, int year) {
        return ejbRef.findStudentByBirthDate(LocalDate.of(year, month, day));
    }

    /**
     * Web service operation
     * @param gpa
     * @return 
     */
    @WebMethod(operationName = "findStudentByGpa")
    public List<Student> findStudentByGpa(double gpa) {
        return ejbRef.findStudentByGpa(gpa);
    } 
    
}
