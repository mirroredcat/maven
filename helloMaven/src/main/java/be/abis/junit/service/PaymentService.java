package be.abis.junit.service;

import be.abis.junit.exception.SalaryTooLowException;
import be.abis.junit.model.Person;

public interface PaymentService {
    void paySalary(Person p) throws SalaryTooLowException;
}
