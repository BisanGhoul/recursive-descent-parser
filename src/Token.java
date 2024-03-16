
public class Token {

	int id;
	String type;
    String token;
    int line;

public Token() {
	
}
    public Token(int id, String type, String token) {
		super();
		this.id = id;
		this.type = type;
		this.token = token;
	}
    
	public Token(int id, String type, String token, int line) {
		super();
		this.id = id;
		this.type = type;
		this.token = token;
		this.line = line;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String toString() {
        return token+"\t"+type+"\t"+String.valueOf(id);
    }

}
