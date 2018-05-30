package com.example.persistance.repository;

import com.example.persistance.entity.CalendarTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarTableRepository  extends JpaRepository<CalendarTable, Integer> {

}
