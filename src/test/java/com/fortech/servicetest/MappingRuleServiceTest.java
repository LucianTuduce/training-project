package com.fortech.servicetest;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.fortech.model.MappingRule;
import com.fortech.service.MappingRuleService;

@RunWith(MockitoJUnitRunner.class)
public class MappingRuleServiceTest {

	@InjectMocks
	private MappingRuleService mappingRuleService;
	@Mock
	private EntityManager entityManager;

	@Test
	public void insertInDatabase_addObjectInDatabase_AddedSuccesfully() {

		MappingRule mappingRuleCreatedForAdd = createTestMappingRuleForInsertInDatabase();
		assertNotNull(mappingRuleService);
		assertNotNull(entityManager);
		mappingRuleService.insertInDatabase(mappingRuleCreatedForAdd);
		Mockito.verify(entityManager, Mockito.times(1)).persist(mappingRuleCreatedForAdd);

	}
	
	
	@Test
	public void updateInDatabase_updateObjectInDatabase_UpdatedSuccesfully() {

		MappingRule mappingRuleCreatedForUpdate = createTestMappingRuleForUpdateInDatabase();
		assertNotNull(mappingRuleService);
		assertNotNull(entityManager);
		mappingRuleService.updateInDatabase(mappingRuleCreatedForUpdate);
		Mockito.verify(entityManager,Mockito.times(1)).merge(mappingRuleCreatedForUpdate);	
	}
	

	@Test
	public void deleteFromDatabase_deleteObjectFromDatabase_DeletedSuccesfully() {

		MappingRule mappingRuleCreatedForDelete = createTestMappingRuleForInsertInDatabase();
		assertNotNull(mappingRuleService);
		assertNotNull(entityManager);
		mappingRuleService.deleteFromDatabase(mappingRuleCreatedForDelete.getId());
		Mockito.verify(entityManager,Mockito.times(1)).find(MappingRule.class, mappingRuleCreatedForDelete.getId());

	}

	@Test
	public void findById_findObjectInDatabase_FoundSuccesfully() {

		MappingRule mappingRuleCreatedForFinding = createTestMappingRuleForInsertInDatabase();
		assertNotNull(mappingRuleService);
		assertNotNull(entityManager);
		Mockito.when(entityManager.find(MappingRule.class, 12)).thenReturn(mappingRuleCreatedForFinding);
		
		assertSame(mappingRuleCreatedForFinding, mappingRuleService.findById(12));
		assertNull(mappingRuleService.findById(21));

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
