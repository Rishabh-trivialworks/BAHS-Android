package com.androidapp.bahs.utils;

import com.androidapp.bahs.service.db.DatabaseHelper;
import com.androidapp.bahs.service.db.dao.ProgramDAO;
import com.androidapp.bahs.service.ds.ProgramBean;

/**
 * Created by cpu505 on 30/9/15.
 */
public class TableUtils {

    private ProgramDAO mProgramDao;


    public TableUtils() {
       // mProgramDao = new ProgramDAO(DatabaseHelper.getDatabase());
    }

    public ProgramBean getProgramBean(String programId) {
        return mProgramDao.createObject(mProgramDao.getCursor(programId));
    }

    public boolean programAddedToMyWorkoutCheck(String programId) {
        ProgramBean bean = getProgramBean(programId);
        if (bean.getIs_my_workout() == 1) {
            return true;
        }
        return false;
    }


    public void addProgramToMyWorkout(String programId) {
        ProgramBean bean = getProgramBean(programId);
        bean.setIs_my_workout(1);
        mProgramDao.insertOrUpdate(bean, bean.getIs_added_from_program_list_service());
    }

    public void removeProgramFromMyWorkout(String programId) {
        ProgramBean bean = getProgramBean(programId);
        bean.setIs_my_workout(0);
        mProgramDao.insertOrUpdate(bean, bean.getIs_added_from_program_list_service());
    }
}
