package de.hfu.residents.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import de.hfu.residents.domain.Resident;

public class ResidentRepositoryStub implements ResidentRepository {

	@Override
	public List<Resident> getResidents() {
		List<Resident> liste = new ArrayList<Resident>();
		liste.add(new Resident("Kevin", "Schneider", "Musterstrasse", "Furtwangen", new Date(2000,05,31)));
		liste.add(new Resident("Max", "Mustermann", "Musterstrasse", "Musterstadt", new Date(2001,01,01)));
		liste.add(new Resident("Test", "Testermann", "Testerstrasse", "Testerstadt", new Date(2012,10,10)));
		return liste;
	}

}
