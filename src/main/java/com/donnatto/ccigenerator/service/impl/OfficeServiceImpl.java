package com.donnatto.ccigenerator.service.impl;

import com.donnatto.ccigenerator.model.Office;
import com.donnatto.ccigenerator.repository.OfficeRepository;
import com.donnatto.ccigenerator.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private OfficeRepository repository;

    @Override
    public Office getOfficeByOldNumber(String oldNumber) {
        return repository.findByOldnumber(oldNumber);
    }
}
