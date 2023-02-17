package com.helper.work.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "repair_orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RepairOrder {

    @Id
    private UUID id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "status")
    private String status;

    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;

    @Column(name = "cost")
    private BigDecimal cost;
}
