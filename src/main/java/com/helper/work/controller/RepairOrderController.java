package com.helper.work.controller;

import com.helper.work.domain.RepairOrder;
import com.helper.work.dto.RepairOrderDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller("/repair-order")
public class RepairOrderController {

    @PostMapping
    public RepairOrder createRepairOrder(@RequestBody RepairOrderDto dto) {
        return null;
    }
}
