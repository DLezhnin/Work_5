package inno.lab5.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name = "tpp_ref_product_register_type")
public class RegisterType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  internalid;
//    @OneToOne
//    @JoinColumn(name = "type")
//    @ToString.Exclude
    private String        value;
    private String        registerTypeName;
//    @OneToOne
//    @JoinColumn(name = "value")
//    @ToString.Exclude
    private String        productClassCode;
    private Instant       registerTypeStartDate;
    private Instant       registerTypeEndDate;
//    @OneToOne
//    @JoinColumn(name = "value")
//    @ToString.Exclude
    private String        accountType;
}
