package service;

import constant.MyConstant;
import model.DeliveryNote;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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
            return (int)sessionFactory.getCurrentSession().save(note);
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return MyConstant.ERROR_INSERT;
        }
    }

    public boolean update(DeliveryNote note){
        try {
            DeliveryNote noteExist = findOne(note.getId());
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
        DeliveryNote note = findOne(id);
        try {
            sessionFactory.getCurrentSession().delete(note);
            return true;
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return false;
        }
    }

    public DeliveryNote findOne(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from DeliveryNote where id=:id");
        query.setInteger("id", id);

        return (DeliveryNote) query.uniqueResult();
    }
}
