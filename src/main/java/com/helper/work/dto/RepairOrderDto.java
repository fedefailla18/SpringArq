package com.helper.work.dto;

import com.helper.work.domain.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RepairOrderDto {

    private UUID id;

    private String description;

    private Car car;

    private String status;

    private LocalDateTime appointmentDate;

    private BigDecimal cost;
}
