package com.app.fe.config;

import com.app.fe.member.code.CountryCode;
import com.app.fe.nation.entity.Nation;
import com.app.fe.nation.repository.NationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("local")
@RequiredArgsConstructor
@Component
public class LocalAppRunner implements ApplicationRunner {

    private final NationRepository tblNationRepository;

    // 기초 데이터 셋팅
    @Override
    public void run(ApplicationArguments args) throws Exception {
        initTblNation();
    }

    public void initTblNation() {
        log.debug("######## LocalAppRunner initTblNation");
        Nation korea = Nation.builder()
                .countryCode(CountryCode.KOREA)
                .build();
        tblNationRepository.save(korea);

        Nation china = Nation.builder()
                .countryCode(CountryCode.CHINA)
                .build();
        tblNationRepository.save(china);

        Nation unitedStates = Nation.builder()
                .countryCode(CountryCode.UNITED_STATES)
                .build();
        tblNationRepository.save(unitedStates);
    }
}
