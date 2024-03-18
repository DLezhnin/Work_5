package inno.lab5.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "agreement")
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private Product productid;
    private String  generalAgreementId;
    private String  supplementaryAgreementId;
    private String  arrangementType;
    private Long    shedulerJobId;
    private String  number;
    private Instant openingDate;
    private Instant closingDate;
    private Instant cancelDate;
    private Long    validityDuration;
    private String  cancellationReason;
    private String  status;
    private Instant interestCalculationDate;
    private Long    interestRate;
    private Long    coefficient;
    private String  coefficientAction;
    private Long    minimumInterestRate;
    private Long    minimumInterestRateCoefficient;
    private String  minimumInterestRateCoefficientAction;
}
