package com.fortech.servicetest;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.fortech.model.MarketRule;
import com.fortech.model.MarketRulePK;
import com.fortech.service.MarketRuleService;

@RunWith(MockitoJUnitRunner.class)
public class MarketRuleServiceTest {

	@InjectMocks
	private MarketRuleService marketRuleService;
	
	@Mock
	private EntityManager entityManager;
	
	@Test
	public void insertInDatabase_addObjectInDatabase_AddedSuccesfully() {

		MarketRule marketRuleCreatedForAdd = createTestMarketRuleForInsertInDatabase();
		assertNotNull(marketRuleService);
		assertNotNull(entityManager);
		marketRuleService.insertInDatabase(marketRuleCreatedForAdd);
		Mockito.verify(entityManager, Mockito.times(1)).persist(marketRuleCreatedForAdd);

	}

	@Test
	public void updateInDatabase_updateObjectInDatabase_UpdatedSuccesfully() {

		MarketRule marketRuleCreatedForUpdate = createTestMarketRuleForUpdateInDatabase();
		assertNotNull(marketRuleService);
		assertNotNull(entityManager);
		marketRuleService.updateInDatabase(marketRuleCreatedForUpdate);
		Mockito.verify(entityManager,Mockito.times(1)).merge(marketRuleCreatedForUpdate);	
	}

	@Test
	public void deleteFromDatabase_deleteObjectFromDatabase_DeletedSuccesfully() {

		MarketRule marketRuleCreatedForDelete = createTestMarketRuleForInsertInDatabase();
		MarketRulePK marketRulePK = createMarketRulePKForTest();
		assertNotNull(marketRuleService);
		assertNotNull(entityManager);
		marketRuleService.deleteFromDatabase(marketRulePK);
		Mockito.verify(entityManager,Mockito.times(1)).find(MarketRule.class, marketRuleCreatedForDelete.getId());

	}

	@Test
	public void findById_findObjectInDatabase_FoundSuccesfully() {

		MarketRule marketRuleCreatedForFinding = createTestMarketRuleForInsertInDatabase();
		MarketRulePK marketRulePK = createMarketRulePKForTest();
		assertNotNull(marketRuleService);
		assertNotNull(entityManager);
		Mockito.when(entityManager.find(MarketRule.class, marketRulePK)).thenReturn(marketRuleCreatedForFinding);
		
		assertSame(marketRuleCreatedForFinding, marketRuleService.findById(marketRulePK));
		assertNull(marketRuleService.findById(createFakeMarketRulePKForTest()));

	}
	
	private MarketRule createTestMarketRuleForInsertInDatabase() {
		MarketRule marketRule = new MarketRule();
		marketRule.setActive((short)1);
		marketRule.setRule("Personal Use");
		marketRule.setId(createMarketRulePKForTest());
		return marketRule;
	}

	private MarketRulePK createMarketRulePKForTest() {
		MarketRulePK marketRulePK = new MarketRulePK();
		marketRulePK.setBranch(121);
		marketRulePK.setCountryNumber("CJ-28-TUD");
		marketRulePK.setStockCategory((short)0);
		return marketRulePK;
	}
	
	private MarketRulePK createFakeMarketRulePKForTest() {
		MarketRulePK marketRulePK = new MarketRulePK();
		marketRulePK.setBranch(0);
		marketRulePK.setCountryNumber("XXXXX");
		marketRulePK.setStockCategory((short)0);
		return marketRulePK;
	}
	
	private MarketRule createTestMarketRuleForUpdateInDatabase() {
		MarketRule marketRule = new MarketRule();
		marketRule.setActive((short)1);
		marketRule.setRule("Personal Use");
		marketRule.setId(createMarketRulePKForTest());
		return marketRule;
	}


}
