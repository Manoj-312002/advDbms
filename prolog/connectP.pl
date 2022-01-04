:- use_module(library(odbc)).

employee(Emp) :-
    odbc_connect('SWI-Prolog Discourse', Connection, []),
    odbc_query(Connection, 'SELECT (fname) FROM employee', row(Emp) ).

male(Emp) :-
    odbc_connect('SWI-Prolog Discourse', Connection, []),
    odbc_query(Connection, 'SELECT (fname) FROM employee where sex=\'M\' ', row(Emp) ).

department(Emp, Dpt) :-
    odbc_connect('SWI-Prolog Discourse', Connection, []),
    odbc_query(Connection, 'SELECT (dname) , (fname) from employee,department where dnumber=dno ', row(Dpt , Emp) ).

salary(Emp , Sal ) :-
    odbc_connect('SWI-Prolog Discourse', Connection, []),
    odbc_query(Connection, 'SELECT (fname),(salary) from employee ', row(Emp , Sal) , [types([default,float])] ).

works_on( Emp , Proj ,  Hrs ) :-
    odbc_connect('SWI-Prolog Discourse', Connection, []),
    odbc_query(Connection, ' SELECT (fname) , (pname) , (hours) from employee , project , works_on where pno = pnumber and essn = ssn ' , row(Emp , Proj , Hrs) , [types([default,default,float])] ).

supervise( Emp2 , Emp1 ) :-
    odbc_connect('SWI-Prolog Discourse', Connection, []),
    odbc_query(Connection, 'SELECT (s2.fname) , (s1.fname) from employee s1 , employee s2 where s1.ssn = s2.super_ssn ', row( Emp1 , Emp2 ) ).

superior( Emp1 , Emp2 ) :- supervise( Emp1 , Emp2 ).
superior( Emp1 , Emp2 ) :- supervise( Emp1 , X ) , superior( X , Emp2 ).
subordinate( Emp1 , Emp2 ) :- superior( Emp2 , Emp1 ).
over_40k_emp(X) :- employee(X) , salary(X,Y) , Y >= 40000.
main_productx_emp(X) :- employee(X) , works_on(X , 'ProductX' , Y ) , Y >= 20.
president(X) :- employee(X) , not( superior(Y,X) ).