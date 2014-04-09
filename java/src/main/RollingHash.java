
public class RollingHash implements HashHelper {

	private int prime = 3;
	/**
	 * set Prime
	 * foreach character
	 *    charat(i) * Math.power(prime, i- startIndex) 
	 * Rolling
	 *    currentHash - i-len*prime + i+len*Math.power(prime, i-startIndex)
	 */
	@Override
	public int hash(int currentHash, String input, int pos, int length) {
		
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
	}

}
