package com.example.FinalProject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "receipt")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@NamedQueries({@NamedQuery(name = "receiptsByUserIdAll",
        query = "from Receipt r where r.user.id=:userId"),
        @NamedQuery(name = "receiptsByUserIdByStatus",
                query = "from Receipt r where r.user.id=:userId and r.status=:status"),
                @NamedQuery(name = "receiptsByStatus",
                        query = "from Receipt r where r.status=:status")
})

public class Receipt implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id;

    @Lob
    @Column(name = "receiptInfo", length=10000)
    private String receiptInfo;

    @Column(name = "managerId")
    private String managerId;

    @Column(name = "status")
    private String status;

    @Column(name = "total")
    private Double total;

    @Column(name = "createDate")
    private Date createDate;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "receipt", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Parcel> parcels = new ArrayList<>();
}
