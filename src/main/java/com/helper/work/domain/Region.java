package com.helper.work.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "regions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String name;

    @ElementCollection
    @CollectionTable(name = "regions_zip_codes", joinColumns = @JoinColumn(name = "region_id"))
    @Column(name = "zip_codes")
    private List<String> zipCodes;

    @Column(name = "coordinates")
    private String coordinates;

}
