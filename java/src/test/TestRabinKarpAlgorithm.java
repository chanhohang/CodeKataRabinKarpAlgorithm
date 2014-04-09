import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
		
		int prime = 3;
		HashHelper rolling =(int currentHash, String input, int pos, int length) -> {
			
			if (currentHash == 0)
			{
				for (int i = pos; i< pos+length;i++)
				{
					currentHash += input.charAt(i) * Math.pow(prime, i -pos);
				}
			}
			else
			{
				currentHash -= input.charAt(pos-1);
				currentHash /= prime;
				currentHash += input.charAt(pos+length-1) * Math.pow(prime, length-1);
			}
			
			return currentHash;
		};
		
		RabinKarpAlgorithm algo = new RabinKarpAlgorithm(rolling);
		int expectedResult = inputStr.indexOf(pattern);
		Assert.assertEquals(expectedResult, algo.search(inputStr, pattern));

	}

}
