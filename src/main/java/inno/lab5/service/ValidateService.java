package inno.lab5.service;

import jakarta.xml.bind.ValidationException;

public interface ValidateService {
    void validateProductById (Long id) throws ValidationException;

}
