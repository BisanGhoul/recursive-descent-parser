# recursive-descent-parser
The RecursiveDescentPascalParser class implements a recursive descent parser for a simplified Pascal-like programming language. It reads a text file containing code written in this language and checks for syntax errors following the defined grammar rules. The parser employs recursive methods to analyze program declarations, statements, expressions, conditions, and more.

Key features of the parser include:

    * Lexical analysis using a PascalScanner to tokenize the input code.
    * Recursive parsing functions for program declaration, block structure, variable and constant declarations, statements like assignment, read/write, if-else, while, repeat-until, and more.
    * Error handling with detailed error messages indicating the line number, unexpected token, and expected token or syntax.

## Production Rules (Grammar):
![Image Alt Text](https://github.com/BisanGhoul/recursive-descent-parser/blob/master/production%20rules.png)

Note:     The tokens in bold letters are reserved words or standard identifiers.
	      The symbols between “ ? “  are special tokens.


## Sample Output:
![Image Alt Text](https://github.com/BisanGhoul/recursive-descent-parser/blob/master/analyzer%20output.png)


## Sample Code:
```program testing;
const
   max=33;
var
  m,n:integer;
  
begin
  readln(n,m);
  while m<>10 do
    begin
      n := (n div 10) +1
      write(n, m);
      {this is a comment}
    end;
  y:=3.5;
  x:=5.0;
  if n>m then
    if x>=5 then
      begin
        x:= x*y + 20.6;
        write(m,x);
      end
    else
      writeln(m,y);
 end.```
