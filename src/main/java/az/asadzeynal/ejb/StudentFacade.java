package az.asadzeynal.ejb;

import az.asadzeynal.entity.Student;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author asadzeynal
 */
@Stateless
@TransactionManagement( TransactionManagementType.BEAN )
public class StudentFacade extends AbstractFacade<Student> {
//
//    @Resource
//    private EJBContext context;
//    
    @PersistenceContext(unitName = "az.asadzeynal_Library_1.0PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
//        UserTransaction utx = context.getUserTransaction();
        return em;
    }
    
    public List<Student> findStudents(String s, String byWhat){
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> c = cq.from(Student.class);
        Expression<String> path = c.get(byWhat);
        cq.select(c);
        ParameterExpression<String> p = cb.parameter(String.class, byWhat);
        Predicate l1 = cb.like(path, p); 
        cq.where(l1);
        TypedQuery<Student> tq = em.createQuery(cq);
        tq.setParameter(byWhat, s + "%");
        return tq.getResultList();
    }
    
    
   public List<Student> findStudentByBirthDate(LocalDate birthdate){
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> c = cq.from(Student.class);
        Expression<String> path = c.get("birthdate");
        cq.select(c);
        ParameterExpression<String> p = cb.parameter(String.class, "birthdate");
        Predicate l1 = cb.equal(path, p); 
        cq.where(l1);
        TypedQuery<Student> tq = em.createQuery(cq);
        tq.setParameter("birthdate", birthdate);
        return tq.getResultList();
   }
   
   public List<Student> findStudentByGpa(double gpa){
       CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> c = cq.from(Student.class);
        Expression<String> path = c.get("GPA");
        cq.select(c);
        ParameterExpression<String> p = cb.parameter(String.class, "gpa");
        Predicate l1 = cb.equal(path, p); 
        cq.where(l1);
        TypedQuery<Student> tq = em.createQuery(cq);
        tq.setParameter("gpa", gpa);
        return tq.getResultList();
   }

    public StudentFacade() {
        super(Student.class);
    }
    
}