package inno.lab5.service.impl;

import inno.lab5.exception.EntityNotFoundException;
import inno.lab5.model.Product;
import inno.lab5.model.Register;
import inno.lab5.repository.RegisterRepository;
import inno.lab5.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements inno.lab5.service.RegisterService {

    @Autowired
    private final RegisterRepository registerRepository;
    @Autowired
    private final ProductService databaseProductService;

    @Override
    public Register save(Register register) {
        Product product = databaseProductService.findById(register.getProductid().getId());
        register.setProductid(product);
        return registerRepository.save(register);
    }

    @Override
    public Register findById(Long id) {
        return registerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Продуктовый регистр с id {0} не найден", id
                )));
    }
}
