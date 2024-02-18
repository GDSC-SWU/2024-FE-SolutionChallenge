package com.app.fe.nation.entity;

import com.app.fe.member.code.CountryCode;
import com.app.fe.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Entity
@Table(name = "TBL_NATION")
public class Nation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TBL_NATION_ID")
    private Long tblNationId;

    @Enumerated(EnumType.STRING)
    @Column(name = "COUNTRY_CODE")
    private CountryCode countryCode;

    @Builder
    public Nation(CountryCode countryCode) {
        this.countryCode = countryCode;
    }

    public void update(CountryCode countryCode) {
        this.countryCode = countryCode;
    }
}
