package com.donnatto.ccigenerator.utils;

import com.donnatto.ccigenerator.model.AccountType;
import com.donnatto.ccigenerator.model.Bank;
import com.donnatto.ccigenerator.service.OfficeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Helpers {

    @Autowired
    private OfficeServiceImpl service;

    public static String getTypeNumber(AccountType type) throws Exception {
        switch (type) {
            case CTA_CORRIENTE:
                return "0";
            case AHORROS:
                return "1";
            default:
                throw new Exception("Account type is not valid");
        }
    }

    public static String getBankNumber(Bank bank) throws Exception {
        switch (bank) {
            case BCP:
                return  "002";
            case BCP2:
                return "800";
            case INTERBANK:
                return  "003";
            case SCOTIABANK:
                return  "009";
            default:
                throw new Exception("Bank is not valid");
        }
    }

    public String getNewOfficeNumber(String oldNumber) {
        return service.getOfficeByOldNumber(oldNumber).getNewnumber();
    }

    public String calcDigits(String... args) {

        String response = null;
        StringBuilder input = new StringBuilder();

        try {
            for (String arg : args) {
                input.append(arg);
            }

            char[] chars = input.toString().toCharArray();
            int length = chars.length;
            int[] numbers = new int[length];
            int sum = 0;
            int result;

            for (int i = 0; i < length; i++) {
                numbers[i] = Character.getNumericValue(chars[i]);
                numbers[i] = i % 2 == 0 ? numbers[i] : numbers[i] * 2;
                numbers[i] = numbers[i] > 9 ? 1 + numbers[i] % 10 : numbers[i];
                sum += numbers[i];
            }
            result = sum % 10 == 0 ? 0 : 10 - sum % 10;

            response = String.valueOf(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
