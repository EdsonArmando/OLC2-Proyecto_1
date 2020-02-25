package Analizadores;
import java_cup.runtime.*;
import java.util.ArrayList;
%%
%{
    String cadena="";
    int llamadaString = 0;
    int ESTADOACTUAL = 0;

    public Symbol addSymbol(Symbol s){
        System.out.println(s.value.toString());
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
id = {letra}+({letra}|{digito}|"_")*   
caracter = "\'" [^\'] "\'"
espacio = \t|\f|" "|\r|\n


%state COMENTMULTI
%state COMENTSIMPLE
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

<COMENTMULTI>{
    "*/"         {yybegin(ESTADOACTUAL);}
    .            {}
    [ \t\r\n\f]  {}
}

<COMENTSIMPLE>{ 
    [^"\n"]      {}
      "\n"       {yybegin(ESTADOACTUAL);}
}

<YYINITIAL>{ 
    "/*"            { ESTADOACTUAL = YYINITIAL; yybegin(COMENTMULTI);} 
    "//"            { ESTADOACTUAL = YYINITIAL; yybegin(COMENTSIMPLE);}
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
    "-"             {return addSymbol(new Symbol(Syma.tResta,yycolumn,yyline,yytext()));}
    ";"             {return addSymbol(new Symbol(Syma.tPuntoComa,yycolumn,yyline,yytext()));}
    "."             {return addSymbol(new Symbol(Syma.tPunto,yycolumn,yyline,yytext()));}
    ">"             {return addSymbol(new Symbol(Syma.tMayorQ,yycolumn,yyline,yytext()));}
    "<"             {return addSymbol(new Symbol(Syma.tMenorQ,yycolumn,yyline,yytext()));}
    "!"             {return addSymbol(new Symbol(Syma.tDifQ,yycolumn,yyline,yytext()));}
    "&"             {return addSymbol(new Symbol(Syma.tAnd,yycolumn,yyline,yytext()));}
    "^"             {return addSymbol(new Symbol(Syma.tXor,yycolumn,yyline,yytext()));}
    "|"             {return addSymbol(new Symbol(Syma.tOr,yycolumn,yyline,yytext()));}
    "+"             {return addSymbol(new Symbol(Syma.tSuma,yycolumn,yyline,yytext()));}
    "*"             {return addSymbol(new Symbol(Syma.tMult,yycolumn,yyline,yytext()));}
    "/"             {return addSymbol(new Symbol(Syma.tDiv,yycolumn,yyline,yytext()));}
        

    {id}            {return addSymbol(new Symbol(Syma.tId,yycolumn,yyline,yytext()));}
    {caracter}      {return addSymbol(new Symbol(Syma.tCaracter,yycolumn,yyline,yytext()));}
    {entero}        {return addSymbol(new Symbol(Syma.tEntero,yycolumn,yyline,yytext()));}

}

<YYINITIAL>{
    {espacio}       { /* ignorar */ }
    .               {System.out.println("Error Lexico: <<"+yytext()+">> ["+yyline+" , "+yycolumn+"]");}
}