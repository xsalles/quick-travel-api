package br.com.quick_travel.main.modules.User.model;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import br.com.quick_travel.main.modules.Location.model.LocationModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+$", message = "Name must contain only letters and spaces")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @Length(min = 6, max = 100, message = "Password must be at least 6 characters long and maximum 100 characters")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private LocationModel location;
}
