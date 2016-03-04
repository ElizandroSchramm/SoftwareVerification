package com.br.Model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class SeguradoTest {

	@Test
	public void testInserirNovoSegurado() {
		Segurado segurado = new Segurado();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			segurado.setDados("Elizandro", "12345678920", "M", new Date(sdf.parse("08/09/1988").getTime()), 1, "", "");
			segurado.setContato("4733334455");
			int id = segurado.save();
			assertTrue("Erro ao criar segurado.", id > 0);
		} catch (ParseException e) {
			e.printStackTrace();
			fail("Erro ao criar segurado.");
		}
	}

}
