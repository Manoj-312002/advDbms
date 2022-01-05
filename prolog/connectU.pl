:- use_module(library(odbc)).

student(Std) :-
    odbc_connect('SWI-Prolog Discourse', Connection, []),
    odbc_query(Connection, 'SELECT (fname) FROM students', row(Std) ).

course(Crs) :-
    odbc_connect('SWI-Prolog Discourse', Connection, []),
    odbc_query(Connection, 'SELECT (cname) FROM courses', row(Crs) ).

credCourse( Course , Credits ) :- 
    odbc_connect('SWI-Prolog Discourse', Connection, []),
    odbc_query(Connection, 'SELECT (cname),(credits) from courses', row(Course , Credits) , [types([default,integer])] ).

preq( Preq , Course ) :-
    odbc_connect('SWI-Prolog Discourse', Connection, []),
    odbc_query(Connection, 'select (t1.cname) , (t2.cname) from preq , courses t1 , courses t2 where t1.cid = preq.c1 and t2.cid = preq.c2', row(Preq , Course) ).

courseEnr( Std , Course ) :- 
    odbc_connect('SWI-Prolog Discourse', Connection, []),
    odbc_query(Connection, 'select (fname) , (cname) from courses_enr , students, courses where ssn = Sssn and Ecid = cid', row(Std , Course) ).

preqA(X,Y) :- preq(X,Y).
preqA(X,Y) :- preq(X,Z), preqA(Z,Y).

preq_student(X,Y) :- preqA(Y,Z) , courseEnr(X, Z).
