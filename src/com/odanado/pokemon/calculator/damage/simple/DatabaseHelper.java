/**
 * 参考
 * http://y-anz-m.blogspot.jp/2011/01/android-sqline-database.html
 */
package com.odanado.pokemon.calculator.damage.simple;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author odan
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "pokemon";
    private static String DB_NAME_ASSET = "pokemon.db";
    private static final int DATABASE_VERSION = 1;
    
    private SQLiteDatabase sqliteDatabase;
    private final Context context;
    private final File databasePath;
    
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        
        this.context = context;
        databasePath = this.context.getDatabasePath(DB_NAME);
    }
    
    /**
     * assets に格納したデータベースをコピーするために
     * からのデータベースを作成する
     */
    public void createEmptyDatabase() throws IOException {
        boolean isDatabaseExist = hasExisted();
        
        if(isDatabaseExist) {
            
        }
        else {
            // 空のデータベース作成
            getReadableDatabase();
            
            try {
                copyDatabaseFromAsset();
                
                String dbPath = databasePath.getAbsolutePath();
                SQLiteDatabase database = null;
                
                try {
                    database = SQLiteDatabase.openDatabase(dbPath, 
                            null, SQLiteDatabase.OPEN_READWRITE);
                
                } catch (SQLiteAbortException e) {
                    
                }
                
                if (database != null) {
                    database.setVersion(DATABASE_VERSION);
                    database.close();
                }
            } catch (SQLException e) {
                throw new Error("Error copying database");
            }
        }
    }
    
    public void copyDatabaseFromAsset() throws IOException {
        InputStream input = context.getAssets().open(DB_NAME_ASSET);
        OutputStream output = new FileOutputStream(databasePath);
        
        byte[] buffer = new byte[1024];
        int size;
        
        while ((size = input.read(buffer)) > 0) {
            output.write(buffer,0,size);
        }
        
        output.flush();
        output.close();
        input.close();
        
    }
    
    /**
     * データベースが存在しているか
     * @return 存在している場合 {@code true}
     */
    public boolean hasExisted() {
        boolean res = false;
        String dbPath = databasePath.getAbsolutePath();
        
        SQLiteDatabase database = null;
        
        try {
            database = SQLiteDatabase.openDatabase(dbPath, 
                    null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLException e) {
            
        }
        
        // 存在しない
        if (database == null) {
            res = false;
        }
        else {
        
            int oldVersion = database.getVersion();
            int newVersion = DATABASE_VERSION;
        
            //存在して最新
            if(oldVersion == newVersion) {
                database.close();
                res = true;
            }
            else {
                File f = new File(dbPath);
                f.delete();
                res = false;
            }
        }
        
        return res;
    }

    
    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    
    @Override  
    public synchronized void close() {  
        if(sqliteDatabase != null)  
            sqliteDatabase.close();  
  
            super.close();  
    }  

}
