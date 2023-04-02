package database;

import android.content.Context;
import android.database.sqlite.*;

public class DatabaseHelper {

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
    public static final String CREATE_TABLE = "create table " + TABLE_NAME + "("

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

    }
}
