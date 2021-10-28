package com.advdbms;

import java.util.Scanner;

public class transferFund {
    public static void main(String[] args) throws Exception {
        connection db = new connection();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your account number : "); String uid = sc.nextLine();
        System.out.print("Enter your swipe id : "); String swipe_id = sc.nextLine();
        System.out.print("Enter your cvv : "); int cvv = sc.nextInt();
        System.out.print("Enter your pin : "); int pin = sc.nextInt();
        System.out.print("Enter sender account : "); String toId = sc.next();
        System.out.print("Enter Amount : "); Float amt = sc.nextFloat();

        if( db.verifyPin(uid, swipe_id, cvv, pin) ) db.transact(uid, toId, -amt);
        else System.out.println("Invalid credentials");
        
        System.out.println("Transactions \n");
        db.transactions(uid);

        sc.close();
    }
}
