package service;

import model.Staff;
import model.Student;
import model.Teacher;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class StaffService {

    @Autowired
    private SessionFactory sessionFactory;

    public boolean save(Staff staff){
        try {
            sessionFactory.getCurrentSession().save(staff);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

    public boolean delete(int id){
        Staff staff = findOne(id);
        try {
            sessionFactory.getCurrentSession().delete(staff);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public List<Staff> findAll(){
        return sessionFactory.getCurrentSession().createQuery("from Staff").list();
    }

    public Staff findOne(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Staff where id=:id");
        query.setInteger("id", id);

        return (Staff) query.uniqueResult();

    }
}
