import java.util.HashMap;

public class WordSets {

    public String Standard_identifiers[] = new String[]{
            "abs", "false", "page","sin","arctan","get","pred","sqr","boolean","input","put","sqrt","char","integer",
            "read","succ","chr","ln","readln","text","cos","maxint","real","true","dispose","new","reset",
            "trunc","eof","odd","rewrite","write","elon","ord","round","writeln","exp","output"};
	
    public static final HashMap<String, Integer> RESERVED_WORDS = new HashMap<String, Integer>();

    
    
    public HashMap<String,Integer> getReservedWords(){
    	HashMap<String, Integer> ReservedWords = new HashMap<String, Integer>();
    	ReservedWords.put("and",1);
    	ReservedWords.put("file",2);
    	ReservedWords.put("mod",3);
    	ReservedWords.put("repeat",4);
    	ReservedWords.put("array",5);
    	ReservedWords.put("for",6);
    	ReservedWords.put("nil",7);
    	ReservedWords.put("set",8);
    	ReservedWords.put("begin",9);
    	ReservedWords.put("forward",10);
    	ReservedWords.put("not",11);
    	ReservedWords.put("then",12);
    	ReservedWords.put("case",13);
    	ReservedWords.put("function",14);
    	ReservedWords.put("of",15);
    	ReservedWords.put("to",16);
    	ReservedWords.put("const",17);
    	ReservedWords.put("goto",18);
    	ReservedWords.put("or",19);
    	ReservedWords.put("type",20);
    	ReservedWords.put("div",21);
    	ReservedWords.put("if",22);
    	ReservedWords.put("packed",23);
    	ReservedWords.put("until",24);
    	ReservedWords.put("do",25);
    	ReservedWords.put("in",26);
    	ReservedWords.put("procedure",27);
    	ReservedWords.put("var",28);
    	ReservedWords.put("downto",29);
    	ReservedWords.put("label",30);
    	ReservedWords.put("program",31);
    	ReservedWords.put("while",32);
    	ReservedWords.put("else",33);
    	ReservedWords.put("main",34);
    	ReservedWords.put("record",35);
    	ReservedWords.put("with",36);
    	ReservedWords.put("end",37);
		return ReservedWords;

    }
    
    
}
