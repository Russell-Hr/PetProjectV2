package com.example.FinalProject.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "parcel")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "parcelsByUserIdAll",
                query = "from Parcel"),
       @NamedQuery(name = "parcelsByStatusAll",
                query = "from Parcel"),
        @NamedQuery(name = "parcelsByUserIdByStatus",
                query = "from Parcel"),
        @NamedQuery(name = "parcelsByUserIdByReceiptId",
                query = "from Parcel"),
        @NamedQuery(name = "parcelsByUserIdWithReceipt",
                query = "from Parcel")
})
public class Parcel implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id;

    @Column(name = "fromPoint")
    private String fromPoint;

    @Column(name = "toPoint")
    private String toPoint;

    @Column(name = "deliveryAddress")
    private String deliveryAddress;

    @Column(name = "category")
    private String category;

    @Column(name = "distance")
    private int distance;

    @Column(name = "length")
    private int length;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;

    @Column(name = "weight")
    private double weight;

    @Column(name = "price")
    private double price;

    @Column(name = "status")
    private String status;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "paymentDate")
    private Date paymentDate;

    @Column(name = "deliveryDate")
    private Date deliveryDate;

    @ManyToOne
    private Receipt receipt;

    @ManyToOne
    private User user;

}
