package inno.lab5.mapper;

import inno.lab5.model.Agreement;
import inno.lab5.model.Product;
import inno.lab5.model.Register;
import inno.lab5.service.AgreementService;
import inno.lab5.service.RegisterService;
import inno.lab5.service.ValidateService;
import inno.lab5.service.impl.DatabaseProductService;
import inno.lab5.web.model.request.AgreementRequest;
import inno.lab5.web.model.request.RegisterRequest;
import inno.lab5.web.model.response.AgreementResponse;
import inno.lab5.web.model.response.ProductResponse;
import inno.lab5.web.model.request.ProductRequest;
import inno.lab5.web.model.response.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    @Autowired
    private final DatabaseProductService productService;

    public Product requestToProduct(ProductRequest request){

        if (request.getId() != null) {
            Product product = productService.findById(request.getId());
            List<Agreement> listAgr = new ArrayList<>();
            List<AgreementRequest> listAgrReq = request.getAgreements();
            for (AgreementRequest lst : listAgrReq) {
                Agreement agreement = requestToAgreement(lst);
                agreement.setProductid(product);
                listAgr.add(agreement);
            }
            product.setAgreements(listAgr);
            return product;
        }else{
            Product product = new Product();
            List<Register> listReg = new ArrayList<>();
            List<RegisterRequest> listRegReq = request.getProductRegisters();
            for (RegisterRequest lst : listRegReq) {
                Register register = requestToRegister(lst);
                register.setProductid(product);
                listReg.add(register);
            }
                product.setProductCodeId(request.getProductCodeId());
                product.setClientid(request.getClient());
                product.setType(request.getType());
                product.setNumber(request.getNumber());
                product.setPriority(request.getPriority());
                product.setDateOfConclusion(request.getDateOfConclusion());
                product.setStartDateTime(request.getStartDateTime());
                product.setEndDateTime(request.getEndDateTime());
                product.setDays(request.getDays());
                product.setPenaltyRate(request.getPenaltyRate());
                product.setNso(request.getNso());
                product.setThresholdAmount(request.getThresholdAmount());
                product.setRequisiteType(request.getRequisiteType());
                product.setInterestRateType(request.getInterestRateType());
                product.setTaxRate(request.getTaxRate());
                product.setReasoneClose(request.getReasonClose());
                product.setState(request.getState());
                product.setRegisters(listReg);
            return product;
            }
    }

    public ProductResponse productToResponse(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setProductCodeId(product.getProductCodeId());
        productResponse.setClient(product.getClientid());
        productResponse.setType(product.getType());
        productResponse.setNumber(product.getNumber());
        productResponse.setPriority(product.getPriority());
        productResponse.setDateOfConclusion(product.getDateOfConclusion());
        productResponse.setStartDateTime(product.getStartDateTime());
        productResponse.setEndDateTime(product.getEndDateTime());
        productResponse.setDays(product.getDays());
        productResponse.setPenaltyRate(product.getPenaltyRate());
        productResponse.setNso(product.getNso());
        productResponse.setThresholdAmount(product.getThresholdAmount());
        productResponse.setRequisiteType(product.getRequisiteType());
        productResponse.setInterestRateType(product.getInterestRateType());
        productResponse.setTaxRate(product.getTaxRate());
        productResponse.setReasonClose(product.getReasoneClose());
        productResponse.setState(product.getState());
        productResponse.setProductRegisters(productRegisterListToResponseList(product.getRegisters()));
        productResponse.setAgreements(agreementListToResponseList(product.getAgreements()));
        return productResponse;
    }

    public Register requestToRegister(RegisterRequest request) {
        Register register = new Register();
        register.setType(request.getType());
        register.setAccount(request.getAccount());
        register.setCurrencyCode(request.getCurrencyCode());
        register.setState(request.getState());
        register.setAccountNumber(request.getAccountNumber());

        return register;
    }

    public RegisterResponse registerToResponse(Register register) {
        RegisterResponse registerResponse = new RegisterResponse();

        registerResponse.setId(register.getId());
        registerResponse.setType(register.getType());
        registerResponse.setAccount(register.getAccount());
        registerResponse.setCurrencyCode(register.getCurrencyCode());
        registerResponse.setState(register.getState());
        registerResponse.setAccountNumber(register.getAccountNumber());

        return registerResponse;
    }

    public List<RegisterResponse> productRegisterListToResponseList(List<Register> registers) {
        return registers.stream()
                .map(this::registerToResponse)
                .collect(Collectors.toList());
    }

    public Agreement requestToAgreement(AgreementRequest request) {
        Agreement agreement = new Agreement();
        agreement.setGeneralAgreementId(request.getGeneralAgreementId());
        agreement.setSupplementaryAgreementId(request.getSupplementaryAgreementId());
        agreement.setArrangementType(request.getArrangementType());
        agreement.setShedulerJobId(request.getShedulerJobId());
        agreement.setNumber(request.getNumber());
        agreement.setOpeningDate(request.getOpeningDate());
        agreement.setCancelDate(request.getCancelDate());
        agreement.setValidityDuration(request.getValidityDuration());
        agreement.setCancellationReason(request.getCancellationReason());
        agreement.setInterestCalculationDate(request.getInterestCalculationDate());
        agreement.setInterestRate(request.getInterestRate());
        agreement.setCoefficient(request.getCoefficient());
        agreement.setCoefficientAction(request.getCoefficientAction());
        agreement.setMinimumInterestRate(request.getMinimumInterestRate());
        agreement.setMinimumInterestRateCoefficient(request.getMinimumInterestRateCoefficient());
        agreement.setMinimumInterestRateCoefficientAction(request.getMinimumInterestRateCoefficientAction());

        return agreement;
    }

    public AgreementResponse agreementToResponse(Agreement agreement) {
        AgreementResponse agreementResponse = new AgreementResponse();

        agreementResponse.setId(agreement.getId());
        agreementResponse.setGeneralAgreementId(agreement.getGeneralAgreementId());
        agreementResponse.setSupplementaryAgreementId(agreement.getSupplementaryAgreementId());
        agreementResponse.setArrangementType(agreement.getArrangementType());
        agreementResponse.setShedulerJobId(agreement.getShedulerJobId());
        agreementResponse.setNumber(agreement.getNumber());
        agreementResponse.setOpeningDate(agreement.getOpeningDate());
        agreementResponse.setCancelDate(agreement.getCancelDate());
        agreementResponse.setValidityDuration(agreement.getValidityDuration());
        agreementResponse.setCancellationReason(agreement.getCancellationReason());
        agreementResponse.setInterestCalculationDate(agreement.getInterestCalculationDate());
        agreementResponse.setInterestRate(agreement.getInterestRate());
        agreementResponse.setCoefficient(agreement.getCoefficient());
        agreementResponse.setCoefficientAction(agreement.getCoefficientAction());
        agreementResponse.setMinimumInterestRate(agreement.getMinimumInterestRate());
        agreementResponse.setMinimumInterestRateCoefficient(agreement.getMinimumInterestRateCoefficient());
        agreementResponse.setMinimumInterestRateCoefficientAction(agreement.getMinimumInterestRateCoefficientAction());

        return agreementResponse;
    }

    public List<AgreementResponse> agreementListToResponseList(List<Agreement> agreements) {
        return agreements.stream()
                .map(this::agreementToResponse)
                .collect(Collectors.toList());
    }
}
