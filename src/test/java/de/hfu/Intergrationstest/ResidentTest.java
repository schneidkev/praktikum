package de.hfu.Intergrationstest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.internal.MocksControl.MockType;
import org.junit.Assert;
import org.junit.Test;

import de.hfu.Queue;
import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepositoryStub;
import de.hfu.residents.service.ResidentService;
import de.hfu.residents.service.ResidentServiceException;
import de.hfu.residents.service.BaseResidentService;


public class ResidentTest {
	@Test
	public void testGetFilteredResidentsList() {
		Resident Kevin = new Resident("Kevin", "Schneider", "Musterstrasse", "Furtwangen", new Date(2000,05,31));
		Resident Max = new Resident("Max", "Mustermann", "Musterstrasse", "Musterstadt", new Date(2001,01,01));
		Resident Test = new Resident("Test", "Testermann", "Testerstrasse", "Testerstadt", new Date(2012,10,10));
		
		
		
		BaseResidentService y = new BaseResidentService();
		y.setResidentRepository(new ResidentRepositoryStub());
		//Test
		List<Resident> liste = new ArrayList<Resident>();
		liste.add(Kevin);
		Assert.assertArrayEquals(liste.toArray(), y.getFilteredResidentsList(Kevin).toArray());
		//Test 2
		liste.clear();
		liste.add(Max);
		Assert.assertArrayEquals(liste.toArray(), y.getFilteredResidentsList(Max).toArray());
		//Test 3
		liste.clear();
		liste.add(Test);
		Assert.assertArrayEquals(liste.toArray(), y.getFilteredResidentsList(Test).toArray());
	}
	
	@Test
	public void testGetUniqueResident() {
		Resident Kevin = new Resident("Kevin", "Schneider", "Musterstrasse", "Furtwangen", new Date(2000,05,31));
		Resident Max = new Resident("Max", "Mustermann", "Musterstrasse", "Musterstadt", new Date(2001,01,01));
		Resident Test = new Resident("Test", "Testermann", "Testerstrasse", "Testerstadt", new Date(2012,10,10));
		Resident error = new Resident("error", "falsch","Falschestrasse", "Falschestadt", new Date(2222,12,24));
		Resident wildcard = new Resident("kev*", "Sch*", "Muster*", "Must*", new Date(1999,05,31));
		BaseResidentService y = new BaseResidentService();
		y.setResidentRepository(new ResidentRepositoryStub());
		//Test
		try {
			Assert.assertEquals(Kevin, y.getUniqueResident(Kevin));
		} catch (ResidentServiceException e) {
		}
		//Test 2
		try {
			Assert.assertEquals(Max, y.getUniqueResident(Max));
		} catch (ResidentServiceException e) {
		}
		//Test 3
		try {
			Assert.assertEquals(Test, y.getUniqueResident(Test));
		} catch (ResidentServiceException e) {
		}
		//kein Ergebnis Test
		try {
			y.getUniqueResident(error);
			Assert.fail();
		} catch(ResidentServiceException e) {
			Assert.assertEquals(e.getMessage(), "Suchanfrage lieferte kein eindeutiges Ergebnis!");
		} 
		//Wildcard Test
		try {
			y.getUniqueResident(wildcard);
			Assert.fail();
		} catch(ResidentServiceException e) {
			Assert.assertEquals(e.getMessage(), "Wildcards (*) sind nicht erlaubt!");
		} 
		
	}
	@Test
	public void testWildcard() {
		BaseResidentService y = new BaseResidentService();
		y.setResidentRepository(new ResidentRepositoryStub());
		Resident wcard = new Resident("Kevin", "Sch*", "Musterstrasse", "Furtwangen", new Date(2000,05,31));
		Resident wcard2 = new Resident("Max", "Mustermann", "Muster*", "Musterstadt", new Date(2001,01,01));
		try {
			y.getUniqueResident(wcard);
			Assert.fail();
		} catch(ResidentServiceException e) {
			Assert.assertEquals(e.getMessage(), "Wildcards (*) sind nicht erlaubt!");
		} 
		try {
			y.getUniqueResident(wcard2);
			Assert.fail();
		} catch(ResidentServiceException e) {
			Assert.assertEquals(e.getMessage(), "Wildcards (*) sind nicht erlaubt!");
		} 

	}
	@Test
	public void testWildcardNull() {
		BaseResidentService y = new BaseResidentService();
		y.setResidentRepository(new ResidentRepositoryStub());
		Resident wcard = new Resident(null, "Sch*", "Musterstrasse", "Furtwangen", new Date(2000,05,31));
		Resident wcard2 = new Resident("Max", null, "Muster*", "Musterstadt", new Date(2001,01,01));
		try {
			y.getUniqueResident(wcard);
			Assert.fail();
		} catch(ResidentServiceException e) {
			Assert.assertEquals(e.getMessage(), "Wildcards (*) sind nicht erlaubt!");
		} 
		try {
			y.getUniqueResident(wcard2);
			Assert.fail();
		} catch(ResidentServiceException e) {
			Assert.assertEquals(e.getMessage(), "Wildcards (*) sind nicht erlaubt!");
		} 

	}
	
	@Test 
	public void testcompileFilterPattern() {
		BaseResidentService y = new BaseResidentService();
		y.setResidentRepository(new ResidentRepositoryStub());
		Resident wcard = new Resident(null, "Sch*", "Musterstrasse", "Furtwangen", new Date(2000,05,31));
		Resident wcard2 = new Resident("Max", "", "Muster*", "Musterstadt", new Date(2001,01,01));
		y.getFilteredResidentsList(wcard);
		y.getFilteredResidentsList(wcard2);
	}

}
