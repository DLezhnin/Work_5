package inno.lab5.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name = "tpp_product_register")
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long       id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private Product    productid;
    private String     type;
    private Long       account;
    private String     currencyCode;
    private String     state;
    private String     accountNumber;

}
