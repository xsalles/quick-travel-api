package br.com.quick_travel.main.modules.Destinations.repository;

import br.com.quick_travel.main.modules.Destinations.model.DestinationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DestinationsRepository extends JpaRepository<DestinationModel, UUID> {
}
