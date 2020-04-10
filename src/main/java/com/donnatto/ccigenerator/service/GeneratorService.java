package com.donnatto.ccigenerator.service;

import com.donnatto.ccigenerator.model.AccountType;
import com.donnatto.ccigenerator.model.Bank;

public interface GeneratorService {

    public String generateCCI(Bank bank, AccountType type, String account);
}
