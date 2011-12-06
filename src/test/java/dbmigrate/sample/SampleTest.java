package dbmigrate.sample;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SampleTest {
	private int a;

	@Before
	public void initialize() {
		System.out.println("Before");
		a = 0;
	}

	@Test
	public void incrementOnce() {
		System.out.println("increment");
		++a;
		this.assertEquals(a, 1);
	}

	@Test
	public void incrementTwice() {
		System.out.println("increment twice");
		++a;
		++a;
		this.assertEquals(a, 2); // a nie 3, ani nie 10!
	}

	@After
	public void clean() {
		System.out.println("After");
		a = 10;
	}
}
