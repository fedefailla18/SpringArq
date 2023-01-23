package com.helper.work.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "repairs")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Repair {

    @Id
    private UUID id;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private RepairType type;

    @Column(name = "cost")
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "repair_order_id", nullable = false)
    private RepairOrder repairOrder;

    @ManyToOne
    @JoinColumn(name = "type_of_job_id", nullable = false)
    private TypeOfJob typeOfJob;

}
