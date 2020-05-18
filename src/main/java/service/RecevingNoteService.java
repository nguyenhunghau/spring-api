package service;

import constant.MyConstant;
import model.ReceivingNote;
import model.ReceivingNoteDetail;
import model.Staff;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Transactional
@Service
public class RecevingNoteService {

    @Autowired
    private SessionFactory sessionFactory;

    public List<ReceivingNote> findAll(int pageNumber, int pageSize){
        Query query = sessionFactory.getCurrentSession().createQuery("from ReceivingNote");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.list();
    }


    public int save(ReceivingNote note){
        try {
            for (Iterator<ReceivingNoteDetail> iterator = note.getReceivingDetailCollection().iterator(); iterator.hasNext();) {
                sessionFactory.getCurrentSession().save(iterator.next());
            }
            return (int)sessionFactory.getCurrentSession().save(note);
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return MyConstant.ERROR_INSERT;
        }
    }
    
    public int saveDetail(ReceivingNoteDetail nodeDetail) {
         try {
            return (int)sessionFactory.getCurrentSession().save(nodeDetail);
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return MyConstant.ERROR_INSERT;
        }
    }

    public boolean update(ReceivingNote note){
        try {
            for (Iterator<ReceivingNoteDetail> iterator = note.getReceivingDetailCollection().iterator(); iterator.hasNext();) {
                ReceivingNoteDetail detail = iterator.next();
                if(detail.getId() > 0) {
                    ReceivingNoteDetail detailExist = findDetail(detail.getId());
                    detailExist.merge(detail);
                } else {
                    sessionFactory.getCurrentSession().save(detail);
                }
            }
            
            ReceivingNote noteExist = findOne(note.getId());
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
        ReceivingNote note = findOne(id);
        try {
            sessionFactory.getCurrentSession().delete(note);
            return true;
        } catch (Exception ex) {
            LogUtils.write(ExceptionUtils.getStackTrace(ex));
            return false;
        }
    }

    public ReceivingNote findOne(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from ReceivingNote where id=:id");
        query.setInteger("id", id);

        return (ReceivingNote) query.uniqueResult();
    }
    
    public ReceivingNoteDetail findDetail(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from ReceivingNoteDetail where =:id");
        query.setInteger("id", id);

        return (ReceivingNoteDetail) query.uniqueResult();
    }
}
