package be.abis.junit.model;

import be.abis.junit.exception.PersonShouldBeAdultException;
import be.abis.junit.exception.SalaryTooLowException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import static org.apache.commons.lang3.StringUtils.capitalize;

public class Person implements Comparable<Person>{
	
	private int personNumber;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private Company company;
	private double grossSalary;
	
	public Person(int personNumber, String firstName, String lastName, LocalDate birthDay) {
		this.personNumber = personNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDay;
	}

	public Person(int personNumber, String firstName, String lastName, LocalDate birthDate, Company company, double grossSalary) {
		this(personNumber,firstName,lastName,birthDate);
		this.company = company;
		this.grossSalary = grossSalary;
	}

	public double calculateNetSalary()throws SalaryTooLowException {
		double netSalary = (grossSalary - ((company.calculateTaxToPay()/100) * grossSalary));
		if (netSalary < 1500){
			throw new SalaryTooLowException("Salary under 1500");
		} else {
			return netSalary;
		}
	}

	public String capitalizeFirstName(){
		return capitalize(getFirstName());
	}

	public double getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(double grossSalary) {
		this.grossSalary = grossSalary;
	}

	public int getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(int personNumber) {
		this.personNumber = personNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		String formattedDate= DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.getBirthDate());
		String text = "Person " + this.personNumber + ": " + this.firstName + " " +this.lastName + " was born on " + formattedDate+ ", and ";
		if (this.company != null) {
			text+= "works for " + this.company.getName() + " in " + this.company.getAddress().getTown() + ".";
		} else {
			text+= "is not employed for the moment.";
		}
		return text;
	}
	
	public int calculateAge() throws PersonShouldBeAdultException {
		int age = Period.between(birthDate, LocalDate.now()).getYears();
		if (age<18) throw new PersonShouldBeAdultException(this.getFirstName() + ", you are too young");
		return age;
	}



	@Override
	public int compareTo(Person person) {
		return this.firstName.compareTo(person.firstName);
	}
}
