package inno.lab5.service.impl;

import inno.lab5.exception.EntityNotFoundException;
import inno.lab5.model.Agreement;
import inno.lab5.model.Product;
import inno.lab5.model.Register;
import inno.lab5.repository.DatabaseAgreementRepository;
import inno.lab5.repository.DatabaseRegisterRepository;
import inno.lab5.service.AgreementService;
import inno.lab5.service.ProductService;
import inno.lab5.web.model.request.AgreementRequest;
import inno.lab5.web.model.response.AgreementResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DatabaseAgreementService implements AgreementService {

    @Autowired
    private final DatabaseAgreementRepository agreementRepository;
    @Autowired
    private final ProductService databaseProductService;

    @Override
    public Agreement findById(Long id) {
        return agreementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Соглашение с id {0} не найдено", id
                )));
    }

    @Override
    public Agreement save(Agreement agreement) {
        Product product = databaseProductService.findById(agreement.getProductid().getId());
        agreement.setProductid(product);
        return agreementRepository.save(agreement);
    }
}

