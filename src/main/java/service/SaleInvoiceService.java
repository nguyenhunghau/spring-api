package service;

import constant.MyConstant;
import java.util.ArrayList;
import model.SaleInvoice;
import model.SaleInvoiceDetail;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import model.Customer;
import model.Revenue;
import model.Staff;

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
            for (Iterator<SaleInvoiceDetail> iterator = note.getSaleInvoiceDetailCollection().iterator(); iterator.hasNext();) {
                sessionFactory.getCurrentSession().save(iterator.next());
            }
            return (int) sessionFactory.getCurrentSession().save(note);
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return MyConstant.ERROR_INSERT;
        }
    }

    public boolean update(SaleInvoice note) {
        try {
            for (Iterator<SaleInvoiceDetail> iterator = note.getSaleInvoiceDetailCollection().iterator(); iterator.hasNext();) {
                SaleInvoiceDetail detail = iterator.next();
                if (detail.getId() > 0) {
                    SaleInvoiceDetail detailExist = findDetail(detail.getId());
                    detailExist.merge(detail);
                } else {
                    sessionFactory.getCurrentSession().save(detail);
                }
            }

            SaleInvoice noteExist = findOne(note.getId());
            if (noteExist == null) {
                return false;
            }
            noteExist.merge(note);
            return true;
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return false;
        }

    }

    public boolean delete(int id) {
        SaleInvoice note = findOne(id);
        try {
            sessionFactory.getCurrentSession().delete(note);
            return true;
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return false;
        }
    }

    public SaleInvoice findOne(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from SaleInvoice where id=:id");
        query.setInteger("id", id);

        return (SaleInvoice) query.uniqueResult();
    }

    public List<SaleInvoice> searchInvoice(Integer customerId, Integer staffId, String dateStart, String dateEnd) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("from SaleInvoice where customer.id=:customerId and "
                        + "staff.id=:staffId and date between :dateStart AND :dateEnd");
        query.setInteger("customerId", customerId);
        query.setInteger("staffId", staffId);
        query.setString("dateStart", dateStart);
        query.setString("dateEnd", dateEnd);
        return query.list();
    }

    public List<Revenue> findRevenueByStaff(String dateStart, String dateEnd) {
        Map<Staff, Double> resultMap = new HashMap<>();
        List<Revenue> resultList = new ArrayList<>();

        List<SaleInvoice> saleInvoiceList = searchInvoiceByDate(dateStart, dateEnd);
        for (SaleInvoice invoice : saleInvoiceList) {
            double total = resultMap.getOrDefault(invoice.getStaff(), 0.0);

            for (Iterator<SaleInvoiceDetail> iterator = invoice.getSaleInvoiceDetailCollection().iterator(); iterator.hasNext();) {
                SaleInvoiceDetail detail = iterator.next();
                total += detail.getQuantity() * detail.getPrice();
            }
            resultMap.put(invoice.getStaff(), total);
            
        }
        for(Map.Entry<Staff, Double> entry: resultMap.entrySet()) {
            resultList.add(new Revenue(entry.getKey().getId(), entry.getKey().getName(), entry.getValue()));
        }
        return resultList;
    }

    public List<SaleInvoice> searchInvoiceByDate(String dateStart, String dateEnd) {
        Map<String, Long> map = new HashMap<>();
        Query query = sessionFactory.getCurrentSession().
                createQuery("from SaleInvoice where date between :dateStart AND :dateEnd");

        query.setString("dateStart", dateStart);
        query.setString("dateEnd", dateEnd);
        return query.list();
    }

    public List<Revenue> findRevenueByCustomer(String dateStart, String dateEnd) {
        Map<Customer, Double> resultMap = new HashMap<>();
        List<Revenue> resultList = new ArrayList<>();

        List<SaleInvoice> saleInvoiceList = searchInvoiceByDate(dateStart, dateEnd);
        for (SaleInvoice invoice : saleInvoiceList) {
            double total = resultMap.getOrDefault(invoice.getCustomer(), 0.0);

            for (Iterator<SaleInvoiceDetail> iterator = invoice.getSaleInvoiceDetailCollection().iterator(); iterator.hasNext();) {
                SaleInvoiceDetail detail = iterator.next();
                total += detail.getQuantity() * detail.getPrice();
            }
            resultMap.put(invoice.getCustomer(), total);
        }
        for(Map.Entry<Customer, Double> entry: resultMap.entrySet()) {
            resultList.add(new Revenue(entry.getKey().getId(), entry.getKey().getName(), entry.getValue()));
        }
        return resultList;
    }

    public SaleInvoiceDetail findDetail(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from SaleInvoiceDetail where id=:id");
        query.setInteger("id", id);

        return (SaleInvoiceDetail) query.uniqueResult();
    }
}
