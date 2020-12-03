package de.hfu.Intergrationstest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

import static org.easymock.EasyMock.*;
public class ResidentEasyMockTest {
	
	@Test
	public void testUniqueResident() {
		List<Resident> liste = new ArrayList<Resident>();
		liste.add(new Resident("Kevin", "Schneider", "Musterstrasse", "Furtwangen", new Date(2000,05,31)));
		liste.add(new Resident("Max", "Mustermann", "Musterstrasse", "Musterstadt", new Date(2001,01,01)));
		liste.add(new Resident("Test", "Testermann", "Testerstrasse", "Testerstadt", new Date(2012,10,10)));
		ResidentRepository y = createMock(ResidentRepository.class);
		expect(y.getResidents()).andReturn(liste);
		expect(y.getResidents()).andReturn(liste);
		expect(y.getResidents()).andReturn(liste);
		expect(y.getResidents()).andReturn(liste);


		replay(y);
		BaseResidentService x = new BaseResidentService();
		x.setResidentRepository(y);
		
		Resident Kevin = new Resident("Kevin", "Schneider", "Musterstrasse", "Furtwangen", new Date(2000,05,31));
		Resident Max = new Resident("Max", "Mustermann", "Musterstrasse", "Musterstadt", new Date(2001,01,01));
		Resident Test = new Resident("Test", "Testermann", "Testerstrasse", "Testerstadt", new Date(2012,10,10));
		Resident error = new Resident("error", "falsch","Falschestrasse", "Falschestadt", new Date(2222,12,24));
		Resident wildcard = new Resident("kev*", "Sch*", "Muster*", "Must*", new Date(1999,05,31));
		try {
			assertThat(x.getUniqueResident(Kevin), equalTo(Kevin));
		} catch (ResidentServiceException e) {
		}

		try {
			assertThat(x.getUniqueResident(Max), equalTo(Max));
		} catch (ResidentServiceException e) {
		}
		
		try {
			assertThat(x.getUniqueResident(Test), equalTo(Test));
		} catch (ResidentServiceException e) {
		}
		
		
		//ERROR
		try {
			x.getUniqueResident(error);
			Assert.fail();
		} catch(ResidentServiceException e) {
			assertThat(e.getMessage(), equalTo("Suchanfrage lieferte kein eindeutiges Ergebnis!"));
		} 
		//Wildcard Test
		try {
			x.getUniqueResident(wildcard);
			Assert.fail();
		} catch(ResidentServiceException e) {
			assertThat(e.getMessage(), equalTo("Wildcards (*) sind nicht erlaubt!"));
		} 
		verify(y);
	}
	

	@Test
	public void testGetFilteredResidentsList() {
		List<Resident> liste = new ArrayList<Resident>();
		liste.add(new Resident("Kevin", "Schneider", "Musterstrasse", "Furtwangen", new Date(2000,05,31)));
		liste.add(new Resident("Max", "Mustermann", "Musterstrasse", "Musterstadt", new Date(2001,01,01)));
		liste.add(new Resident("Test", "Testermann", "Testerstrasse", "Testerstadt", new Date(2012,10,10)));
		ResidentRepository y = createMock(ResidentRepository.class);
		expect(y.getResidents()).andReturn(liste);
		expect(y.getResidents()).andReturn(liste);
		expect(y.getResidents()).andReturn(liste);


		replay(y);
		BaseResidentService x = new BaseResidentService();
		x.setResidentRepository(y);
		
		
		Resident Kevin = new Resident("Kevin", "Schneider", "Musterstrasse", "Furtwangen", new Date(2000,05,31));
		Resident Max = new Resident("Max", "Mustermann", "Musterstrasse", "Musterstadt", new Date(2001,01,01));
		Resident Test = new Resident("Test", "Testermann", "Testerstrasse", "Testerstadt", new Date(2012,10,10));
		
		
		

		//Test
		liste.clear();
		liste.add(Kevin);
		assertThat(x.getFilteredResidentsList(Kevin),equalTo(liste));
		//Test 2
		liste.clear();
		liste.add(Max);
		assertThat(x.getFilteredResidentsList(Max),equalTo(liste));

		//Test 3
		liste.clear();
		liste.add(Test);
		assertThat(x.getFilteredResidentsList(Test),equalTo(liste));
		verify(y);

	}
}
