package com.androidapp.bahs.service.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidapp.bahs.service.db.tables.ProgramTable;
import com.androidapp.bahs.service.ds.ProgramBean;
import com.androidapp.bahs.service.utils.Constants;
import com.androidapp.bahs.service.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;


public class ProgramDAO extends AbsDAO<ProgramBean> {


    public ProgramDAO(SQLiteDatabase database) {
        super(database);
    }

    @Override
    public void delete(String id) {
        String whereClause = ProgramTable.ProgramColumns.id + " = " + id;
        db.delete(ProgramTable.NAME, whereClause, null);
    }

    @Override
    public void delete() {
        db.delete(ProgramTable.NAME, null, null);
    }

    @Override
    public Cursor getCursor() {
        Cursor cursor = db.query(ProgramTable.NAME, null, null, null, null, null, ProgramTable.ProgramColumns.created_date + " desc");
        cursor.moveToFirst();
        return cursor;
    }

    @Override
    public String getMinCreatedDate() {
        String query = "SELECT MIN(" + ProgramTable.ProgramColumns.created_date + ") from " + ProgramTable.NAME + " where " + ProgramTable.ProgramColumns.is_added_from_program_list_service + " =" + Constants.SERVICE_TAGS.PROGRAM_LIST_SERVICE;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public String getMinCreatedDateMyWorkout() {
        String query = "SELECT MIN(" + ProgramTable.ProgramColumns.created_date + ") from " + ProgramTable.NAME + " where " + ProgramTable.ProgramColumns.is_added_from_program_list_service + " =" + Constants.SERVICE_TAGS.MYWORKOUT_PROGRAMS_LIST_SERVICE;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    @Override
    public String getMaxModifiedDate() {
        return null;
    }

    @Override
    public Cursor getCursor(String id) {
        String whereClause = ProgramTable.ProgramColumns.id + " = " + id;
        Cursor cursor = db.query(ProgramTable.NAME, null, whereClause, null, null, null, null);
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor getCursorForMyWorkouts() {
        String whereClause = ProgramTable.ProgramColumns.is_my_workout + " = " + 1;
        Cursor cursor = db.query(ProgramTable.NAME, null, whereClause, null, null, null, null);
        cursor.moveToFirst();
        return cursor;
    }

    @Override
    public Cursor getCursor(String id, String id2) {
        return null;
    }

    @Override
    public int getSize() {
        return getCursor().getCount();
    }

    @Override
    public List<ProgramBean> getList() {
        Cursor cursor = getCursor();
        return createList(cursor);
    }

    @Override
    public List<ProgramBean> createList(Cursor cursor) {
        List<ProgramBean> list = new ArrayList<ProgramBean>();
        LogUtils.debug(TAG, "cursor size = " + cursor.getCount());
        for (int i = 0; i < cursor.getCount(); i++) {
            list.add(createObject(cursor));
            cursor.moveToNext();
        }
        return list;
    }

    @Override
    public ProgramBean createObject(Cursor cursor) {
        ProgramBean data = new ProgramBean();
        if (cursor != null && cursor.getCount() > 0) {
            data.setmProgramId(cursor.getString(cursor.getColumnIndex(ProgramTable.ProgramColumns.id)));
            data.setmProgramDescription(cursor.getString(cursor.getColumnIndex(ProgramTable.ProgramColumns.program_description)));
            data.setmProgramImageUrl(cursor.getString(cursor.getColumnIndex(ProgramTable.ProgramColumns.program_image_url)));
            data.setmProgramName(cursor.getString(cursor.getColumnIndex(ProgramTable.ProgramColumns.program_name)));
            data.setmProgramStatus(cursor.getInt(cursor.getColumnIndex(ProgramTable.ProgramColumns.is_locked)));
            data.setIs_my_workout(cursor.getInt(cursor.getColumnIndex(ProgramTable.ProgramColumns.is_my_workout)));
            data.setmProgramsWorkDuration(cursor.getInt(cursor.getColumnIndex(ProgramTable.ProgramColumns.program_duration)));
            data.setmProgramWorkoutCount(cursor.getInt(cursor.getColumnIndex(ProgramTable.ProgramColumns.program_workouts_count)));
            data.setCreated_at(cursor.getString(cursor.getColumnIndex(ProgramTable.ProgramColumns.created_date)));
            data.setUpdated_at(cursor.getString(cursor.getColumnIndex(ProgramTable.ProgramColumns.modified_date)));
            data.setIs_added_from_program_list_service(cursor.getInt(cursor.getColumnIndex(ProgramTable.ProgramColumns.is_added_from_program_list_service)));
        }
        return data;
    }


    @Override
    public long insert(ProgramBean data, int serviceTag) {
        try {
            LogUtils.debug(TAG, "insert data = " + data);

            ContentValues cv = new ContentValues();
            cv.put(ProgramTable.ProgramColumns.id, data.getmProgramId());
            cv.put(ProgramTable.ProgramColumns.is_locked, data.ismProgramStatus());
            cv.put(ProgramTable.ProgramColumns.is_my_workout, data.getIs_my_workout());
            cv.put(ProgramTable.ProgramColumns.program_description, data.getmProgramDescription());
            cv.put(ProgramTable.ProgramColumns.program_duration, data.getmProgramsWorkDuration());
            cv.put(ProgramTable.ProgramColumns.program_image_url, data.getmProgramImageUrl());
            cv.put(ProgramTable.ProgramColumns.program_name, data.getmProgramName());
            cv.put(ProgramTable.ProgramColumns.program_workouts_count, data.getmProgramWorkoutCount());
            cv.put(ProgramTable.ProgramColumns.created_date, "" + data.getCreated_at());
            cv.put(ProgramTable.ProgramColumns.modified_date, "" + data.getUpdated_at());
            cv.put(ProgramTable.ProgramColumns.is_added_from_program_list_service, serviceTag);

            long result = db.insert(ProgramTable.NAME, null, cv);
            LogUtils.debug(TAG, "insert result = " + result);

            return result;
        } catch (Exception er) {
            LogUtils.error(TAG, "insert error = " + er.getMessage());
            return -1;
        }
    }

    @Override
    public void insertBulk(List<ProgramBean> list, int serviceTag) {
        for (int i = 0; i < list.size(); i++) {
            ProgramBean bean = list.get(i);
            insert(bean, serviceTag);
        }
    }

    @Override
    public void insertOrUpdate(ProgramBean data, int serviceTag) {
        if (getCursor(data.getmProgramId()).getCount() > 0) {

            ContentValues cv = new ContentValues();
            cv.put(ProgramTable.ProgramColumns.id, data.getmProgramId());
            cv.put(ProgramTable.ProgramColumns.is_locked, data.ismProgramStatus());
            cv.put(ProgramTable.ProgramColumns.is_my_workout, data.getIs_my_workout());
            cv.put(ProgramTable.ProgramColumns.program_description, data.getmProgramDescription());
            cv.put(ProgramTable.ProgramColumns.program_duration, data.getmProgramsWorkDuration());
            cv.put(ProgramTable.ProgramColumns.program_image_url, data.getmProgramImageUrl());
            cv.put(ProgramTable.ProgramColumns.program_name, data.getmProgramName());
            cv.put(ProgramTable.ProgramColumns.program_workouts_count, data.getmProgramWorkoutCount());
            cv.put(ProgramTable.ProgramColumns.created_date, "" + data.getCreated_at());
            cv.put(ProgramTable.ProgramColumns.modified_date, "" + data.getUpdated_at());
            cv.put(ProgramTable.ProgramColumns.is_added_from_program_list_service, serviceTag);

            String whereClause = ProgramTable.ProgramColumns.id + " = " + data.getmProgramId();
            long result = db.update(ProgramTable.NAME, cv, whereClause, null);
            LogUtils.debug(TAG, "update result = " + result);

        } else {
            insert(data, serviceTag);
        }
    }
}
