package cursoSpring.main;

import java.util.Arrays;
import java.util.List;

import cursoSpring.dao.AddressDAO;
import cursoSpring.dao.DocumentDAO;
import cursoSpring.dao.PersonDAO;
import cursoSpring.dao.PhoneDAO;
import cursoSpring.entity.Address;
import cursoSpring.entity.Document;
import cursoSpring.entity.Person;
import cursoSpring.entity.Phone;
import cursoSpring.entity.Address.TypeAddress;
import cursoSpring.entity.Phone.TypePhone;

public class AppSpringJPA {

	public static void main(String[] args) {
		System.out.println("Hello World");
		
//		insertPerson();
//		findPersonById();
//		findAllPersons();
//		countPersons();
//		findBylastName();
//		findByAge();
//		findByFullName();
//		updatePerson();
//		deletePerson();
//		insertDocument();
//		updateDocument();
//		findPersonByCpf();
//		insertPhone();
//		insertPhoneByPerson();
//		updatePhone();
//		updatePhoneByPerson();
//		deleteOnCascade();
		
//		insertAddressByPerson();
//		insertPersonByAddress();
		findByCity();
	}
	
	
	private static void findByCity() {
		List<Address> addresses = new AddressDAO().findByCity("Porto Alegre");
		
		for(Address address : addresses) {
			System.out.println(address.toString());
		}
	}


	private static void insertPersonByAddress() {
		Person person = new PersonDAO().findById(19L);
		
		Address ad1 = new Address();
		ad1.setCity("Porto Alegre");
		ad1.setStreet("Av. Beira Rio, 102");
		ad1.setType(TypeAddress.RESIDENCIAL);
		ad1.setPersons(Arrays.asList(person));
		
		AddressDAO dao = new AddressDAO();
		dao.save(ad1);
		
		System.out.println(dao.findById(ad1.getId()));
	}


	private static void insertAddressByPerson() {
		Address ad1 = new Address();
		Address ad2 = new Address();
		
		ad1.setCity("Porto Alegre");
		ad1.setStreet("Av. Beira Rio, 102");
		ad1.setType(TypeAddress.RESIDENCIAL);
		
		ad2.setCity("Porto Alegre");
		ad2.setStreet("Av. Floresta, 36");
		ad2.setType(TypeAddress.COMERCIAL);
		
		Person person = new Person();
		person.setFirstName("Isabel");
		person.setLastName("Martins");
		person.setAge(20);
		person.setDocument(new Document("322.456.789-11", 391148023));
		person.addPhone(new Phone(TypePhone.RESIDENCIAL,"940124854"));
		person.addPhone(new Phone(TypePhone.COMERCIAL,"940821554"));
		person.setAddresses(Arrays.asList(ad1, ad2));
		
		new PersonDAO().save(person);
		
		System.out.println(new PersonDAO().findById(person.getId()));
	}


	private static void deleteOnCascade() {
//		new PersonDAO().delete(15L);
		
//		new PhoneDAO().delete(11L);
		
		PhoneDAO dao = new PhoneDAO();
		Phone phone = dao.findById(11L);
		
		System.out.println(phone.toString());
		
		phone.getPerson().delPhone(phone);
		dao.delete(phone);
	}
	
	private static void updatePhoneByPerson() {
		
		Person person = new PersonDAO().findById(19L);
		
		for(Phone phone : person.getPhones()) {
			System.out.println("1- " + phone.toString());
			
			if(TypePhone.COMERCIAL == phone.getType()) {
				phone.setType(TypePhone.RESIDENCIAL);
			}
		}
		
		new PersonDAO().update(person);
		
		for(Phone phone : person.getPhones()) {
			System.out.println("2- " + phone.toString());
		}
		
	}


	private static void updatePhone() {
		Person person = new PersonDAO().findById(15L);
		
		PhoneDAO dao = new PhoneDAO();
		Phone phone = dao.findById(10l);
		phone.setPerson(person);
		
		dao.update(phone);
		
		phone = dao.findById(phone.getId());
		
		System.out.println(phone.toString());
		
	}


	private static void insertPhoneByPerson() {
		
		Phone ph1 = new Phone(TypePhone.CELULAR, "432332344");
		Phone ph2 = new Phone(TypePhone.COMERCIAL,"1112322353");
		
		Person person = new Person();
		person.setFirstName("Maria");
		person.setLastName("Fatima");
		person.setAge(20);
		person.setDocument(new Document("111.654.444-32",124356743));
		
//		ph1.setPerson(person);
//		ph2.setPerson(person);
//		
//		person.setPhones(Arrays.asList(ph1,ph2));
		
		person.addPhone(ph1);
		person.addPhone(ph2);
		
		new PersonDAO().save(person);
	}


	private static void insertPhone() {
		
		Person person = new Person();
		person.setFirstName("Vinicius");
		person.setLastName("Carvalho");
		person.setAge(29);
		person.setDocument(new Document("123.426.789-49",345876234));
		
		Phone phone = new Phone(TypePhone.CELULAR,"920273845");
		phone.setPerson(person);
		
		PhoneDAO dao = new PhoneDAO();
		dao.save(phone);
		
		phone = dao.findById(phone.getId());
		
		System.out.println(phone.toString());
	}


	private static void findPersonByCpf() {
		Person p = new PersonDAO().findByCpf("475.469.818-59");
		
		System.out.println(p.toString());
	}


	private static void updateDocument() {
		Document doc = new DocumentDAO().findById(1L);
		
		System.out.println(doc.toString());
		
		doc.setCpf("475.469.818-59");
		
		new DocumentDAO().update(doc);
		
		System.out.println(new DocumentDAO().findById(1L).toString());
		
	}


	private static void insertDocument() {
		Person p1 = new Person();
		p1.setFirstName("Aline");
		p1.setLastName("de Souza");
		p1.setAge(24);
		p1.setDocument(new Document("123.456.789-99",123456789));
		
		new PersonDAO().save(p1);
		
		System.out.println(p1.toString());
	}


	//***	PERSON  ***//

	private static void deletePerson() {
		new PersonDAO().delete(2L);
		
	}

	private static void updatePerson() {
		Person p1 = new PersonDAO().findById(1L);
		System.out.println(p1.toString());
		
		p1.setLastName("de Souza");
		
		new PersonDAO().update(p1);
		
		Person p2 = new PersonDAO().findById(1L);
		System.out.println(p2.toString());
		
	}

	private static void findByFullName() {
		Person person = new PersonDAO().findByFullName("Vinicius", "de Carvalho");
		System.out.println(person.toString());
	}

	private static void findByAge() {
		List<Person> persons = new PersonDAO().findAgeIsBetween(10, 19);
		
		for(Person p : persons) {
			System.out.println(p.toString());
		}
	}

	private static void findBylastName() {
		List<Person> persons = new PersonDAO().findByLastName("Danielle");
		
		for(Person p : persons) {
			System.out.println(p.toString());
		}
	}

	private static void countPersons() {
		long total = new PersonDAO().count();
		
		System.out.println("Total of Persons: " + total);
		
	}

	private static void findAllPersons() {
		List<Person> persons = new PersonDAO().findAll();
		
		for(Person p : persons) {
			System.out.println(p.toString());
		}
		
	}

	private static void findPersonById() {
		Person p1 = new PersonDAO().findById(1l);
		Person p2 = new PersonDAO().findById(5l);
		
		System.out.println(p1.toString());
		System.out.println(p1.toString());
	}

	private static void insertPerson() {
		Person p1 = new Person();
		p1.setFirstName("Fabiana");
		p1.setLastName("de Carvalho");
		p1.setAge(19);
		
		new PersonDAO().save(p1);
		
		System.out.println(p1.toString());
		
		Person p2 = new Person();
		p2.setFirstName("Gilberto");
		p2.setLastName("Figueira");
		p2.setAge(36);
		
		new PersonDAO().save(p2);
		System.out.println(p2.toString());
		
		Person p3 = new Person();
		p3.setFirstName("Yasmin");
		p3.setLastName("Danielle");
		p3.setAge(19);
		
		new PersonDAO().save(p3);
		System.out.println(p3.toString());
	}

}
