package com.customersscheduling.Repository;

import com.customersscheduling.DTO.TimetableDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<TimetableDto, Integer> {

    @Override
    TimetableDto save(TimetableDto entity);
}
