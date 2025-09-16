package br.com.quick_travel.main.modules.Location.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.quick_travel.main.modules.Location.model.LocationModel;

public interface LocationRepository extends JpaRepository<LocationModel, UUID> {
    
}
