package com.advdbms;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class createAcc {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        connection db = new connection();
        System.out.print("Enter name : "); String name = sc.nextLine();
        System.out.print("Enter Intial Deposit : "); Float balance = sc.nextFloat();
        
        Set<String> s = new HashSet<String>();
        System.out.println("Enter phone number if over enter -1 ");
        while( true ){
            String t = sc.nextLine();
            if( t.equals("-1") ) break;
            if( t.equals("") ) continue;
            s.add(t);
        }
        
        String uid = db.insertName(name, s, balance);

        System.out.print("Enter cvv : "); int cvv = sc.nextInt();
        System.out.print("Enter pin : "); int pin = sc.nextInt();

        String swipe_id = db.insertPin(uid, cvv, pin);

        System.out.println("Account created : " );
        System.out.println("user id : " + uid + "\n Swipe id : " + swipe_id );

        sc.close();
    }
}
