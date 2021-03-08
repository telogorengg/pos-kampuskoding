package mvp.ujang.posmvp.utils;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ajts.androidmads.sqliteimpex.SQLiteImporterExporter;

public class Connection {

    private SQLiteImporterExporter dbHelper;
    private SQLiteDatabase database;
    private Context context;

    public Connection(Context context) {
        this.context = context;
    }

    public Connection open()throws SQLException {
        dbHelper = new SQLiteImporterExporter(context, AppConstants.DB_NAME);
        if (!dbHelper.isDataBaseExists()) {
            importDB();
        }
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public Connection close()throws SQLException{
        dbHelper.close();
        return this;
    }

    public SQLiteImporterExporter dbHelper() {
        return dbHelper;
    }

    public SQLiteDatabase database() {
        return database;
    }

    public void importDB(){
        try {
            dbHelper .importDataBaseFromAssets();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
