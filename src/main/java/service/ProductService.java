package service;

import model.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class ProductService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Product> findAll() {
        System.out.println(sessionFactory.getCurrentSession().createQuery("from Product").list().size());
        return sessionFactory.getCurrentSession().createQuery("from Product").list();

    }

    public List<Inventory> findInventoryProduct(String dateStart, String dateEnd) {
        List<Inventory> resultList = new ArrayList<>();
        Map<String, Integer> receivingProductMap = createReceivingProductMap(dateStart, dateEnd);
        Map<String, Integer> deliveryProductMap = createDeliveryProductMap(dateStart, dateEnd);
        for (Map.Entry<String, Integer> receiving : receivingProductMap.entrySet()) {
            int receivedTotal = receiving.getValue();
            int deliveryTotal = deliveryProductMap.getOrDefault(receiving.getKey(), 0);
            int balance = receivedTotal - deliveryTotal;
            resultList.add(new Inventory(receiving.getKey(), receivedTotal, deliveryTotal, balance));
        }
        return resultList;
    }

    private Map<String, Integer> createReceivingProductMap(String dateStart, String dateEnd) {
        List<ReceivingNote> receivingList = findReceivingList(dateStart, dateEnd);
        Map<String, Integer> resultMap = new HashMap<>();
        for (ReceivingNote item : receivingList) {
            for (Iterator<ReceivingNoteDetail> iterator = item.getReceivingDetailCollection().iterator(); iterator.hasNext(); ) {
                ReceivingNoteDetail detail = iterator.next();
                int total = resultMap.getOrDefault(detail.getProduct().getName(), 0);
                total += detail.getQuantity();
                resultMap.put(detail.getProduct().getName(), total);
            }
        }
        return resultMap;
    }

    private Map<String, Integer> createDeliveryProductMap(String dateStart, String dateEnd) {
        List<DeliveryNote> deliveryList = findDeliveryList(dateStart, dateEnd);
        Map<String, Integer> resultMap = new HashMap<>();
        for (DeliveryNote item : deliveryList) {
            for (Iterator<DeliveryNoteDetail> iterator = item.getDeliveryNoteDetailCollection().iterator(); iterator.hasNext(); ) {
                DeliveryNoteDetail detail = iterator.next();
                int total = resultMap.getOrDefault(detail.getProduct().getName(), 0);
                total += detail.getQuantity();
                resultMap.put(detail.getProduct().getName(), total);
            }
        }
        return resultMap;
    }

    private List<DeliveryNote> findDeliveryList(String dateStart, String dateEnd) {
        Query query = sessionFactory.getCurrentSession().createQuery("from DeliveryNote where date between :dateStart AND :dateEnd");
        query.setString("dateStart", dateStart);
        query.setString("dateEnd", dateEnd);
        return query.list();
    }

    private List<ReceivingNote> findReceivingList(String dateStart, String dateEnd) {
        Query query = sessionFactory.getCurrentSession().createQuery("from ReceivingNote where date between :dateStart AND :dateEnd");
        query.setString("dateStart", dateStart);
        query.setString("dateEnd", dateEnd);
        return query.list();
    }
}
