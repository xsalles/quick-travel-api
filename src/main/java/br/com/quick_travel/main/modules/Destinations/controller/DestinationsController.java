package br.com.quick_travel.main.modules.Destinations.controller;

import br.com.quick_travel.main.common.dto.ApiResponseDto;
import br.com.quick_travel.main.modules.Destinations.model.DestinationModel;
import br.com.quick_travel.main.modules.Destinations.service.DestinationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/destinations")
public class DestinationsController {
    @Autowired
    private DestinationsService destinationsService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<DestinationModel>> createDestination(@RequestBody DestinationModel destinationModel) {
        return destinationsService.createDestination(destinationModel);
    }
}
