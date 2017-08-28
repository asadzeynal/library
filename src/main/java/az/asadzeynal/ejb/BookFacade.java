package az.asadzeynal.ejb;

import az.asadzeynal.entity.Book;
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
public class BookFacade extends AbstractFacade<Book> {

    @PersistenceContext(unitName = "az.asadzeynal_Library_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
    }
    
    public List<Book> findBook(String s, String byWhat){
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> c = cq.from(Book.class);
        Expression<String> path = c.get(byWhat);
        cq.select(c);
        ParameterExpression<String> p = cb.parameter(String.class, byWhat);
        Predicate l1 = cb.like(path, p); 
        cq.where(l1);
        TypedQuery<Book> tq = em.createQuery(cq);
        tq.setParameter(byWhat, s + "%");
        return tq.getResultList();
    }
    
    
    
}
