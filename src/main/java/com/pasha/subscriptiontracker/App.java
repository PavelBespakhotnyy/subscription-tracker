package com.pasha.subscriptiontracker;

import com.pasha.subscriptiontracker.Database.ConnectionDatabase;

public class App
{
    public static void main( String[] args )
    {
        System.out.println("Connecting...");
        ConnectionDatabase con = new ConnectionDatabase();
        con.Connect();
    }
}
