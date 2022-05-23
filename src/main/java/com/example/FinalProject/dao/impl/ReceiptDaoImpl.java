package com.example.FinalProject.dao.impl;

import com.example.FinalProject.dao.ReceiptDao;
import com.example.FinalProject.entity.Receipt;
import org.apache.logging.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReceiptDaoImpl implements ReceiptDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(ReceiptDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Receipt addReceipt(Receipt receipt) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(receipt);
        return receipt;
    }

    @Override
    @Transactional
    public void modifyReceiptStatus(String id, String status) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Receipt set status = :status" + " where id = : id");
        query.setParameter("status", status);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void modifyReceiptTotal(String id, Double total) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Receipt set total = :total" + " where id = : id");
        query.setParameter("total", total);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Receipt> getAll() {
        List<Receipt> receipts = sessionFactory.getCurrentSession().createQuery("from Receipt").list();
        for (Receipt receipt :
                receipts) {
            LOGGER.info("receipt list: {}", receipt);
        }

        return receipts;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Receipt> getAllByUser(String userId) {
        List<Receipt> receipts = sessionFactory.getCurrentSession().getNamedQuery("receiptsByUserIdAll")
                .setParameter("userId", userId).list();
        for (Receipt receipt :
                receipts) {
            log.info("receipt list User All: {}", receipt);
        }
        return receipts;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Receipt> getAllByUserByStatus(String userId, String status) {
        List<Receipt> receipts = sessionFactory.getCurrentSession().getNamedQuery("receiptsByUserIdByStatus")
                .setParameter("userId", userId)
                .setParameter("status", status)
                .list();
        return receipts;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Receipt> getAllByStatus(String status) {
        List<Receipt> receipts = sessionFactory.getCurrentSession().getNamedQuery("receiptsByStatus")
                .setParameter("status", status)
                .list();
        return receipts;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Receipt> getByIds(List<String> ids) {
        Session session = sessionFactory.getCurrentSession();
        List<Receipt> result = session.byMultipleIds(Receipt.class).multiLoad(ids);
        return result;
    }


    @Override
    @Transactional(readOnly = true)
    public Receipt getById(String receiptId) {
        Session session = sessionFactory.getCurrentSession();
        Receipt receipt = session.get(Receipt.class, receiptId);
        return receipt;
    }

}