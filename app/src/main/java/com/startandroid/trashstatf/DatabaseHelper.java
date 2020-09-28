package com.startandroid.trashstatf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;



public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME ="CardDelivery_BD";
    private static final int DATABASE_VERSION = 2;

    // Информация таблицы clients
    private static final String TABLE_CLIENTS = "clients";
    private static final String KEY_client_login = "client_login"; // почта пользователя
    private static final String KEY_client_password = "client_password";
    private static final String KEY_client_name = "client_name";
    private static final String KEY_client_mobile = "client_mobile";
    private static final String KEY_client_surname = "client_surname";
    private static final String KEY_client_id = "client_id";
    private static final String KEY_client_passport = "client_passport";


    // Информация таблицы orderingCards
    private static final String TABLE_ORDERINGCARDS = "orderingCards";
    private static final String KEY_order_id = "order_id";
    private static final String KEY_order_cardType = "card_type";
    private static final String KEY_order_cardName = "card_name";
    private static final String KEY_order_city = "order_city";
    private static final String KEY_order_street = "order_street";
    private static final String KEY_order_flat = "order_flat";
    private static final String KEY_order_home = "order_home";
    private static final String KEY_order_korp = "order_kop";
    private static final String KEY_order_entrance = "order_entrance";
    private static final String KEY_order_status = "order_status";

    private Context context;



    // Создать таблицу клиентов
    private static final String CREATE_TABLE_CLIENTS = "CREATE TABLE "+ TABLE_CLIENTS +
                                                    "("+KEY_client_id +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                                                    KEY_client_login + " TEXT,"+
                                                    KEY_client_surname + " TEXT,"+
                                                    KEY_client_name+" TEXT,"+
                                                    KEY_client_passport + " TEXT,"+
                                                    KEY_client_password+" TEXT," +
                                                    KEY_client_mobile+" TEXT );";



    // Создать таблицу с заказами карт
    private static final String CREATE_TABLE_ORDERINGCARDS = "CREATE TABLE "+ TABLE_ORDERINGCARDS +
            "("+KEY_order_id +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
            KEY_client_login + " INTEGER,"+
            KEY_order_cardType + " TEXT,"+
            KEY_order_cardName + " TEXT,"+
            KEY_order_city+" TEXT,"+
            KEY_order_street + " TEXT,"+
            KEY_order_home + " TEXT,"+
            KEY_order_korp + " TEXT,"+
            KEY_order_entrance + " TEXT,"+
            KEY_order_status + " TEXT,"+
            KEY_order_flat+" TEXT );" ;





    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    // Создание таблиц при первом запуске
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CLIENTS);
        db.execSQL(CREATE_TABLE_ORDERINGCARDS);
    }

    // Существует ли клиент в базе
    public boolean isClientExists(String login){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_CLIENTS+" WHERE "+KEY_client_login+"='"+login+"'",null);
        if(cursor.moveToNext()){
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    // Проверка корректности пароля
    public boolean isPassCorrect(String login, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_CLIENTS+" WHERE "+KEY_client_login+"='"+login+"'"+" AND "+KEY_client_password+"='"+pass+"'",null);
        if(cursor.moveToNext()){
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }


    // Добавить нового клиента в базу данных
    public void addNewUser(String password, String email, String number, String name, String surname, String passport){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_client_login,email);
        values.put(KEY_client_password,password);
        values.put(KEY_client_mobile,number);
        values.put(KEY_client_name,name);
        values.put(KEY_client_surname, surname);
        values.put(KEY_client_passport, passport);
        db.insert(TABLE_CLIENTS , null, values);
    }

    // Добавить заказ карты
    public void addNewOrder(String login, String cardType, String cardName, String city,String street, String home, String korp, String entrance, String flat, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_client_login, login);
        values.put(KEY_order_cardType, cardType);
        values.put(KEY_order_cardName, cardName);
        values.put(KEY_order_city, city);
        values.put(KEY_order_street, street);
        values.put(KEY_order_home, home);
        values.put(KEY_order_korp, korp);
        values.put(KEY_order_entrance, entrance);
        values.put(KEY_order_flat, flat);
        values.put(KEY_order_status, status);
        db.insert(TABLE_ORDERINGCARDS, null, values);
    }

    public boolean authClient(String email, String pass){
        if(isClientExists(email)){
            return isPassCorrect(email, pass);
        }
        return false;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor readAllClientOrders(String login){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +TABLE_ORDERINGCARDS+" WHERE "+KEY_client_login +"='"+login+"'" ,null);
        return cursor;
        }

        public String getName(String login){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT "+KEY_client_name+" FROM " +TABLE_CLIENTS+" WHERE "+KEY_client_login +"='"+login+"'" ,null);
            String result="";
            while(cursor.moveToNext())
                result = cursor.getString(cursor.getColumnIndex(KEY_client_name));
            return result;
        }

        public String getSurname(String login){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+KEY_client_surname+" FROM " +TABLE_CLIENTS+" WHERE "+KEY_client_login +"='"+login+"'" ,null);
        String result="";
        while(cursor.moveToNext())
            result = cursor.getString(cursor.getColumnIndex(KEY_client_surname));
        return result;
    }

    public String getPassport(String login){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+KEY_client_passport+" FROM " +TABLE_CLIENTS+" WHERE "+KEY_client_login +"='"+login+"'" ,null);
        String result="";
        while(cursor.moveToNext())
            result = cursor.getString(cursor.getColumnIndex(KEY_client_passport));
        return result;
    }








}
