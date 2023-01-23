package com.helper.work.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "regions")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Region {

    @Id
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "zip_codes", nullable = false)
    private String zipCodes;

    @Column(name = "coordinates", nullable = false)
    private String coordinates;

    public Region(String name, List<String> zipCodes, String coordinates) {
        this.name = name;
        this.zipCodes = String.join(",", zipCodes);
        this.coordinates = coordinates;
    }
}
