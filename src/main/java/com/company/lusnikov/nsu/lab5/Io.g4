grammar Io;

prog:	(expr NEWLINE)* ;
expr:
    var
    | goto_
    | label
    | if_
    | print
    | declvar
    ;
declvar : SPACE* 'var ' VARNAME ('=') (INT |STRING);
var: SPACE* VARNAME ('=') (INT | STRING);
goto_: SPACE* 'goto ' INT ;
label: SPACE* 'label ' INT ;
print: SPACE* 'print' SPACE* (VARNAME | STRING);
if_: SPACE* 'if ' NEWLINE* '('cond')' NEWLINE* ' then' NEWLINE (expr NEWLINE)* 'end' ;
cond:
    'not' cond
    | orderable '>' orderable;
orderable : INT | STRING | VARNAME;
NEWLINE : [\r\n]+ ;
INT     : [1-9]+[0-9]* | [0];
VARNAME : [a-z]+;
STRING  : '"'STRINGL'"' ;
STRINGL : [a-z0-9]+ ;
SPACE : [ ] ;

