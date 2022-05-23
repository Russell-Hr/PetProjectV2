package com.example.FinalProject.dao.impl;

import com.example.FinalProject.dao.ParcelDao;
import com.example.FinalProject.entity.Parcel;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Slf4j
@Repository
public class ParcelDaoImpl implements ParcelDao {
    //private Parcel parcel;
    private static final Logger LOGGER = LoggerFactory.getLogger(ParcelDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addParcel(Parcel parcel) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(parcel);
        session.save(parcel);
        LOGGER.info("Parcel has been successfully saved. Parcel details: {}", parcel);
    }

    @Override
    @Transactional(readOnly = true)
    public Parcel getParcelById(int id) {
        Session session = sessionFactory.getCurrentSession();
        //Parcel parcel =(Parcel)session.get(Parcel.class, id);
        //Query query = session.createQuery("select from Parcel p where p.id=id");
        //Parcel parcel = parcels.get(1);
        //Parcel p = query.setString("status", p.getStatus()).uniqueResult();
        //Query query = session.createQuery("select p from Profile p where p.user.username = :username");
        // Profile p = query.setString("username", user.getUsername()).uniqueResult();
        //p.setStatus("john");

        return null;
    }

    @Override
    @Transactional
    public void modifyParcelStatus(String id, String status) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Parcel set status = :status" + " where id = : id");
        query.setParameter("status", status);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void modifyParcelDeliveryDate(String id, Date deliveryDate) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Parcel set deliveryDate = :deliveryDate" + " where id = : id");
        query.setParameter("deliveryDate", deliveryDate);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void modifyParcelReceiptId(String id, String receiptId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Parcel set receiptId = :receiptId" + " where id = : id");
        query.setParameter("receiptId", receiptId);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Parcel> getAll() {
        List<Parcel> parcels = sessionFactory.getCurrentSession().createQuery("from Parcel").list();
        for (Parcel parcel :
                parcels) {
            LOGGER.info("Parcel list: {}", parcel);
        }
        return parcels;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Parcel> getAllByUser(String userId) {
        List<Parcel> parcels = sessionFactory.getCurrentSession().getNamedQuery("parcelsByUserIdAll")
                .setParameter("userId", userId).list();
        for (Parcel parcel :
                parcels) {
            LOGGER.info("Parcel list User All: {}", parcel);
        }
        return parcels;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Parcel> getAllByStatus(String status) {
        List<Parcel> parcels = sessionFactory.getCurrentSession().getNamedQuery("parcelsByStatusAll")
                .setParameter("status", status).list();
        for (Parcel parcel :
                parcels) {
            LOGGER.info("Parcel list Status All: {}", parcel);
        }
        return parcels;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Parcel> getAllByReceipt(Integer userId) {
        List<Parcel> parcels = sessionFactory.getCurrentSession().getNamedQuery("parcelsByUserIdByStatus")
                .setParameter("userId", userId).list();
        for (Parcel parcel :
                parcels) {
            LOGGER.info("Parcel list User All: {}", parcel);
        }
        return parcels;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Parcel> getAllByUserByStatus(Integer userId, String status) {
        List<Parcel> parcels = sessionFactory.getCurrentSession().getNamedQuery("parcelsByUserIdByStatus")
                .setParameter("userId", userId)
                .setParameter("status", status)
                .list();
        return parcels;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Parcel> getAllByUserByReceiptId(Integer userId, Integer receiptId) {
        List<Parcel> parcels = sessionFactory.getCurrentSession().getNamedQuery("parcelsByUserIdByReceiptId")
                .setParameter("userId", userId)
                .setParameter("receiptId", receiptId)
                .list();
        return parcels;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Parcel> getAllByUserWithReceipt(Integer userId) {
        List<Parcel> parcels = sessionFactory.getCurrentSession().getNamedQuery("parcelsByUserIdWithReceipt")
                .setParameter("userId", userId)
                .list();
        return parcels;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Parcel> getAllWithReceipt() {
        List<Parcel> parcels = sessionFactory.getCurrentSession().createQuery("from Parcel p where p.receiptId!=0").list();
        for (Parcel parcel :
                parcels) {
            LOGGER.info("Parcel list: {}", parcel);
        }
        return parcels;
    }

}
