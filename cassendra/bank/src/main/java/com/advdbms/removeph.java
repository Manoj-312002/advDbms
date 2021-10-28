package com.advdbms;

import java.util.Scanner;
import java.util.Set;

public class removeph {
    public static void main(String[] args) throws Exception {
        connection db = new connection();
        Scanner sc = new Scanner(System.in);

        String uid = "b6b18147-2ad8-462b-93ac-bb7264dceff6";
        Set<String> s = db.getPh(uid);
        System.out.println("Phone numbers \n");
        for( var v : s ) System.out.println(v);

        System.out.print("Enter ph to remove : "); String ph = sc.nextLine();
        db.session.execute("UPDATE name_by_id SET ph = ph - { '" + ph + "' } WHERE id = " + uid + " AND name = 'user1' " );
        sc.close();
    }
}
