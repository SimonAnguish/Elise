class Vocabulary {
	public HashMap<String, Word> vl = new HashMap();
	
	public Vocabulary() {}
	
	public void addWord(Word word) {
		vl.put(word.toString(), word);
	}
}