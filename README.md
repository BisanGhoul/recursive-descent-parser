# recursive-descent-parser
The RecursiveDescentPascalParser class implements a recursive descent parser for a simplified Pascal-like programming language. It reads a text file containing code written in this language and checks for syntax errors following the defined grammar rules. The parser employs recursive methods to analyze program declarations, statements, expressions, conditions, and more.

Key features of the parser include:

    Lexical analysis using a PascalScanner to tokenize the input code.
    Recursive parsing functions for program declaration, block structure, variable and constant declarations, statements like assignment, read/write, if-else, while, repeat-until, and more.
    Error handling with detailed error messages indicating the line number, unexpected token, and expected token or syntax.

## Produxtion Rules (Grammar):
	prog-decl ->    heading        declarations        block        “.”
	heading   -> program        “program-name”          “;” 
	block  ->  begin        stmt-list         end
	declarations   ->  const-decl    var-decl  
    	const-decl  -> const    const-list     |       LAMBDA    
const-list    ->   “const-name”    “=”    value      “;”      const-list | LAMBDA 
	var-decl  -> var    var-list     |      LAMBDA	
var-list   ->  var-item     “;”      var-list     |     LAMBDA
var-item   ->  name-list     “:”     data-type    
name-list  ->   “var-name”         more-names 
            more-names   ->    “,”     name-list       |        LAMBDA
	data-type  ->   integer    |    real   |     char  
IRST= λ, “var-name”, write, writeln, read, readln, if
	statement ->  ass-stmt     |     read-stmt     |     write-stmt    |      if-stmt   
                                  |    while-stmt     |     repeat-stmt     |      block  
ass-stmt -> “var-name”        “:=”        exp
exp -> term      exp-prime
	exp-prime -> add-oper     term     exp-prime       |       LAMBDA	
	term -> factor        term-prime  
term-prime  ->  mul-oper       factor       term-prime        |       LAMBDA
	factor ->  “(“     exp     “)”     |       name-value
	add-oper ->  “+”    |     “-“  
	mul-oper ->  “*”     |     “/”       |      mod     |    div
	value ->  “float-value”     |        “integer-value”
	read-stmt ->read    “(“    name-list    “)”     |    readln  “(“    name-list    “)”     
	write-stmt -> write    “(“    name-list    “)”     |    writeln  “(“    name-list    “)”	
if-stmt -> if     condition       then       statement     else-part        
	else-part ->   else     statement     |    LAMBDA
	while-stmt -> while      condition       do      statement
	repeat-stmt   ->  repeat      stmt-list       until        condition   
	condition -> name-value       relational-oper        name-value 
            name-value ->  “var-name”    |    “const-name”    |      value 	
relational-oper ->  “=”      |       “<>”         |       “<”       |       “<=”      |      “>”      |       “>=”



Note:     The tokens in bold letters are reserved words or standard identifiers.
	      The symbols between “ ? “  are special tokens.
