package com.br.DAO;

import static org.junit.Assert.*;

import org.junit.Test;

public class DBConnectionTest {

	@Test
	public final void testDBConnection() {
		try {
			new DBConnection();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public final void testGetConnection() {
		DBConnection db = new DBConnection();
		try {
			db.getConnection();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public final void testStatusConection() {
		DBConnection db = new DBConnection();
		assertTrue(db.statusConection().equals("Conectado."));
	}

	@Test
	public final void testFecharConexao() {
		DBConnection db = new DBConnection();
		assertTrue(db.FecharConexao());
	}
	
	@Test
	public final void testCanExecuteCmd() {
		DBConnection db = new DBConnection();
		assertTrue(db.canExecuteCmd());
	}

}
