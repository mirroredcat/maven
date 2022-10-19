package be.abis.junit.model;

public class Company {
	
	private String name;
	private Address address;
	
	public Company(String name, Address address) {
		this.name = name;
		this.address = address;
	}

	public double calculateTaxToPay(){
		//System.out.println(address.getCountryCode());
		return switch (getAddress().getCountryCode()) {
			case "BE" -> 51.0;
			case "NL" -> 47.0;
			default -> 35.0;
		};
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
	

}
