package online.divyesh.baseproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 26/12/17.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "db_name";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "tbl_name";

    private static final String KEY_ID = "id";
    private static final String KEY_POST_URL = "post_url";
    private static final String KEY_FILE_NAME = "file_name";

    private Context context;

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_POST_URL + " TEXT,"
                + KEY_FILE_NAME + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public List<ResponseDatum> getAllEmp() {

        SQLiteDatabase db = this.getReadableDatabase();

        List<ResponseDatum> res = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                ResponseDatum data = new ResponseDatum();
                data._id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                data.filename = cursor.getString(cursor.getColumnIndex(KEY_FILE_NAME));
                data.postUrl = cursor.getString(cursor.getColumnIndex(KEY_POST_URL));

                res.add(data);
            }
        }

        db.close();

        return res;
    }

    public long addEmps(List<ResponseDatum> data) {

        SQLiteDatabase db = this.getWritableDatabase();
        long flag = 0;
        for (int i = 0; i < data.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(KEY_POST_URL, data.get(i).postUrl);
            values.put(KEY_FILE_NAME, data.get(i).filename);
            flag = db.insert(TABLE_NAME, null, values);
        }

        db.close();

        return flag;
    }
}
