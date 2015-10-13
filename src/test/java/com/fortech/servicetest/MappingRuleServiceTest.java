package com.fortech.servicetest;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.fortech.model.MappingRule;
import com.fortech.service.MappingRuleService;

public class MappingRuleServiceTest {


	private MappingRuleService mappingRuleService;
	private EntityManager entityManager;

	@Before
	public void init(){
		mappingRuleService = new MappingRuleService();
		entityManager = Mockito.mock(EntityManager.class);
		mappingRuleService.setEntityManager(entityManager);
	}
	
	@Test
	public void insertInDatabase_addObjectInDatabase_AddedSuccesfully() {

		MappingRule mappingRuleCreatedForAdd = createTestMappingRuleForInsertInDatabase();
		assertNotNull(mappingRuleService);
		assertNotNull(mappingRuleService.getEntityManager());
		mappingRuleService.insertInDatabase(mappingRuleCreatedForAdd);
		Mockito.verify(mappingRuleService.getEntityManager(), Mockito.times(1)).persist(mappingRuleCreatedForAdd);

	}
	
	
	@Test
	public void updateInDatabase_updateObjectInDatabase_UpdatedSuccesfully() {

		MappingRule mappingRuleCreatedForUpdate = createTestMappingRuleForUpdateInDatabase();
		assertNotNull(mappingRuleService);
		assertNotNull(mappingRuleService.getEntityManager());
		mappingRuleService.updateInDatabase(mappingRuleCreatedForUpdate);
		Mockito.verify(mappingRuleService.getEntityManager(),Mockito.times(1)).merge(mappingRuleCreatedForUpdate);	
	}
	

	@Test
	public void deleteFromDatabase_deleteObjectFromDatabase_DeletedSuccesfully() {

		MappingRule mappingRuleCreatedForDelete = createTestMappingRuleForInsertInDatabase();
		assertNotNull(mappingRuleService);
		assertNotNull(mappingRuleService.getEntityManager());
		mappingRuleService.deleteFromDatabase(mappingRuleCreatedForDelete.getId());
		Mockito.verify(mappingRuleService.getEntityManager(),Mockito.times(1)).find(MappingRule.class, mappingRuleCreatedForDelete.getId());

	}

	@Test
	public void findById_findObjectInDatabase_FoundSuccesfully() {

		MappingRule mappingRuleCreatedForFinding = createTestMappingRuleForInsertInDatabase();
		assertNotNull(mappingRuleService);
		assertNotNull(mappingRuleService.getEntityManager());
		Mockito.when(entityManager.find(MappingRule.class, 12)).thenReturn(mappingRuleCreatedForFinding);
		
		MappingRuleService mockedMappingRuleService = Mockito.mock(MappingRuleService.class);
		Mockito.when(mockedMappingRuleService.findById(12)).thenCallRealMethod();
		Mockito.when(mockedMappingRuleService.getEntityManager()).thenReturn(entityManager);
		
		assertSame(mappingRuleCreatedForFinding, mappingRuleService.findById(12));
		assertNull(mockedMappingRuleService.findById(21));

	}

	
	private MappingRule createTestMappingRuleForInsertInDatabase() {
		MappingRule map = new MappingRule();
		map.setId(1);
		map.setSourceValue("3333");
		map.setTargetValue("4000");
		map.setVehicleAttribute("Running");
		return map;
	}
	
	private MappingRule createTestMappingRuleForUpdateInDatabase() {
		MappingRule map = new MappingRule();
		map.setId(1);
		map.setSourceValue("1111");
		map.setTargetValue("2200");
		map.setVehicleAttribute("Running");
		return map;
	}

}
