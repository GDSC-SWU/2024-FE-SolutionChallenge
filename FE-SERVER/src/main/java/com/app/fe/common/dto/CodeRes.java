package com.app.fe.common.dto;

import com.app.fe.community.code.CommunityType;
import com.app.fe.community.code.FileType;
import com.app.fe.income.code.OrderType;
import com.app.fe.income.code.SalaryType;
import com.app.fe.member.code.CountryCode;
import com.app.fe.member.code.MemberRole;
import com.app.fe.common.util.EnumConverter;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

public class CodeRes {

    @Getter
    @Builder
    public static class Item {
        private Map<String, String> orderType;
        private Map<String, String> salaryType;
        private Map<String, String> communityType;
        private Map<String, String> countryCode;
        private Map<String, String> memberRole;
        private Map<String, String> fileType;

        public static Item of() {
            return Item.builder()
                    .orderType(EnumConverter.convertEnumsToMap(OrderType.class))
                    .salaryType(EnumConverter.convertEnumsToMap(SalaryType.class))
                    .communityType(EnumConverter.convertEnumsToMap(CommunityType.class))
                    .countryCode(EnumConverter.convertEnumsToMap(CountryCode.class))
                    .memberRole(EnumConverter.convertEnumsToMap(MemberRole.class))
                    .fileType(EnumConverter.convertEnumsToMap(FileType.class))
                    .build();
        }
    }
}
