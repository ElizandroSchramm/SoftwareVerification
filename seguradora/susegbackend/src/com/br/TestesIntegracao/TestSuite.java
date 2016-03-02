package com.br.TestesIntegracao;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.br.Controle.CotacaoControleTest;

import junit.framework.JUnit4TestAdapter;

@RunWith(Suite.class)
@SuiteClasses({CotacaoTest.class, 
	           CotacaoControleTest.class, 
	           })

public class TestSuite {
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TestSuite.class);
	}
}