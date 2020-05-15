package service;

import model.Customer;
import model.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class OrderService {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Order> findAll(){
        return sessionFactory.getCurrentSession().createQuery("from Order").list();
    }

}