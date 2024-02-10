package com.app.fe.nation.dto;

import com.app.fe.member.code.CountryCode;
import com.app.fe.nation.entity.Nation;
import lombok.Builder;
import lombok.Getter;

public class NationResDto {

    @Getter
    @Builder
    public static class Detail {
        private Long tblNationId;
        private CountryCode countryCode;

        public static Detail of(Nation tblNation) {
            return Detail.builder()
                    .tblNationId(tblNation.getTblNationId())
                    .countryCode(tblNation.getCountryCode())
                    .build();
        }
    }
}
