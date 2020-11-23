grammar Io;
prog:	(expr NEWLINE)* ;
expr:
    var
    | goto
    | label
    | if
    | print
    ;
var: ( VARNAME ('=') (INT | STRING));
goto: ('goto ' INT) ;
label: ('label ' INT) ;
print: 'print' ;
if: ('('cond') then' (expr NEWLINE)* 'end') ;
cond: 'jj';
NEWLINE : [\r\n]+ ;
INT     : [0-9]+ ;
VARNAME : [a-z]+;
STRING  : '"'[a-z0-9]+'"' ;

