package com.androidapp.bahs.service.ds;

import java.util.ArrayList;

public class ProgramBean extends AbsBindObject {

    private String image_path;
    private String program_name;
    private int total_no_of_workouts = 0;
    private String id;
    private int total_no_of_weeks = 0;
    private String description;
    private int is_active = 0;
    private int is_added_from_program_list_service = 1;
    private int is_my_workout = 0;
    private String created_at;
    private String updated_at;

    public int getIs_added_from_program_list_service() {
        return is_added_from_program_list_service;
    }

    public void setIs_added_from_program_list_service(int is_added_from_program_list_service) {
        this.is_added_from_program_list_service = is_added_from_program_list_service;
    }

    public int getIs_my_workout() {
        return is_my_workout;
    }

    public void setIs_my_workout(int is_my_workout) {
        this.is_my_workout = is_my_workout;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getmProgramImageUrl() {
        return image_path;
    }

    public void setmProgramImageUrl(String mProgramImageUrl) {
        this.image_path = mProgramImageUrl;
    }

    public String getmProgramName() {
        return program_name;
    }

    public void setmProgramName(String mProgramName) {
        this.program_name = mProgramName;
    }

    public int getmProgramWorkoutCount() {
        return total_no_of_workouts;
    }

    public void setmProgramWorkoutCount(int mProgramWorkoutCount) {
        this.total_no_of_workouts = mProgramWorkoutCount;
    }

    public String getmProgramId() {
        return id;
    }

    public void setmProgramId(String mProgramId) {
        this.id = mProgramId;
    }

    public int getmProgramsWorkDuration() {
        return total_no_of_weeks;
    }

    public void setmProgramsWorkDuration(int mProgramsWorkDuration) {
        this.total_no_of_weeks = mProgramsWorkDuration;
    }

    public String getmProgramDescription() {
        return description;
    }

    public void setmProgramDescription(String mProgramDescription) {
        this.description = mProgramDescription;
    }

    public int ismProgramStatus() {
        return is_active;
    }

    public void setmProgramStatus(int mProgramStatus) {
        this.is_active = mProgramStatus;
    }

}
