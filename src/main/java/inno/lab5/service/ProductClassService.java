package inno.lab5.service;

import inno.lab5.model.ProductClass;

public interface ProductClassService {
    ProductClass findByValue(String value);
    ProductClass findById(Long id);
}
