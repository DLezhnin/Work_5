package inno.lab5.service;

import inno.lab5.model.Register;

public interface RegisterService {
    Register findById(Long id);
    Register save(Register register);
}
