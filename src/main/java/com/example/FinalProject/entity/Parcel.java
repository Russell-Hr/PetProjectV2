package com.example.FinalProject.entity;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parcel")
@DynamicInsert
@DynamicUpdate
@Data
@NamedQueries({
        @NamedQuery(name = "parcelsByUserIdAll",
                query = "from Parcel p where p.userId=:userId"),
        @NamedQuery(name = "parcelsByStatusAll",
                query = "from Parcel p where p.status=:status"),
        @NamedQuery(name = "parcelsByUserIdByStatus",
                query = "from Parcel p where p.userId=:userId and p.status=:status")
})
public class Parcel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    //private UUID id = UUID.randomUUID();
    private int id;

    @Column(name = "fromPoint")
    private String fromPoint;

    @Column(name = "toPoint")
    private String toPoint;

    //@Enumerated(EnumType.STRING)
    @Column(name = "deliveryAddress")
    private String deliveryAddress;

    //@Enumerated(EnumType.STRING)
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

    @Column(name = "userId")
    private int userId;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "paymentDate")
    private Date paymentDate;

    @Column(name = "deliveryDate")
    private Date deliveryDate;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "receipt_has_parcel",
            joinColumns = @JoinColumn(name = "parcelId"),
            inverseJoinColumns = @JoinColumn(name = "receiptId")
    )
    private List<Receipt> receipts = new ArrayList<>();

    @ManyToOne
    private User user;

    @Override
    public String toString() {
        return "Parcel{" +
                "id=" + id +
                ", fromPoint='" + fromPoint + '\'' +
                ", toPoint='" + toPoint + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", category='" + category + '\'' +
                ", distance=" + distance +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", weight=" + weight +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                ", createDate=" + createDate +
                ", paymentDate=" + paymentDate +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}
