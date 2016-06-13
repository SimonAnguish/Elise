class Word {
	private HashMap<String,Integer> prefix = new HashMap();
	private HashMap<String,Integer> suffix = new HashMap();
	private String word;
	
	public Word(String word) {
		this.word = word;
	}
	
	public void addPrefix(String w1, String w2) {
		String p = w1 + " " + w2;
		prefix.put(p, 1);
	}
	
	public void addSuffix(String w1, String w2) {
		String s = w1 + " " + w2;
		suffix.put(s, 1);
	}
	
	public String[] getPrefix() {
		Random r = new Random();
		List<String> keys = new ArrayList<String>(prefix.keySet());
		String randomKey = keys.get(r.nextInt(keys.size()));
		
		return randomKey.split(" ");
	}

	public String[] getSuffix() {
		String[] empty_array = {"cat","dog"};
		Random r = new Random();
		List<String> keys = new ArrayList<String>(suffix.keySet());
		String randomKey = keys.get(r.nextInt(keys.size()));
		return randomKey.split(" ");
	}
	
	public String toString() {
		return word;
	}
}