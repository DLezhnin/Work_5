package inno.lab5.service.impl;

import inno.lab5.exception.EntityNotFoundException;
import inno.lab5.model.Product;
import inno.lab5.model.Register;
import inno.lab5.repository.DatabaseProductRepository;
import inno.lab5.repository.DatabaseRegisterRepository;
import inno.lab5.service.ProductService;
import inno.lab5.service.RegisterService;
import inno.lab5.web.model.request.RegisterRequest;
import inno.lab5.web.model.response.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DatabaseRegisterService implements RegisterService {

    @Autowired
    private final DatabaseRegisterRepository registerRepository;
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
