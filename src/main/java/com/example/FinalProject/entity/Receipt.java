package com.example.FinalProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receipt")
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@NamedQueries({@NamedQuery(name = "receiptsByUserIdAll",
        query = "from Receipt r where r.userId=:userId"),
        @NamedQuery(name = "receiptsByUserIdByStatus",
                query = "from Receipt r where r.userId=:userId and r.status=:status")
})

//from usertable u, resulttable r where u.id = r.id and r.ispassed = true"




public class Receipt implements Serializable {
    //SELECT a FROM Locality a INNER JOIN a.city c WHERE c.cityId = :cityId
    @Id
    @Column(name = "id")
    private int id;
    //private UUID id = UUID.randomUUID();

    //@ManyToOne
    @JoinColumn(name = "parcelId")
    private int parcelId;

    @Column(name = "userId")
    private int userId;

    //@Enumerated(EnumType.STRING)
    @Column(name = "managerId")
    private int managerId;

    @Column(name = "status")
    private String status;

    @Column(name = "price")
    private Double price;

    //@ManyToOne
    @JoinColumn(name = "total")
    private Double total;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "paymentDate")
    private Date paymentDate;

    @Column(name = "infoUser")
    private String infoUser;

    @Column(name = "infoRoute")
    private String infoRoute;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "receipt_has_parcel",
            joinColumns = @JoinColumn(name = "receiptId"),
            inverseJoinColumns = @JoinColumn(name = "parcelId")
    )
    private List<Parcel> parcels = new ArrayList<>();



    //Getters and setters ommitted for brevity

    public void addParcel(Parcel parcel) {
        parcels.add(parcel);
        parcel.getReceipts().add(this);
    }

    public void removeParcel(Parcel parcel) {
        parcels.remove(parcel);
        parcel.getReceipts().remove(this);
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", parcelId=" + parcelId +
                ", userId=" + userId +
                ", managerId=" + managerId +
                ", status='" + status + '\'' +
                ", price=" + price +
                ", total=" + total +
                ", createDate=" + createDate +
                ", paymentDate=" + paymentDate +
                ", infoUser='" + infoUser + '\'' +
                ", infoRoute='" + infoRoute + '\'' +
                '}';
    }
}
