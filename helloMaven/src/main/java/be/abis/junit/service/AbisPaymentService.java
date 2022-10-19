package be.abis.junit.service;

import be.abis.junit.exception.SalaryTooLowException;
import be.abis.junit.model.Person;

public class AbisPaymentService implements PaymentService{


    @Override
    public void paySalary(Person p) throws SalaryTooLowException {
        System.out.println("Paying " + p.calculateNetSalary() + " euro to " + p.getFirstName());
    }
}
