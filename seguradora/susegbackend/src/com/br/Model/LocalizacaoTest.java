package com.br.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LocalizacaoTest {
	
	private Localizacao local;
	
	@Before
	public void beforeTest(){
		local = new Localizacao();
		local.setEndereco("Avenida Paulista", 1234, "89110-000");
		local.setLocal("Blumenau", "SC", "Brasil");
	}

	@Test
	public void testInserirNovaLocalizacao() {
		int localId = local.save();
		assertTrue("N‹o salvou o local", localId > 0);
	}

}
