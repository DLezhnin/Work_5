package inno.lab5.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "tpp_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long       id;
    private Long       productCodeId;
    @Column(name = "client_id")
    private Long       clientid;
    private String     type;
    private String     number;
    private Integer    priority;
    private Instant    dateOfConclusion;
    private Instant    startDateTime;
    private Instant    endDateTime;
    private Integer    days;
    private BigDecimal penaltyRate;
    private BigDecimal nso;
    private BigDecimal thresholdAmount;
    private String     requisiteType;
    private String     interestRateType;
    private BigDecimal taxRate;
    private String     reasoneClose;
    private String     state;
    @OneToMany(mappedBy = "productid", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Register> registers = new ArrayList<>();

    @OneToMany(mappedBy = "productid", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Agreement> agreements = new ArrayList<>();

    public void addProductRegister(Register register){
        registers.add(register);
    }

    public void addAgreement(Agreement agreement){
        agreements.add(agreement);
    }

//    public void removeProductRegister(Long productRegisterId) {
//        registers = registers.stream().filter(o -> !o.getId().equals(productRegisterId)).collect(Collectors.toList());
//    }
}
