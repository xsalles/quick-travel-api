package br.com.quick_travel.main.modules.Location.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quick_travel.main.common.dto.ApiResponseDto;
import br.com.quick_travel.main.modules.Location.model.LocationModel;
import br.com.quick_travel.main.modules.Location.service.LocationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponseDto<String>> addLocation(@Valid HttpServletRequest request,
            @RequestBody LocationModel locationModel) {
        return locationService.addLocationForUser(request, locationModel);
    }
}
