class Language {
	Vocabulary v;
//	private String vocabulary_path = "./local-data/vocab.csv";
//	private File f = new File(vocabulary_path);
//	
//	private HashMap<String,Word> vl = new HashMap();
//	
	public Language() {
		v = new Vocabulary();
//		// Read the file
//		try {
//			Scanner scanner = new Scanner(f);
//			while (scanner.hasNext()) {
//				String next = scanner.nextLine();
//				vl.put(next.split(",")[0], next.split(",")[1]);
//			}
//			
//		} catch (FileNotFoundException e) {
//			try {
//				vf.createNewFile();
//			} catch(IOException e1) {
//				System.out.printf("IO Exception\n");
//			}
//		}
	}
	
	public void read(String text) {
		String[] text_split = text.split(" ");
		String[] word_list = new String[text_split.length + 4];
		word_list[0] = "";
		word_list[1] = "";
		
		int i = 2;
		for (String word: text_split) {
			word_list[i] = word;
			i++;
		}
		
		Random r = new Random();
		String randomKey = text_split[r.nextInt(text_split.length)];
		
		word_list[i] = randomKey;
		i++;
		word_list[i] = randomKey;
		
		Word w;
		
		for (i=2;i<word_list.length-2;i++) {
			w = new Word(word_list[i]);
			w.addPrefix(word_list[i-2], word_list[i-1]);
			w.addSuffix(word_list[i+1], word_list[i+2]);
			v.addWord(w);
		}
	}
	
	public void generateNonsense() {
		Random r = new Random();
		List<String> keys = new ArrayList<String>(v.vl.keySet());
		String randomKey = keys.get(r.nextInt(keys.size()));
		System.out.printf("%s ", randomKey);
		
		String next_word;
		
		for (int i=0;i<25;i++) {
			while (randomKey == null) {
				randomKey = keys.get(r.nextInt(keys.size()));
			}
			next_word = v.vl.get(randomKey).getSuffix()[0];
			System.out.printf("%s ", next_word);
			randomKey = next_word;
		}
		
	}
//	
//	public void add(String word) {
//		vl.put(word, 1);
//	}
//	
//	public void increaseCount(String word) {
//		if (vl.containsKey(word)) {
//			int v = vl.get(word) + 1;
//			vl.put(word, v);
//		} else {
//			add(word);
//		}
//	}
//
//	public void update() {
//		try {
//			FileWriter write = new FileWriter(vocabulary_path, false);
//			PrintWriter print_line = new PrintWriter(write);
//		
//			for (String v: vl.keySet()) {
//				print_line.printf("%s,%d\n", v, vl.get(v));
//			}
//			
//			print_line.close();
//		} catch (IOException e) {
//			System.out.printf("Cannot open file");
//		}
//	}
//	
//	public void list() {
//		for (String words: vl.keySet()) {
//			System.out.printf("%s, %d\n", words, vl.get(words));
//		}
//	}
}