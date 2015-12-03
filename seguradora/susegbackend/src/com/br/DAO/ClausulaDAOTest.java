package com.br.DAO;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClausulaDAOTest {

	@Test
	public final void testLoadFromDBInt() {
		assertTrue(ClausulaDAO.loadFromDB(1).getTipo().equals("Carro reserva 7"));
	}

	@Test
	public final void testLoadFromDBString() {
		assertTrue(ClausulaDAO.loadFromDB("Carro reserva 7").getCodigo() == 1);
	}

}
