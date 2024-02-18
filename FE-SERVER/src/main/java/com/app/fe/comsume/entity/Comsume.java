package com.app.fe.comsume.entity;

import com.app.fe.common.entity.BaseEntity;
import com.app.fe.nation.entity.Nation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Entity
@Table(name = "TBL_COMSUME")
public class Comsume extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TBL_COMSUME_ID")
    private Long tblComsumeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TBL_NATION_ID")
    private Nation tblNation;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRODUCT_ORIGINAL_PRICE")
    private BigDecimal productOriginalPrice;

    @Column(name = "PRODUCT_KOREA_PRICE")
    private BigDecimal productKoreaPrice;
}
