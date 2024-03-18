package inno.lab5.service.impl;

import inno.lab5.exception.EntityNotFoundException;
import inno.lab5.model.ProductClass;
import inno.lab5.repository.ProductClassRepository;
import inno.lab5.service.ProductClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
@Service
@RequiredArgsConstructor
public class ProductClassServiceImpl implements ProductClassService {

    @Autowired
    private final ProductClassRepository productClassRepository;

    @Override
    public ProductClass findByValue(String value) {
        return productClassRepository.findByValue(value)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Класс продукта с значением value {0} не найден!", value)));
    }

    @Override
    public ProductClass findById(Long id) {
        return productClassRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Класс продукта с значением value {0} не найден!", id)));
    }
}
