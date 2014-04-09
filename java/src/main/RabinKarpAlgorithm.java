public class RabinKarpAlgorithm {

	private HashHelper hashFunction;
	
	public RabinKarpAlgorithm()
	{
		this.hashFunction = new HashHelper() {
			
			@Override
			public int hash(int currentHash, String input, int pos, int length) {
				return input.substring(pos, pos+length).hashCode();
			}
		};
	}
	
	public RabinKarpAlgorithm(HashHelper hashFunction)
	{
		this.hashFunction = hashFunction;
	}
	
	public int search(String text, String pattern) {
		if (text == null || pattern == null)
		{
			return -1;
		}
		int patternLength = pattern.length();
		int textLength = text.length();

		if (patternLength <= textLength) {
			int patternHash = hash(0, pattern, 0, patternLength);
			int textHash = hash(0, text, 0, patternLength);
			for (int i = 0; i < textLength - patternLength + 1; i++) {
				if (patternHash == textHash) {
					if (equals(text, i, patternLength, pattern)) {
						return i;
					}
				}
				if (i + patternLength + 1 > textLength) {
					break;
				}
				textHash = hash(textHash, text, i + 1, patternLength);
			}
		}
		return -1;
	}

	private boolean equals(String input, int pos, int len, String pattern) {
		return input.substring(pos, pos + len).equals(pattern);
	}

	private int hash(int currentHash, String input, int pos, int len) {
		return hashFunction.hash(currentHash, input, pos, len);
	}
}
