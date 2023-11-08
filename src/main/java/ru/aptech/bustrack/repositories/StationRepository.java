package ru.aptech.bustrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.aptech.bustrack.entities.Station;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Long> {

    @Query(value = "SELECT name FROM station WHERE name IN :nameList", nativeQuery = true)
    List<String> findAlreadySavedStationNames(@Param("nameList") List<String> stationNames);

}