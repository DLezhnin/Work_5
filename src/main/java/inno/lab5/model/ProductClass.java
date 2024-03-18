package inno.lab5.model;

import jakarta.persistence.*;
import lombok.ToString;

public class ProductClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        internalid;
    @OneToOne
    @JoinColumn(name = "productClassCode")
    @ToString.Exclude
    private String      value;
    private String      gbiCode;
    private String      name;
    private String      productRowCode;
    private String      productRowName;
    private String      subclassCode;
    private String      subclassName;
}
