package Analizadores;
import java_cup.runtime.*;
import java.util.ArrayList;
%%
%{
    String cadena="";
    int llamadaString = 0;
    int ESTADOACTUAL = 0;

    public Symbol addSymbol(Symbol s){
        //System.out.println(s.value.toString());
        return s;
    }
%}

%public
%class LexicoArit
%cupsym Syma
%cup
%char
%line
%column
%full
%unicode
%ignorecase

digito = [0-9]
entero = {digito}+
doble = {digito}+"." {digito}+
numero = {digito}+("." {digito}+)?
letra = [a-zA-ZñÑ]
id = {letra}+({letra}|{digito}|("_"|"."))*   
caracter = "\'" [^\'] "\'"
espacio = \t|\f|" "|\r|\n


%state COMENT_SIMPLE
%state COMENT_MULTI
%state STRING

%%

<STRING>{
    [\"] {  yybegin(ESTADOACTUAL);
            String temporal = cadena; 
            cadena = "";
            return addSymbol( new Symbol(Syma.tCadena, yychar,yyline,temporal) );   
         }
    [^\"] { cadena += yytext(); }
}

<YYINITIAL> "#*"                {yybegin(COMENT_MULTI);}     // Si la entrada es un comentario inicia aqui
<COMENT_MULTI> "*#"             {yybegin(YYINITIAL);}        // Si se acaba el comentario vuelve a YYINITIAL
<COMENT_MULTI> .                {}
<COMENT_MULTI> [ \t\r\n\f]      {}

<YYINITIAL> "#"                {yybegin(COMENT_SIMPLE);}   // Si la entrada es comentario simple inicia aqui
<COMENT_SIMPLE> [^"\n"]         {}                          // 
<COMENT_SIMPLE> "\n"            {yybegin(YYINITIAL);}       // aqui sale del estado


<YYINITIAL>{ 
    "\""            { ESTADOACTUAL = YYINITIAL; yybegin(STRING);}
    "{"             {return addSymbol(new Symbol(Syma.tLlaveA,yycolumn,yyline,yytext()));}
    "}"             {return addSymbol(new Symbol(Syma.tLlaveC,yycolumn,yyline,yytext()));}
    ":"             {return addSymbol(new Symbol(Syma.tDosPuntos,yycolumn,yyline,yytext()));}
    "("             {return addSymbol(new Symbol(Syma.tParA,yycolumn,yyline,yytext()));}
    ")"             {return addSymbol(new Symbol(Syma.tParC,yycolumn,yyline,yytext()));}
    "["             {return addSymbol(new Symbol(Syma.tCorcheA,yycolumn,yyline,yytext()));}
    "]"             {return addSymbol(new Symbol(Syma.tCorcheC,yycolumn,yyline,yytext()));}
    ","             {return addSymbol(new Symbol(Syma.tComa,yycolumn,yyline,yytext()));}
    "="             {return addSymbol(new Symbol(Syma.tIgual,yycolumn,yyline,yytext()));}
    "=="            {return addSymbol(new Symbol(Syma.tIgualIgual,yycolumn,yyline,yytext()));}
    "-"             {return addSymbol(new Symbol(Syma.tResta,yycolumn,yyline,yytext()));}
    ";"             {return addSymbol(new Symbol(Syma.tPuntoComa,yycolumn,yyline,yytext()));}
    "."             {return addSymbol(new Symbol(Syma.tPunto,yycolumn,yyline,yytext()));}
    ">"             {return addSymbol(new Symbol(Syma.tMayorQ,yycolumn,yyline,yytext()));}
    "<"             {return addSymbol(new Symbol(Syma.tMenorQ,yycolumn,yyline,yytext()));}
    "!"             {return addSymbol(new Symbol(Syma.tDifQ,yycolumn,yyline,yytext()));}
    "%%"            {return addSymbol(new Symbol(Syma.tModulo,yycolumn,yyline,yytext()));}
    "&"             {return addSymbol(new Symbol(Syma.tAnd,yycolumn,yyline,yytext()));}
    "^"             {return addSymbol(new Symbol(Syma.tXor,yycolumn,yyline,yytext()));}
    "|"             {return addSymbol(new Symbol(Syma.tOr,yycolumn,yyline,yytext()));}
    "+"             {return addSymbol(new Symbol(Syma.tSuma,yycolumn,yyline,yytext()));}
    "*"             {return addSymbol(new Symbol(Syma.tMult,yycolumn,yyline,yytext()));}
    "/"             {return addSymbol(new Symbol(Syma.tDiv,yycolumn,yyline,yytext()));}
    "print"         {return addSymbol(new Symbol(Syma.tPrint,yycolumn,yyline,yytext()));}
    "function"      {return addSymbol(new Symbol(Syma.tFunction,yycolumn,yyline,yytext()));}
    "true"          {return addSymbol(new Symbol(Syma.tTrue,yycolumn,yyline,yytext()));}
    "false"         {return addSymbol(new Symbol(Syma.tFalse,yycolumn,yyline,yytext()));}
    "if"            {return addSymbol(new Symbol(Syma.tIf,yycolumn,yyline,yytext()));}
    "else"          {return addSymbol(new Symbol(Syma.tElse,yycolumn,yyline,yytext()));}
    "do"            {return addSymbol(new Symbol(Syma.tDo,yycolumn,yyline,yytext()));}
    "while"         {return addSymbol(new Symbol(Syma.tWhile,yycolumn,yyline,yytext()));}
    "for"           {return addSymbol(new Symbol(Syma.tFor,yycolumn,yyline,yytext()));}
    "return"        {return addSymbol(new Symbol(Syma.tReturn,yycolumn,yyline,yytext()));}
    "c"             {return addSymbol(new Symbol(Syma.tC,yycolumn,yyline,yytext()));}
    "list"          {return addSymbol(new Symbol(Syma.tList,yycolumn,yyline,yytext()));}
    "matrix"        {return addSymbol(new Symbol(Syma.tMatrix,yycolumn,yyline,yytext()));}
    "pie"           {return addSymbol(new Symbol(Syma.tPie,yycolumn,yyline,yytext()));}
    "barplot"       {return addSymbol(new Symbol(Syma.tBarplot,yycolumn,yyline,yytext()));}
    

    {id}            {return addSymbol(new Symbol(Syma.tId,yycolumn,yyline,yytext()));}
    {caracter}      {return addSymbol(new Symbol(Syma.tCaracter,yycolumn,yyline,yytext()));}
    {entero}        {return addSymbol(new Symbol(Syma.tEntero,yycolumn,yyline,yytext()));}
    {doble}         {return addSymbol(new Symbol(Syma.tDoble,yycolumn,yyline,yytext()));}

}

<YYINITIAL>{
    {espacio}       { /* ignorar */ }
    .               {System.out.println("Error Lexico: <<"+yytext()+">> ["+yyline+" , "+yycolumn+"]");}
}