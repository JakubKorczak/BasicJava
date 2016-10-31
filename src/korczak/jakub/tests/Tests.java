package korczak.jakub.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import korczak.jakub.main.Main;

public class Tests
{
	@Test
	public void testScientificNotation() throws IOException
	{	
		assertTrue(Main.checkingIfInScientificNotation("2.4"));
		assertTrue(Main.checkingIfInScientificNotation("-12.45"));
		assertTrue(Main.checkingIfInScientificNotation("10E12"));
		assertTrue(Main.checkingIfInScientificNotation("-5.45E9"));
		assertTrue(Main.checkingIfInScientificNotation("8E-3"));
		assertTrue(Main.checkingIfInScientificNotation("23.34e10"));
		assertTrue(Main.checkingIfInScientificNotation("24.3e-5"));
		
		assertFalse(Main.checkingIfInScientificNotation("aaaa"));
		assertFalse(Main.checkingIfInScientificNotation("E345"));
		assertFalse(Main.checkingIfInScientificNotation(""));
	}

	@Test
	public void testStandardDeviation() throws IOException
	{

		for (int i = 0; i < 100; i++)
		{
			assertTrue(Main.standardDeviation());
		}

	}

}
