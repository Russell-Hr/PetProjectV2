package com.example.FinalProject.dao.impl;

import com.example.FinalProject.dao.ReceiptDao;
import com.example.FinalProject.entity.Receipt;
import org.apache.logging.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ReceiptDaoImpl implements ReceiptDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(ReceiptDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long addReceipt(Receipt receipt) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(receipt);
        return 0;
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
    public List<Receipt> getAllByUser(Integer userId) {
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
    public List<Receipt> getAllByUserByStatus(Integer userId, String status) {

        List<Receipt> receipts = sessionFactory.getCurrentSession().getNamedQuery("receiptsByUserIdByStatus")
                .setParameter("userId", userId)
                .setParameter("status", status)
                .list();
        return receipts;
    }



}