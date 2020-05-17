package service;

import constant.MyConstant;
import model.DeliveryNote;
import model.SaleInvoice;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class SaleInvoiceService {

    @Autowired
    private SessionFactory sessionFactory;

    public List<SaleInvoice> findAll(int pageNumber, int pageSize) {
        Query query = sessionFactory.getCurrentSession().createQuery("from SaleInvoice");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.list();
    }

    public int save(SaleInvoice note) {
        try {
            return (int)sessionFactory.getCurrentSession().save(note);
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return MyConstant.ERROR_INSERT;
        }
    }

    public boolean update(SaleInvoice note){
        try {
            SaleInvoice noteExist = findOne(note.getId());
            if(noteExist == null) {
                return false;
            }
            noteExist.merge(note);
            return true;
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return false;
        }

    }

    public boolean delete(int id){
        SaleInvoice note = findOne(id);
        try {
            sessionFactory.getCurrentSession().delete(note);
            return true;
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return false;
        }
    }

    public SaleInvoice findOne(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from SaleInvoice where id=:id");
        query.setInteger("id", id);

        return (SaleInvoice) query.uniqueResult();
    }

    public List<SaleInvoice> searchInvoice(Integer customerId, Integer staffId, String dateStart, String dateEnd) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("from SaleInvoice where customer.id=:customerId and " +
                        "staff.id=:staffId and date between :dateStart AND :dateEnd");
        query.setInteger("customerId", customerId);
        query.setInteger("staffId", staffId);
        query.setString("dateStart", dateStart);
        query.setString("dateEnd", dateEnd);
        return query.list();
    }

    public Map<String, Long> findRevenueByStaff(String dateStart, String dateEnd) {
        Map<String, Long> map = new HashMap<>();
        Query query = sessionFactory.getCurrentSession().
                createQuery("SELECT staff.name, SUM(quantity * price) from SaleInvoice where date between :dateStart AND :dateEnd group by staff.id");

        query.setString("dateStart", dateStart);
        query.setString("dateEnd", dateEnd);
        List<Object[]> result = query.list();
        for(Object[] item: result) {
            map.put((String)item[0], (long)item[1]);
        }
        return map;
    }

    public Map<String, Long> findRevenueByCustomer(String dateStart, String dateEnd) {
        Map<String, Long> map = new HashMap<>();
        Query query = sessionFactory.getCurrentSession().
                createQuery("SELECT customer.name, SUM(quantity * price) from SaleInvoice where date between :dateStart AND :dateEnd group by customer.id");

        query.setString("dateStart", dateStart);
        query.setString("dateEnd", dateEnd);
        List<Object[]> result = query.list();
        for(Object[] item: result) {
            map.put((String)item[0], (long)item[1]);
        }
        return map;
    }
}
