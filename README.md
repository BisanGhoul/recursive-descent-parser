# recursive-descent-parser
The RecursiveDescentPascalParser class implements a recursive descent parser for a simplified Pascal-like programming language. It reads a text file containing code written in this language and checks for syntax errors following the defined grammar rules. The parser employs recursive methods to analyze program declarations, statements, expressions, conditions, and more.

Key features of the parser include:

    Lexical analysis using a PascalScanner to tokenize the input code.
    Recursive parsing functions for program declaration, block structure, variable and constant declarations, statements like assignment, read/write, if-else, while, repeat-until, and more.
    Error handling with detailed error messages indicating the line number, unexpected token, and expected token or syntax.

## Produxtion Rules (Grammar):
