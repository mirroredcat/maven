package be.abis.junit;


import be.abis.junit.exception.PersonShouldBeAdultException;
import be.abis.junit.exception.SalaryTooLowException;
import be.abis.junit.model.Address;
import be.abis.junit.model.Company;
import be.abis.junit.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PersonTest {

    Person p;
    Person p2;

    @Mock
    private Address a;

    @Mock
    private Company c;

    @BeforeEach
    void setUp() {
        p = new Person(1, "John", "Doe", LocalDate.of(1980,2,12));
        p2 = new Person(2, "Alice", "Still", LocalDate.of(2016, 2,2));
    }

    @Test
    void capitalizeFirstName(){
        p = new Person(1, "john", "Doe", LocalDate.of(1980,2,12));
        assertEquals(p.capitalizeFirstName(),"John");
    }

    @Test
    @Order(1)
    void person1ShouldBe42YearsOld() throws PersonShouldBeAdultException {
        assertThat(p.calculateAge(),equalTo(42));
    }

    @Test
    @Order(3)
    void toStringSentenceStartsWithPerson(){
        assertThat(p.toString(), startsWith("Person"));
    }

    @Test
    @Order(2)
    void personIsNotAdultThrowsException() {
        assertThrows(PersonShouldBeAdultException.class, () -> p2.calculateAge());
    }

    @Test
    void calculateNestSalaryOfBelgianPersonUsingMockCompany() throws SalaryTooLowException {
        when(c.calculateTaxToPay()).thenReturn(51.0);
        p.setCompany(c);
        p.setGrossSalary(3500.0);
        assertEquals(p.calculateNetSalary(), 1715.0);
        verify(c).calculateTaxToPay();
    }

    @Test
    void salaryOf3000ThrowsSalaryTooLowException(){
        when(c.calculateTaxToPay()).thenReturn(51.0);
        p.setCompany(c);
        p.setGrossSalary(3000);
        assertThrows(SalaryTooLowException.class, ()->p.calculateNetSalary());
    }
}
