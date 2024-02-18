package com.app.fe.nation.service;

import com.app.fe.nation.dto.NationRes;
import com.app.fe.nation.repository.NationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class NationService {
    private final NationRepository tblNationRepository;

    public List<NationRes.Detail> list() {
        return tblNationRepository.findAll().stream()
                .map(NationRes.Detail::of).collect(Collectors.toList());
    }
}
