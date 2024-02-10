package com.app.fe.income;

import com.app.fe.income.code.OrderType;
import com.app.fe.income.dto.IncomeReqDto;
import com.app.fe.income.dto.IncomeResDto;
import com.app.fe.income.service.IncomeService;
import com.app.fe.common.dto.DefaultRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/income")
@RestController
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping("/create")
    public DefaultRes<IncomeReqDto.Create, IncomeResDto.Detail> createIncome(
            @RequestBody IncomeReqDto.Create create) {
        return new DefaultRes<>(create, incomeService.createIncome(create));
    }

    @GetMapping("/list")
    public DefaultRes<?, List<IncomeResDto.Detail>> listIncome() {
        return new DefaultRes<>(null, incomeService.listIncome());
    }

    @GetMapping("/detail")
    public DefaultRes<String, IncomeResDto.Detail> detailIncome(@RequestParam("IncomeId") Long IncomeId,
                                                                @RequestParam("orderType") OrderType orderType) {
        return new DefaultRes<>(IncomeId.toString(), incomeService.detailIncome(IncomeId, orderType));
    }

    @PostMapping("/update")
    public DefaultRes<IncomeReqDto.Update, IncomeResDto.Detail> updateIncome(
            @RequestBody IncomeReqDto.Update update) {
        return new DefaultRes<>(update, incomeService.updateIncome(update));
    }

    @PostMapping("/delete/{IncomeId}")
    public DefaultRes<String, String> deleteIncome(@PathVariable Long IncomeId) {
        return new DefaultRes<>(IncomeId.toString(), incomeService.deleteIncome(IncomeId));
    }

    /*@GetMapping("/work/list")
    public DefaultRes<?, List<WorkDetailRes.Detail>> listWorkDetail(
            @RequestParam("IncomeId") Long IncomeId,
            @RequestParam("orderValue") String orderValue) {
        return new DefaultRes<>(null, incomeService.listWorkDetail(IncomeId, orderValue));
    }*/
}
