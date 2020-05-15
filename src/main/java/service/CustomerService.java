package service;

import constant.MyConstant;
import model.Customer;
import model.Staff;
import org.apache.commons.lang3.exception.ExceptionUtils;
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
}
