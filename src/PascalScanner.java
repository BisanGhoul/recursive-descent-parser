import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PascalScanner {

	WordSets sets = new WordSets();
	String standard_identifiers[] = sets.Standard_identifiers;
	HashMap<String, Integer> RESERVED_WORDS = sets.getReservedWords();

	static final String PLUS = "+";
	static final String MINUS = "-";
	static final String MUL = "*";
	static final String DIV = "/";
	static final String NEW_LINE = "/";


	
	char currChar;
	BufferedReader scanner;

	int lines=1;
	List<Token> tokens = new ArrayList<>();

	public PascalScanner(File file) {
		try {
			
//			test2
			
//		System.out.println("test");
			scanner =
					new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		currChar = returnNextHarf();
		
	}
	char returnNextHarf() {
		try {
			return (char) scanner.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (char) (-1);
	}
	List<Token>  returnTokens() {
		Token token = nT();//read first token
		
		while (token != null) {
			
			tokens.add(token);
			token = nT();
			
		}
		
		return tokens;
	}

	Token nT() {//next token
		
		
		int case_num = 1;

		for(;;) {
//        	System.out.println(current);
			if (currChar==(char)(-1)){//end
				try {scanner.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}return null;
			} // if end

			
			
			
			switch (case_num) {
			
			
			case 1: {
				
				if ( currChar == '\t'||
						 currChar == '\b'||currChar == '\f' 
						|| currChar == '\r'||currChar == ' '||currChar == '\n') {
					if(currChar == '\n') {
						lines++;
					}
					currChar = returnNextHarf();
					
					continue;
				} else if (currChar == '+') {
					currChar = returnNextHarf();
					
					return new Token(880, "Special Symbol", PLUS,lines);
					
				} else if (currChar == '-') {
					currChar = returnNextHarf();
					//sysou
					return new Token(881, "Special Symbol", MINUS,lines);
				} else if (currChar == '*') {
					
					currChar = returnNextHarf();
					
					return new Token(882, "Special Symbol", MUL,lines);
				} else if (currChar == '/') {
					
							currChar = returnNextHarf();
					return new Token(883, "Special Symbol", DIV,lines);
				} else if (currChar == '=') {
					currChar = returnNextHarf();
					
					return new Token(884, "Special Symbol", "=",lines);
					
				}  else if (currChar == '[') {
					currChar = returnNextHarf();
					return new Token(885, "Special Symbol", "[",lines);
				} else if (currChar == ']') {
					currChar = returnNextHarf();
					return new Token(886, "Special Symbol", "]",lines);
				} else if (currChar == '(') {

					currChar = returnNextHarf();
					if (currChar == '*') {
						currChar = returnNextHarf();
						// String word = String.valueOf(currChar);
//System.out.println("c1: "+currChar);
//System.out.println((currChar != '*' ));
//System.out.println(currChar != (char) -1);
//System.out.println(readNextChar()!=')');
//System.out.println((currChar != '*' && currChar != (char) -1 && readNextChar()!=')') );
						while (currChar != '*' && currChar != (char) -1) {
							// word += String.valueOf(currChar);
//							System.out.println("c1 before: "+currChar);

							currChar = returnNextHarf();

//							System.out.println("c1 after: "+currChar);

						}
//						System.out.println("c1 out: "+currChar);

						currChar = returnNextHarf();
						if (currChar == ')') {
							currChar = returnNextHarf();
break;
						}
					} else {
//						currChar = readNextChar();
						return new Token(887, "Special Symbol", "(",lines);
					}
				} else if (currChar == ')') {
					currChar = returnNextHarf();
					return new Token(888, "Special Symbol", ")",lines);
				} else if (currChar == '{') {//comment
					
					while (currChar != '}' && currChar != (char) -1) {
						currChar = returnNextHarf();
					}
					currChar = returnNextHarf();
					break;
				} else if (currChar == ',') {
					currChar = returnNextHarf();
					return new Token(889, "Special Symbol", ",",lines);
				} else if (currChar == '^') {
					currChar = returnNextHarf();
					return new Token(890, "Special Symbol", "^",lines);
				} else if (currChar == '.') {
					currChar = returnNextHarf();
					if (currChar == '.') {
						currChar = returnNextHarf();
						return new Token(820, "Special Symbol", "..",lines);
					} else {
						return new Token(821, "Special Symbol", ".",lines);
					}
				} else if (currChar == ';') {
					currChar = returnNextHarf();
					return new Token(892, "Special Symbol", ";",lines);
				} else if (currChar == '\'') {// '
					currChar = returnNextHarf();
					//String word = String.valueOf(currChar);
					String word="";
					while (currChar != '\'' && currChar != (char) -1) {
						 word += String.valueOf(currChar);
						currChar = returnNextHarf();

					}
					currChar = returnNextHarf();

					return new Token(7777, "String", '\''+word+'\'',lines);

				} else if (currChar == ':') {
					currChar = returnNextHarf();
					if (currChar == '=') {
						currChar = returnNextHarf();
						return new Token(893, "Special Symbol", ":=",lines);
					} else {
						return new Token(894, "Special Symbol", ":",lines);
					}
				} else if (currChar == '<') {
					currChar = returnNextHarf();
					if (currChar == '=') {
						currChar = returnNextHarf();
						return new Token(895, "Special Symbol", "<=",lines);
					} else if (currChar == '>') {
						currChar = returnNextHarf();
						return new Token(896, "Special Symbol", "<>",lines);

					} else {
						return new Token(897, "Special Symbol", "<",lines);
					}
				} else if (currChar == '>') {
					currChar = returnNextHarf();
					if (currChar == '=') {
						
						currChar = returnNextHarf();
						return new Token(898, "Special Symbol", ">=",lines);
					} else {
						
						
						
						
						return new Token(899, "Special Symbol", ">",lines);
					}
				} else {
					//cont
						case_num = 2;
						
						
						
						
						continue;
				}

			}
			case 2: {
				if (isRaqam(currChar)==true) {
					String number = String.valueOf(currChar);
					
					while(true) {
						
						currChar = returnNextHarf();
						if (currChar == '.'||isRaqam(currChar)) {
							number =number+String.valueOf(currChar);
							
						} else {
							
							if (number.contains("."))
								return new Token(1111, "Float", number,lines);
							else
								return new Token(2222, "Integer", number,lines);
						}
						
					}
				} else
					
					case_num = 3;
			}
			case 3: {
				if (isHarf(currChar) ) {
					
					String word = String.valueOf(currChar);
					while(true) {
						currChar = returnNextHarf();
						if (isHarf(currChar)||isRaqam(currChar)) {
							word += String.valueOf(currChar);
						} else {
							
							List standard_identifiersList = Arrays.asList(standard_identifiers);

							if (standard_identifiersList.contains(word.toLowerCase())) {
								return new Token(7676, "Standard Identifier", word,lines);
							} else if (RESERVED_WORDS.containsKey(word.toLowerCase())) {
								return new Token(RESERVED_WORDS.get(word.toLowerCase()), "Reserved Word", word,lines);
							} else {
								return new Token(7777, "Identifier", word,lines);
							}

						}
					} // end for
				} else {
					Token token = new Token(1010, "Error (undefined)", currChar + "",lines);
					currChar = returnNextHarf();

					
					return token;

				}
			}
			}
		}

	}
	boolean isHarf(char character) {
		boolean x = ('z'>=character);
		boolean y = ('a'<=character);
		boolean X = ('Z'>=character);
		boolean Y = ('A'<=character);
		if(x&&y)
			return true;
		if(X&&Y)
			return true;
		//if ture return else false

		return false;
	}
	boolean isRaqam(char c) {
		return Character.isDigit(c);

	}

}