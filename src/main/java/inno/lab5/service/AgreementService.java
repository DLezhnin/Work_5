package inno.lab5.service;

import inno.lab5.model.Agreement;

public interface AgreementService {
    Agreement findById(Long id);
    Agreement save(Agreement agreement);
}
