import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecursiveDescentPascalParser {

	int i = 0;
	File file;
	PascalScanner lexical_analyzer ;
	List<Token> tokens;
	Token token;
	
	public void initialFunc(File file2) {
		 file = file2;
		 lexical_analyzer = new PascalScanner(file);
		 tokens = new ArrayList<Token>();
		 
		tokens=lexical_analyzer.returnTokens();
		prog_decl();
	}
	
	public void printErrorMsg(Token f,String expected) {
		String found =f.getToken();
		

		//ToDo
		System.err.println("****************************************************************************************************************************************");
		System.err.println("========================================================================================================================================");
		System.err.println(">>>>Error!");
		System.err.println(">>>> Line: "+f.getLine()+" ,"+ "token -> ["+found+"] should not be here or there is something missing before or after this token! <<<<");
		System.err.println(">>>>Expected =>> "+expected);
		System.err.println("========================================================================================================================================");
		System.err.println("****************************************************************************************************************************************");

		System.out.println("Exiting Program...");

		System.exit(1);
	}
	
	public Token nextT() {
		Token currToken = tokens.get(i);
		if (currToken.getId()==1010){//undefined
			System.out.println("================================================================");
			System.out.println("Error!");
			System.out.println("Line: "+ currToken.getLine()+", ["+currToken.getToken()+"] is undefined, (NOT IN THE ALPHAPATICAL SET)");
			System.out.println("================================================================");
			System.out.println("Exiting Program...");

			System.exit(1);
		}else {
			System.out.println("TOKEN>> ID:"+currToken.getId()+" | Token: "+currToken.getToken()+" |Type:"+currToken.getType() +"\n");
		}
		int length = tokens.size();
		if((i+1)<length) {
		i++;
		}
//		System.out.println("token: "+currToken.getToken()+" "+currToken.getLine());
		return currToken;
	}
	
	public void prog_decl() {
		token = nextT();
		
		heading();
		declarations();
		block();
		
		if(token.getId()==821) {//.
			System.out.println("************************************************************");
			System.out.println("=============================================================");
			System.out.println(">>>>>>  SUCCESS! No errors in your program!  <<<<<<<<");
			System.out.println("=============================================================");
			System.out.println("************************************************************");

		}else {
			printErrorMsg(token,".");
			
		}
	}
	
	public void heading() {
		if(token.getId()==31) {//program
			
			 token = nextT();
			if(token.getId()==7777) {//user defined name (program name)
				token = nextT();
				if(token.getId()==892) {//";"
					token = nextT();
				}else {
					printErrorMsg(token,"';'");
				}
				
			}else {
				printErrorMsg(token,"'program-name'");
			}
			
			
		}else {
			printErrorMsg(token,"'program'");
		}
	}
	
	public void block() {
		if(token.getId()==9) {//begin
			token = nextT();
			stmt_list();
			if(token.getId()==37) {//end
				token = nextT();
			}else {
				printErrorMsg(token,"end");
			}
		}else {
			printErrorMsg(token,"begin");
		}
	}
	
	public void declarations() {
		const_decl();
		var_decl();
	}
	
	public void const_decl() {
		if(token.getId()==17){//17=const, or token == lambda
			token = nextT();//get token
			//if token == const again?
			const_list();
		}else if(token.getId()!=28 && token.getId()!=9) {//28=var, 9=begin -> FOLLOW(const_decl)
			printErrorMsg(token,"var or begin");
		}
//		else {//do we put this??
//			printErrorMsg();
//		}
		
	}
	
	public void const_list() {
		if(token.getId()==7777){//const-name=7777 First(const_list)=const-name,LAMBDA
			
			token = nextT();//get token
			if(token.getId()==884) {//884="="
				token = nextT();//get token
				value();
				if(token.getId()== 892) {//892=";"
					token = nextT();//get token
					const_list();
				}else {
					printErrorMsg(token,"';'");
				}
			}else {
				printErrorMsg(token,"=");
			}
			
			
		}else if(token.getId()!=28 && token.getId()!=9) {//28=var, 9=begin -> FOLLOW(const_decl)
			printErrorMsg(token,"var or begin");
		}
		//else??
	}
	
	public void var_decl() {
		if(token.getId()==28) {//28=var
			token = nextT();//get token
			var_list();
		}else if(token.getId()!=9) {//9=begin FOLLOW
			printErrorMsg(token, "begin");
		}
	}
	
	public void var_list() {
		if(token.getId()==7777) {//var-name=7777 First=var-name 
//			token = getNextToken();//get token
			var_item();
			if(token.getId()==892) {//892=";"
				token = nextT();//get token
				var_list();
			}else {
				printErrorMsg(token,"';'" );

			}
		}else if(token.getId()!=9){//begin=9, FOLLOW=begin
			printErrorMsg(token,"begin");

		}
	}
	
	public void var_item() {
		name_list();
		if(token.getId()==894){//":"=894
			token = nextT();//get token
			data_type();
		}else {
			printErrorMsg(token,"':'");

		}
	}
	
	public void name_list() {
		if(token.getId()==7777) {//"var-name"
			token = nextT();//get token
			more_names();
		}else {
			printErrorMsg(token,"'var-name' or ';'");

		}
	}
	
	public void more_names() {
		if(token.getId()==889){//coma
			token = nextT();//get token
			name_list();
		}else if(token.getId()!=894 && token.getId()!=888) {//)=888, :=894 ,Follow=),:
			printErrorMsg(token, "')' or ':'");

		}
	}
	
	public void data_type() {
		if(token.getToken().toString().toLowerCase().trim().equals("integer")) {
			token = nextT();//get token

		}else if(token.getToken().toString().toLowerCase().trim().equals("real")) {
			token = nextT();//get token

		}else if(token.getToken().toString().toLowerCase().trim().equals("char")) {
			token = nextT();//get token

		}else {//??????????
			printErrorMsg(token,"data type such as integer, real, or char");

		}
	}
	
	//FIRST λ, “var-name”, write, writeln, while, begin, read, readln, repeat, if
	public void stmt_list() {
		//FIRST a1
		if(token.getId()==7777 || token.getId()==22 || token.getId()==9 || token.getId()==32 || token.getId()==4//if,begin,while,repeat
				||(token.getToken().toString().toLowerCase().trim().equals("write"))
				||(token.getToken().toString().toLowerCase().trim().equals("writeln"))
				||(token.getToken().toString().toLowerCase().trim().equals("read"))
				||(token.getToken().toString().toLowerCase().trim().equals("readln"))
				){
//			token = getNextToken();//get token
			statement();
			if(token.getId()==892) {//;
				token = nextT();//get token
				stmt_list();
			}else {
				printErrorMsg(token, "';'");

			}
		}else if(token.getId()!=24 && token.getId()!=37) {//until, end
			printErrorMsg(token,"until or end or ; or ) or .");

		}
	}
	
	public void statement() {
		if(token.getId()==7777) {//"var-name"
//			token = getNextToken();//get token
			ass_stmt();
		}else if((token.getToken().toString().toLowerCase().trim().equals("read"))
				||(token.getToken().toString().toLowerCase().trim().equals("readln"))) {
//			token = getNextToken();//get token
			read_stmt();
		}else if((token.getToken().toString().toLowerCase().trim().equals("write"))
				||(token.getToken().toString().toLowerCase().trim().equals("writeln"))) {
//			token = getNextToken();//get token
			write_stmt();
		}else if(token.getId()==22) {//if
//			token = getNextToken();//get token
			if_stmt();
		}else if(token.getId()==32) {//while
//			token = getNextToken();//get token
			while_stmt();
		}else if(token.getId()==4) {//repeat
//			token = getNextToken();//get token
			repeat_stmt();
		}else if(token.getId()==9) {//begin
//			token = getNextToken();//get token
			block();
		}else {
			printErrorMsg(token,"'var-name', read, readln, write, writeln, if, while, repeat, begin ");

		}
	}
	
	public void ass_stmt() {
		if(token.getId()==7777) {//"var-name"
			token = nextT();//get token
			if(token.getId()==893) {//:=
				token = nextT();//get token
				exp();
			}else {
				printErrorMsg(token,"':='");

			}
		}else {
			printErrorMsg(token,"'var-name'");

		}
	}
	
	public void exp() {
		term();
		exp_prime();
	}
	
	public void exp_prime() {
		if(token.getId()==880 || token.getId()==881){//+, - First
//			token = getNextToken();//get token
			add_oper();
			term();
			exp_prime();
		}else if(token.getId()!=888 && token.getId()!=33 && token.getId()!=892){//")",else,";" FOLLOW
			printErrorMsg(token,"')' or else or ';'");
		}
	}
	
	public void term() {
		factor();
		term_prime();
	}
	
	public void term_prime() {
		if(token.getId()==882 || token.getId()==883 || token.getId()==3 || token.getId()==21){//*,/,mod,div FIRST
			mul_oper();
			factor();
			term_prime();
		}else if(token.getId()!=880 && token.getId()!=881 && token.getId()!=888 && token.getId()!=33 && token.getId()!=892) {//+,-,),else,; FOLLOW
			printErrorMsg(token,"+ or - or ) or else or ;");
		}
	}
	
	public void factor() {
		if(token.getId()==887) {//"(", First 
			token = nextT();//get token
			exp();
			if(token.getId()==888) {//")"
				token = nextT();//get token
			}else {
				printErrorMsg(token,")");

			}
			
		}else if(token.getId()==7777 || token.getId()==1111 || token.getId()==2222) {//userdefined, float value, int value > FIRST
//			token = getNextToken();//get token
			name_value();
		}else {
			printErrorMsg(token,"'(' or 'var-name' or 'const-name' or float-value or integer-value");

		}
	}
	
	//add-oper -> + | -
	public void add_oper() {
		if(token.getId()==880) {//+
			token = nextT();//get token

		}else if(token.getId()==881) {//-
			token = nextT();//get token

		}else {
			printErrorMsg(token,"'-' or '+'");

		}
	}
	
	//mul-oper -> *|/|mod|div
	public void mul_oper() {
		if(token.getId()==882) {//*
			token = nextT();//get token

		}else if(token.getId()==883) {//"/"
			token = nextT();//get token

		}else if(token.getId()==3) {//mod
			token = nextT();//get token

		}else if(token.getId()==21){//div
			token = nextT();//get token

		}else {
			printErrorMsg(token,"* or / or mod or div");

		}
	}
	
	//value -> "float-val"|"integer-val"
	public void value() {
		if(token.getId()==1111) {//float
			token = nextT();//get token

		}else if(token.getId()==2222) {//int
			token = nextT();//get token

		}else {
			printErrorMsg(token,"float-value or integer-value");

		}
	}
	
	
	//read-stmt -> read "(" name-list ")" | readln "(" name-list ")"
	public void read_stmt() {
		
		if((token.getToken().toString().toLowerCase().trim().equals("read"))) {//read
			token = nextT();//get token
			if(token.getId()==887){//"("
				token = nextT();//get token
				name_list();
				if(token.getId()==888) {//")"
					token = nextT();//get token

				}else {
					printErrorMsg(token,"')'");

				}
			}else{
				printErrorMsg(token,"(");

			}
		}else if((token.getToken().toString().toLowerCase().trim().equals("readln"))) {//readln
			token = nextT();//get token
			if(token.getId()==887){//"("
				token = nextT();//get token
				name_list();
				if(token.getId()==888) {//")"
					token = nextT();//get token

				}else {
					printErrorMsg(token,"')'");

				}
			}else{
				printErrorMsg(token,"'('");

			}
		}else {//error
			printErrorMsg(token, "read or readln");

		}
	}
	
	public void write_stmt() {
		if((token.getToken().toString().toLowerCase().trim().equals("write"))) {//write
			token = nextT();//get token
			if(token.getId()==887){//"("
				token = nextT();//get token
				name_list();
				if(token.getId()==888) {//")"
					token = nextT();//get token

				}else {
					printErrorMsg(token,"')'");

				}
			}else{
				printErrorMsg(token,"'('");

			}
		}else if((token.getToken().toString().toLowerCase().trim().equals("writeln"))) {//writeln
			token = nextT();//get token
			if(token.getId()==887){//"("
				token = nextT();//get token
				name_list();
				if(token.getId()==888) {//")"
					token = nextT();//get token

				}else {
					printErrorMsg(token,"')'");

				}
			}else{
				printErrorMsg(token,"'('");

			}
		}else {//error
			printErrorMsg(token,"write or writeln");
		}

	}
	
	public void if_stmt() {
		if(token.getId()==22) {//if
			token = nextT();//get token
			condition();
			if(token.getId()==12) {//then
				token = nextT();//get token
				statement();
				else_part();
			}else {
				printErrorMsg(token,"then");

			}
		}else {
			printErrorMsg(token,"if");
		}
	}
	
	public void else_part() {
		if(token.getId()==33) {//else >> FIRST
			token = nextT();//get token
			statement();
		}else if(token.getId()!=33 && token.getId()!=892){//else,; >>FOLLOW
			printErrorMsg(token,"else or ';'");

		}//???
	}
	
	public void while_stmt() {
		if(token.getId()==32) {//while
			token = nextT();//get token
			condition();
			if(token.getId()==25) {//do
				token = nextT();//get token
				statement();
			}else {
				printErrorMsg(token,"do");

			}
			
		}else {
			printErrorMsg(token,"while");

		}
	}
	
	public void repeat_stmt() {
		if(token.getId()==4) {//repeat
			token = nextT();//get token
			stmt_list();
			if(token.getId()==24) {//until
				token = nextT();//get token
				condition();
			}else {
				printErrorMsg(token,"until");

			}
		}else {
			printErrorMsg(token,"repeat");

		}
	}
	
	public void condition() {
		name_value();
		relational_oper();
		name_value();
	}
	
	public void name_value() {
		if(token.getId()==7777) {//"var-name" | "const-name"
			token = nextT();//get token

		}else if(token.getId()==1111 || token.getId()==2222) {//float-value, integer-value
//			token = getNextToken();//get token
			value();
		}else {
			printErrorMsg(token,"float-value or integer-value");

		}
	}
	
	public void relational_oper() {
		if(token.getId()==884) {//=
			token = nextT();//get token

		}else if(token.getId()==896) {//<>
			token = nextT();//get token

		}else if(token.getId()==897) {//<
			token = nextT();//get token

		}else if(token.getId()==895) {//<=
			token = nextT();//get token

		}else if(token.getId()==899) {//>
			token = nextT();//get token

		}else if(token.getId()==898) {//>=
			token = nextT();//get token

		}else {
			printErrorMsg(token,"relational operand, such as: =,<>,<,<=,>,>= ");

		}
	}
	

}
