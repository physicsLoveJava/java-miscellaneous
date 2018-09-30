//193
//a = 5
//b = 6
//a + b * 2
//(1 + 2) * 2

grammar Calculator;

prog: stat+;

stat: expr rn       #printExpr
| ID '=' expr rn    # assign
| rn                # blank
;

expr: expr op=('*' | '/') expr     #mulDiv
| expr op=('+'|'-') expr            #addSub
| INT                           #int
| ID                            #id
| '(' expr ')'                  #parensis
;

ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';

ID: [a-zA-Z]+;
INT: [0-9]+;
rn: '\r' ? '\n';
WS: [ \t]+ -> skip;