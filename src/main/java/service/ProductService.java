package service;

import model.Product;
import model.Student;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ProductService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    public void saveStudent(Student student){
//        sessionFactory.getCurrentSession().save(student);
//    }

    public List<Product> findAll(){
        System.out.println(sessionFactory.getCurrentSession().createQuery("from Product").list().size());
        return sessionFactory.getCurrentSession().createQuery("from Product").list();

    }
}
