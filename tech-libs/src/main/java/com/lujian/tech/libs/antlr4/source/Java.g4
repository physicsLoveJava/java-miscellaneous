grammar Java;

classDeclaration: 'class' Identifier typeParameter? ('extends' type)? ('implements' typeList)? classBody;

methodDeclaration: type methodName '(' args? ')' methodBody?;