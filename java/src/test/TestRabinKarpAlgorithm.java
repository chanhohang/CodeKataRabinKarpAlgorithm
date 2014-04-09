import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestRabinKarpAlgorithm {

	@Parameters
	public static Collection<Object[]> init() {

		return Arrays
				.asList(new Object[][] {
						//@formatter:off
						{ "", "" },
						{"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab","b" }, 
						{ "aaaaaaaaaaaaaaaaaaab", "ab" },
						{ "aaaaaaaaaaaaaaaaaaab", "aaa" },
						{ "aaaaaaaaaaaaaaaaaa ab", "ab" },
						{ "aaaaaaaaaaaaaa\n\naaaaab", "a\n\na" },
						{ "\uab97\uab98\uab99", "\uab98\uab99" },
						{ "\uab97\uab98\uab99", "abc" },
						{ "\uab97\uab98\uab99", "abcsdfaw35" }, 
						//@formatter:on

				});
	}

	private String inputStr;
	private String pattern;

	public TestRabinKarpAlgorithm(String inputStr, String pattern) {
		this.inputStr = inputStr;
		this.pattern = pattern;
	}

	@Test
	public void testBasicHashStringSearch() {
		RabinKarpAlgorithm algo = new RabinKarpAlgorithm();
		int expectedResult = inputStr.indexOf(pattern);
		Assert.assertEquals(expectedResult, algo.search(inputStr, pattern));

	}

	@Test
	public void testRollingHashStringSearch() {
		RabinKarpAlgorithm algo = new RabinKarpAlgorithm(new RollingHash());
		int expectedResult = inputStr.indexOf(pattern);
		Assert.assertEquals(expectedResult, algo.search(inputStr, pattern));

	}

}
