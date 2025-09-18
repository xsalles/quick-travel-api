package br.com.quick_travel.main.modules.Destinations.service;

import br.com.quick_travel.main.common.dto.ApiResponseDto;
import br.com.quick_travel.main.common.exceptions.EntityAlreadyExistsException;
import br.com.quick_travel.main.modules.Destinations.model.DestinationModel;
import br.com.quick_travel.main.modules.Destinations.repository.DestinationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class DestinationsService {
    @Autowired
    private DestinationsRepository destinationsRepository;

    public ResponseEntity<ApiResponseDto<DestinationModel>> createDestination(DestinationModel destinationModel) {
        if (destinationsRepository.existsByCountryAndCity(destinationModel.getCountry(), destinationModel.getCity())) {
            throw new EntityAlreadyExistsException("This destination already exists.");
        }

        destinationsRepository.save(destinationModel);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseDto<DestinationModel>("Destination created successfully", HttpStatus.CREATED.value(), destinationModel));

    }
}
