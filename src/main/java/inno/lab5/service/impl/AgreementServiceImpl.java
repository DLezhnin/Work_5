package inno.lab5.service.impl;

import inno.lab5.exception.EntityNotFoundException;
import inno.lab5.model.Agreement;
import inno.lab5.model.Product;
import inno.lab5.repository.AgreementRepository;
import inno.lab5.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;


@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements inno.lab5.service.AgreementService {

    @Autowired
    private final AgreementRepository agreementRepository;
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

