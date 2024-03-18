package inno.lab5.web.model.request;

import inno.lab5.model.Agreement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private Long       Id;
    private Long       productCodeId;
    private Long       client;
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
    private String     reasonClose;
    private String     state;
    private List<RegisterRequest> productRegisters = new ArrayList<>();
    private List<AgreementRequest> agreements = new ArrayList<>();
}
