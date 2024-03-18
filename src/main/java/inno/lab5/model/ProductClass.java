package inno.lab5.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "tpp_ref_product_class")
public class ProductClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        internalid;
    private String      value;
    private String      gbiCode;
    private String      name;
    private String      productRowCode;
    private String      productRowName;
    private String      subclassCode;
    private String      subclassName;
}
