package com.donnatto.ccigenerator.service.impl;

import com.donnatto.ccigenerator.model.AccountType;
import com.donnatto.ccigenerator.model.Bank;
import com.donnatto.ccigenerator.service.GeneratorService;
import com.donnatto.ccigenerator.service.OfficeService;
import com.donnatto.ccigenerator.utils.Helpers;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.donnatto.ccigenerator.utils.Helpers.getBankNumber;
import static com.donnatto.ccigenerator.utils.Helpers.getTypeNumber;

@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Autowired
    private Helpers helpers;

    @Autowired
    private OfficeService officeService;

    @Override
    public String generateCCI(Bank bank, AccountType type, String account) {
        String bankNumber;
        String typeNumber;
        String oldOfficeNumber;
        String officeNumber;
        String accountNumber;
        String checkDigit;
        String response = null;

        try {
            bankNumber = getBankNumber(bank);
            typeNumber = getTypeNumber(type);
            if (bank == Bank.BCP2) {
                oldOfficeNumber = account.substring(0, 3);
                officeNumber = officeService.getOfficeByOldNumber(oldOfficeNumber).getNewnumber();
            } else {
                officeNumber = account.substring(0, 3);
            }
            accountNumber = account.substring(3);

            // Only valid with BCP
            if (accountNumber.length() < 12) {
                accountNumber = typeNumber + StringUtils.leftPad(accountNumber, 11, "0");
            }

            // Implement digit check algorithm
            String firstDigit = helpers.calcDigits(bankNumber, officeNumber);
            String secondDigit = helpers.calcDigits(accountNumber);

            checkDigit = firstDigit + secondDigit;

            response = bankNumber + officeNumber + accountNumber + checkDigit;

            System.out.println("First digit -> " + firstDigit);
            System.out.println("Second digit -> " + secondDigit);
            System.out.println("CCI is -> " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
