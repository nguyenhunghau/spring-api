package service;

import constant.MyConstant;
import model.Customer;
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
public class CustomerService {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Customer> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Customer").list();
    }

    public int save(Customer customer) {
        try {
            return (int)sessionFactory.getCurrentSession().save(customer);
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return MyConstant.ERROR_INSERT;
        }
    }

    public boolean update(Customer customer){
        try {
            Customer customerExisting = findOne(customer.getId());
            if(customerExisting == null) {
                return false;
            }
            customerExisting.merge(customer);
            return true;
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return false;
        }

    }

    public boolean delete(int id){
        Customer customer = findOne(id);
        try {
            sessionFactory.getCurrentSession().delete(customer);
            return true;
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return false;
        }
    }

    public Customer findOne(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Customer where id=:id");
        query.setInteger("id", id);

        return (Customer) query.uniqueResult();
    }
}
