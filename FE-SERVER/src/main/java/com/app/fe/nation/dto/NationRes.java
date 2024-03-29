package com.app.fe.nation.dto;

import com.app.fe.nation.entity.Nation;
import lombok.Builder;
import lombok.Getter;

public class NationRes {

    @Getter
    @Builder
    public static class Detail {
        private Long tblNationId;
        private String countryCode;
        private String countryValue;

        public static Detail of(Nation tblNation) {
            return Detail.builder()
                    .tblNationId(tblNation.getTblNationId())
                    .countryCode(tblNation.getCountryCode().name())
                    .countryValue(tblNation.getCountryCode().getValue())
                    .build();
        }
    }
}
