package com.helper.work.operator;

import com.helper.work.domain.Region;
import com.helper.work.dto.RegionDto;
import com.helper.work.repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegionService {

    private final RegionRepository repository;

    public Region addRegion(RegionDto regionDto) {
        return null;
    }

}
