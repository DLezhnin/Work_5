package inno.lab5.web.model.request;

import inno.lab5.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgreementRequest {
    private Long    id;
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
