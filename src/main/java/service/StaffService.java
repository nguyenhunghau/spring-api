package service;

import constant.MyConstant;
import model.Staff;
import org.apache.commons.lang3.exception.ExceptionUtils;
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

    public int save(Staff staff){
        try {
            return (int)sessionFactory.getCurrentSession().save(staff);
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return MyConstant.ERROR_INSERT;
        }
    }

    public boolean update(Staff staff){
        try {
            Staff staffExisting = findOne(staff.getId());
            if(staffExisting == null) {
                return false;
            }
            staffExisting.merge(staff);
            return true;
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return false;
        }

    }

    public boolean delete(int id){
        Staff staff = findOne(id);
        try {
            sessionFactory.getCurrentSession().delete(staff);
            return true;
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return false;
        }
    }

    public List<Staff> findAll(int pageNumber, int pageSize){
        Query query = sessionFactory.getCurrentSession().createQuery("from Staff");
        query.setFirstResult((pageNumber - 1) * pageSize + 1);
        query.setMaxResults(pageSize);
        return query.list();
    }

    public Staff findOne(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Staff where id=:id");
        query.setInteger("id", id);

        return (Staff) query.uniqueResult();
    }
}
