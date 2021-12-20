package com.example.FinalProject.logic;

import com.example.FinalProject.converter.ParcelConverter;
import com.example.FinalProject.converter.ReceiptConverter;
import com.example.FinalProject.dao.ReceiptDao;
import com.example.FinalProject.dto.ParcelDto;
import com.example.FinalProject.dto.ReceiptDto;
import com.example.FinalProject.entity.Parcel;
import com.example.FinalProject.entity.Receipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Service
public class ReceiptServiceImpl implements ReceiptService {
    private static final Logger logger = LoggerFactory.getLogger(ReceiptServiceImpl.class);

    @Autowired
    private ReceiptDao receiptDao;
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private ReceiptConverter receiptConverter;
    @Autowired
    private ParcelConverter parcelConverter;

    @Transactional
    public boolean addReceipt(ReceiptDto receiptDto, List<ParcelDto> parcelsDto) {
        boolean res = false;
        Double total = 0.;
        String status = "Approved";
        System.out.println("Receipt Manager ==> Add receipt");

        for (ParcelDto parcelDto : parcelsDto) {
            Parcel parcel = parcelConverter.asParcel(parcelDto);
            System.out.println("Receipt Manager2 ==> Add receipt" + parcelDto.getId() + status);
            parcelService.modifyParcel(parcelDto.getId(), status);
            System.out.println("Receipt Manager3 ==> Add receipt");
            total = total + parcelDto.getPrice();
        }
        receiptDto.setTotal(total);
        Receipt receipt = receiptConverter.asReceipt(receiptDto);
            long id = receiptDao.addReceipt(receipt);
//            for (Parcel parcel : parcels) {
//                dbManager.addParcelForReceipt(con, (int) id, parcel.getId(), parcel.getPrice());
//            }


        return res;
    }

    public List<ReceiptDto> getReceipts(int id, int userId, String status, Date createDate, Date paymentDate, int sortColumnNumber) {
        List<ReceiptDto> receiptsDtoList = new ArrayList<>();

        if (userId != 0) {
            if (status.equals("All") || status == null) {
                List<Receipt> receipts = receiptDao.getAllByUser(userId);
                for (Receipt receipt :
                        receipts) {
                    receiptsDtoList.add(receiptConverter.asReceiptDto(receipt));
                }
            } else {
                List<Receipt> receipts = receiptDao.getAllByUserByStatus(userId, status);
                for (Receipt receipt :
                        receipts) {
                    receiptsDtoList.add(receiptConverter.asReceiptDto(receipt));
                }
            }
        } else {
            List<Receipt> receipts = receiptDao.getAll();
            for (Receipt receipt :
                    receipts) {
                receiptsDtoList.add(receiptConverter.asReceiptDto(receipt));
            }
        }

        //List<Receipt> receipts = receiptDao.getAllByUser(userId);
//        for (int i = 0; i < receipts.size(); i++) {
//            receipts.get(i).setInfoRoute("Відправлення № " + receipts.get(i).getParcelId() + "<br>" + receipts.get(i).getInfoRoute()
//                    + "<br>" + "Ціна: " + receipts.get(i).getPrice() + " грн.");
//            //logger.info(receipts.get(i));
//        }
//        int listSize = receipts.size();
//        for (int i = 0; i < listSize; i++) {
//            while (((i + 1) < listSize) && (receipts.get(i).getId() == receipts.get(i + 1).getId())) {
//                receipts.get(i).setInfoRoute(receipts.get(i).getInfoRoute() + "<br><br>" + receipts.get(i + 1).getInfoRoute());
//                receipts.remove(i + 1);
//                listSize = listSize - 1;
//            }
//        }


//        for (Receipt receipt :
//                receipts) {
//            receiptsDtoList.add(receiptConverter.asReceiptDto(receipt));
//        }


        return receiptsDtoList;
    }

    public boolean modifyReceipt(int id, String status) {
        List<Receipt> receipts = null;
        boolean res = false;
        Double total = 0.;
        int userId = 0;
        Date createDate = null;
        Date paymentDate = null;
        String statusToFind = null;
        String parcelStatus = null;
        switch (status) {
            case "Payed":
                parcelStatus = "Payed";
                break;
            case "Canceled":
            case "Denied":
                parcelStatus = "Ordered";
                break;
        }
//        Connection con = null;
//        try {
//            con = dbManager.getConnection();
//            con.setAutoCommit(false);
//            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//            receipts = dbManager.findReceiptsByUser(con, id, userId, statusToFind, createDate, paymentDate);
//            dbManager.modifyReceipt(con, id, status);
//            for (Receipt receipt : receipts) {
//                dbManager.modifyParcel(con, receipt.getParcelId(), parcelStatus);
//            }
//            con.commit();
//        } catch (SQLException | DBException ex) {
//            log.error("cannot do modifyReceipt", ex);
//            con.rollback();
//            new DBException("Cannot modify a receipt with id:" + receipts.get(0).getId(), ex);
//        } finally {
//            dbManager.close(con);
//        }
        return res;
    }
}


