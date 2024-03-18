package inno.lab5.service.impl;

import inno.lab5.model.Product;
import inno.lab5.service.ProductService;
import inno.lab5.service.ValidateService;
import inno.lab5.web.model.request.ProductRequest;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {

    @Autowired
    ProductService productService;

    @Override
    public void validateProductById(Long id) throws ValidationException {
        Product product = productService.findById(id);
        if(product == null){
            throw new ValidationException(MessageFormat.format("Экземпляр продукта с параметром instanceId {0} не найден!",id));
        }
    }
}
