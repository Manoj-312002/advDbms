package com.advdbms;

import java.util.Scanner;

public class removeAcc {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter uid and swipe id");
        String uid = sc.nextLine();
        String swipe_id = sc.nextLine();

        connection db = new connection();
        db.remove(uid, swipe_id);
        sc.close();
    }
}
// 26902a90-1143-4e6e-af22-45c4cdb1b4e7
// 9b954cbd-b15f-4492-8a63-4020d7312444 