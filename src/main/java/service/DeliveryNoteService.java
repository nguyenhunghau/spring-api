package service;

import constant.MyConstant;
import model.DeliveryNote;
import model.DeliveryNoteDetail;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import model.ReceivingNote;

@Transactional
@Service
public class DeliveryNoteService {

    @Autowired
    private SessionFactory sessionFactory;

    public List<DeliveryNote> findAll(int pageNumber, int pageSize) {
        Query query = sessionFactory.getCurrentSession().createQuery("from DeliveryNote");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.list();
    }

    public int save(DeliveryNote note) {
        try {
            for (Iterator<DeliveryNoteDetail> iterator = note.getDeliveryNoteDetailCollection().iterator(); iterator.hasNext();) {
                sessionFactory.getCurrentSession().save(iterator.next());
            }
            return (int) sessionFactory.getCurrentSession().save(note);
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return MyConstant.ERROR_INSERT;
        }
    }

    public boolean update(DeliveryNote note) {
        try {
            for (Iterator<DeliveryNoteDetail> iterator = note.getDeliveryNoteDetailCollection().iterator(); iterator.hasNext();) {
                DeliveryNoteDetail detail = iterator.next();
                if (detail.getId() > 0) {
                    DeliveryNoteDetail detailExist = findDetail(detail.getId());
                    detailExist.merge(detail);
                } else {
                    sessionFactory.getCurrentSession().save(detail);
                }
            }

            DeliveryNote noteExist = findOne(note.getId());
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
        DeliveryNote note = findOne(id);
        try {
            sessionFactory.getCurrentSession().delete(note);
            return true;
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return false;
        }
    }

    public DeliveryNote findOne(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from DeliveryNote where id=:id");
        query.setInteger("id", id);

        return (DeliveryNote) query.uniqueResult();
    }

    public DeliveryNoteDetail findDetail(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from DeliveryNoteDetail where id=:id");
        query.setInteger("id", id);

        return (DeliveryNoteDetail) query.uniqueResult();
    }

    public List<DeliveryNote> search(String dateStart, String dateEnd) {
        Query query = sessionFactory.getCurrentSession().createQuery("from DeliveryNote where date between :dateStart AND :dateEnd");
        query.setString("dateStart", dateStart);
        query.setString("dateEnd", dateEnd);
        return query.list();
    }
}
