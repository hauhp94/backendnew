package c0321g1_pawnshop_backend.dto.pawn_registration;

import c0321g1_pawnshop_backend.dto.contract.TypeProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

<<<<<<< HEAD
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

=======
>>>>>>> b4282ffe7b00faa377054aa2730be124489f086d
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PawnRegistrationDto {
    private Long  pawnRegistrationId;
<<<<<<< HEAD

    @NotBlank
    private String description;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^(090|091|\\(84\\)\\+90|\\(84\\)\\+91)[0-9]{7}$")
    private String phone;

    @NotBlank
    private String address;

    private String status;
    private String registerDate;

    @NotBlank
    private String nameCustomer;
    private TypeProductDto typeProduct;
}
=======
    private String description;
    private String email;
    private String phone;
    private String address;
    private String status;
    private String registerDate;
    private String nameCustomer;
    private TypeProductDto typeProduct;
}
>>>>>>> b4282ffe7b00faa377054aa2730be124489f086d
