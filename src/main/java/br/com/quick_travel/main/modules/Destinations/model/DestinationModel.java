package br.com.quick_travel.main.modules.Destinations.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.UUID;

@Data
@Entity(name = "destinations")
public class DestinationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Pattern(regexp = "^[\\p{L} .'-]+$")
    @NotBlank(message = "Country cannot be blank")
    private String country;

    @Pattern(regexp = "^[\\p{L} .'-]+$")
    @NotBlank(message = "City cannot be blank")
    private String city;

    @Range(min = 0, max = 5, message = "Stars must be between 0 and 5")
    @NotNull(message = "Stars cannot be blank")
    private Integer stars;
}
