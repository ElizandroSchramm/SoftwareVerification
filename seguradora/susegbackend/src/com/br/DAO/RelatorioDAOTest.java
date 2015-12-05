package com.br.DAO;

import static org.junit.Assert.*;

import org.junit.Test;

public class RelatorioDAOTest {

	@Test
	public final void testLoadApolicesVencendo() {
		System.out.println(RelatorioDAO.loadApolicesVencendo(367));
	}

}
