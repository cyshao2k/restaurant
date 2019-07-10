package cl.transbank.tech.restaurant.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import cl.transbank.tech.restaurant.dao.TableDao;
import cl.transbank.tech.restaurant.entity.TableEntity;
import cl.transbank.tech.restaurant.exeption.ItemNotFoundException;
import cl.transbank.tech.restaurant.to.Table;

@RunWith(MockitoJUnitRunner.class)
public class TableManagerImplTest {
	
	@Mock
	private TableDao dao;
	
	@InjectMocks
	private TableManagerImpl service;
	
	@Test
	public void listTablesTest() {
		TableEntity tableEntity = new TableEntity();
		tableEntity.setId(1);
		tableEntity.setStatus("available");
		
		List<TableEntity> tableEntities = new ArrayList<>();
		tableEntities.add(tableEntity);
		
		Mockito.when(dao.findAll()).thenReturn(tableEntities);
		
		List<Table> tables = service.listTables();
		
		assert(!tables.isEmpty());
	}
	
	@Test
	public void getTableTest() throws ItemNotFoundException {
		TableEntity tableEntity = new TableEntity();
		tableEntity.setId(1);
		tableEntity.setStatus("available");
		
		Optional<TableEntity> optional = Optional.of(tableEntity);
		
		Mockito.when(dao.findById(Mockito.anyInt())).thenReturn(optional);
		
		Table table = service.getTable(1);
		
		assert(table != null);
	}

	@Test (expected = ItemNotFoundException.class)
	public void getTableThrowExceptionTest() throws ItemNotFoundException {
		Optional<TableEntity> optional = Optional.empty();
		
		Mockito.when(dao.findById(Mockito.anyInt())).thenReturn(optional);
		
		service.getTable(1);
	}
	
	@Test
	public void setStatus() throws ItemNotFoundException {
		TableEntity tableEntity = new TableEntity();
		tableEntity.setId(1);
		tableEntity.setStatus("available");
		
		Optional<TableEntity> optional = Optional.of(tableEntity);
		
		Mockito.when(dao.findById(Mockito.anyInt())).thenReturn(optional);
		
		Table status = new Table();
		status.setId(1);
		status.setStatus("available");
		
		Mockito.when(dao.save(Mockito.any())).thenReturn(tableEntity);
		
		service.setStatus(status);
	}

}
