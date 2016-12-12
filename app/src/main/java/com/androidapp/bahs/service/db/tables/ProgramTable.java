package com.androidapp.bahs.service.db.tables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class ProgramTable {

    public static final String NAME = "ProgramTable";

    public static void createTable(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + ProgramTable.NAME + '('
                + ProgramColumns._ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + ProgramColumns.id + " TEXT NOT NULL UNIQUE, "
                + ProgramColumns.program_name + " TEXT, "
                + ProgramColumns.program_description + " TEXT, "
                + ProgramColumns.program_workouts_count + " TEXT, "
                + ProgramColumns.program_duration + " TEXT, "
                + ProgramColumns.program_image_url + " TEXT, "
                + ProgramColumns.is_my_workout + " INTEGER, "
                + ProgramColumns.is_added_from_program_list_service + " INTEGER, "
                + ProgramColumns.created_date + " TIMESTAMP, "
                + ProgramColumns.modified_date + " TIMESTAMP, "
                + ProgramColumns.is_locked + " TEXT );");
    }

    public static void dropTable(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS " + ProgramTable.NAME);
    }

    public interface ProgramColumns extends BaseColumns {

        String id = "id";
        String program_name = "program_name";
        String program_description = "program_description";
        String program_workouts_count = "program_workouts_count";
        String program_duration = "program_duration";
        String program_image_url = "program_image_url";
        String is_locked = "lock_status";
        String is_my_workout = "my_workout_status";
        String created_date = "created_date";
        String modified_date = "modified_date";
        String is_added_from_program_list_service = "is_added_from_program_list_service";
    }
}
