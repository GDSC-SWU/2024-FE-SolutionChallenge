package com.app.fe.income;

import com.app.fe.income.dto.IncomeReq;
import com.app.fe.income.code.OrderType;
import com.app.fe.income.dto.IncomeRes;
import com.app.fe.common.dto.DefaultRes;
import com.app.fe.income.service.IncomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/income")
@RestController
public class IncomeController {

    private final IncomeService imcomeService;

    @PostMapping("/create")
    public DefaultRes<IncomeReq.Create, IncomeRes.Detail> createIncome(
            @RequestBody IncomeReq.Create create) {
        return new DefaultRes<>(create, imcomeService.createIncome(create));
    }

    @GetMapping("/list")
    public DefaultRes<?, List<IncomeRes.Detail>> listIncome() {
        return new DefaultRes<>(null, imcomeService.listIncome());
    }

    @GetMapping("/detail")
    public DefaultRes<String, IncomeRes.Detail> detailIncome(@RequestParam("tblIncomeId") Long tblIncomeId,
                                                             @RequestParam("orderType") OrderType orderType) {
        return new DefaultRes<>(tblIncomeId.toString(), imcomeService.detailIncome(tblIncomeId, orderType));
    }

    @PostMapping("/update")
    public DefaultRes<IncomeReq.Update, IncomeRes.Detail> updateIncome(
            @RequestBody IncomeReq.Update update) {
        return new DefaultRes<>(update, imcomeService.updateIncome(update));
    }

    @PostMapping("/delete/{tblIncomeId}")
    public DefaultRes<String, String> deleteIncome(@PathVariable Long tblIncomeId) {
        return new DefaultRes<>(tblIncomeId.toString(), imcomeService.deleteIncome(tblIncomeId));
    }

    /*@GetMapping("/work/list")
    public DefaultRes<?, List<WorkDetailRes.Detail>> listWorkDetail(
            @RequestParam("tblIncomeId") Long tblIncomeId,
            @RequestParam("orderValue") String orderValue) {
        return new DefaultRes<>(null, imcomeService.listWorkDetail(tblIncomeId, orderValue));
    }*/
}
