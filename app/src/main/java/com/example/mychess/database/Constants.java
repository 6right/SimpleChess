package com.example.mychess.database;

public class Constants {

    //Table Name
    public static final String TABLE_NAME = "players";

    //Table Columns
    public static final String _ID = "_id";
    public static final String NICKNAME = "nickname";

    //Database info
    static final String DB_NAME = "CHESS_PLAYERS.DB";

    //Database Version
    static final int DB_VERSION = 1;

    //Create Table query
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            NICKNAME + " TEXT)";

    public static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
}
