package br.com.quick_travel.main.modules.Location.model;

import java.util.UUID;

import br.com.quick_travel.main.modules.Location.validation.ValidCep;
import br.com.quick_travel.main.modules.User.model.UserModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "locations")
public class LocationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "CEP cannot be blank")
    @ValidCep
    private String cep;

    @Pattern(regexp = ".{5,100}", message = "Address must be between 5 and 100 characters")
    @NotBlank(message = "Address cannot be blank")
    private String address;

    @Pattern(regexp = "\\d+", message = "Number must be a positive integer")
    @NotBlank(message = "Number cannot be blank")
    private String number;

    private String complement;

    @OneToOne(mappedBy = "location")
    private UserModel user;
}

