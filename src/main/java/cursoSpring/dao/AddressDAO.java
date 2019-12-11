package cursoSpring.dao;

import java.util.List;

import cursoSpring.entity.Address;

public class AddressDAO extends GenericDAO<Address>{

	public AddressDAO() {
		super(Address.class);
	}
	
	public List<Address> findByCity(String city){
		String jpql = "from Address a where a.city like ?";
		return find(jpql, city);
	}

}
