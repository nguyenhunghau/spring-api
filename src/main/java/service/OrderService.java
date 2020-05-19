package service;

import constant.MyConstant;
import java.util.Iterator;
import model.Orders;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import model.OrderDetail;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.Query;

@Transactional
@Service
public class OrderService {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Orders> findAll(){
        return sessionFactory.getCurrentSession().createQuery("from Orders").list();
    }
    
     public int save(Orders order) {
        try {
            for (Iterator<OrderDetail> iterator = order.getOrderDetailCollection().iterator(); iterator.hasNext();) {
                sessionFactory.getCurrentSession().save(iterator.next());
            }
            return (int) sessionFactory.getCurrentSession().save(order);
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return MyConstant.ERROR_INSERT;
        }
    }
     
     public boolean update(Orders order) {
        try {
            for (Iterator<OrderDetail> iterator = order.getOrderDetailCollection().iterator(); iterator.hasNext();) {
                OrderDetail detail = iterator.next();
                if (detail.getId() > 0) {
                    OrderDetail detailExist = findDetail(detail.getId());
                    detailExist.merge(detail);
                } else {
                    sessionFactory.getCurrentSession().save(detail);
                }
            }

            Orders orderExist = findOne(order.getId());
            if (orderExist == null) {
                return false;
            }
            orderExist.merge(order);
            return true;
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return false;
        }

    }

    public boolean delete(int id) {
        Orders note = findOne(id);
        try {
            sessionFactory.getCurrentSession().delete(note);
            return true;
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return false;
        }
    }

    public Orders findOne(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Orders where id=:id");
        query.setInteger("id", id);

        return (Orders) query.uniqueResult();
    }

    public OrderDetail findDetail(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from OrderDetail where id=:id");
        query.setInteger("id", id);

        return (OrderDetail) query.uniqueResult();
    }

    public List<Orders> search(String dateStart, String dateEnd) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Orders where date between :dateStart AND :dateEnd");
        query.setString("dateStart", dateStart);
        query.setString("dateEnd", dateEnd);
        return query.list();
    }

}