package com.androidapp.bahs.service.ds.response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
/**
 * Project : Mobikasa Retrofit Lib
 * Author : Balwinder Singh Madaan
 * Creation Date : 26-feb-2016
 */
public class ListModel
{
    @SerializedName("worldpopulation")
    @Expose
    private List<Worldpopulation> worldpopulation = new ArrayList<Worldpopulation>();
    public List<Worldpopulation> getWorldpopulation() {
        return worldpopulation;
    }
    public void setWorldpopulation(List<Worldpopulation> worldpopulation) {
        this.worldpopulation = worldpopulation;
    }
    public class Worldpopulation
    {

        @SerializedName("rank")
        @Expose
        private Integer rank;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("population")
        @Expose
        private String population;
        @SerializedName("flag")
        @Expose
        private String flag;
        public Integer getRank() {
            return rank;
        }

        /**
         *
         * @param rank
         * The rank
         */
        public void setRank(Integer rank) {
            this.rank = rank;
        }

        /**
         *
         * @return
         * The country
         */
        public String getCountry() {
            return country;
        }

        /**
         *
         * @param country
         * The country
         */
        public void setCountry(String country) {
            this.country = country;
        }

        /**
         *
         * @return
         * The population
         */
        public String getPopulation() {
            return population;
        }

        /**
         *
         * @param population
         * The population
         */
        public void setPopulation(String population) {
            this.population = population;
        }

        /**
         *
         * @return
         * The flag
         */
        public String getFlag() {
            return flag;
        }

        /**
         *
         * @param flag
         * The flag
         */
        public void setFlag(String flag) {
            this.flag = flag;
        }

    }


    public static class CommonResponse {

        String status;
        String message;
        String redemmed_date_time;
        String total_likes;
        String code;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getRedemmed_date_time() {
            return redemmed_date_time;
        }

        public void setRedemmed_date_time(String redemmed_date_time) {
            this.redemmed_date_time = redemmed_date_time;
        }

        public String getTotal_likes() {
            return total_likes;
        }

        public void setTotal_likes(String total_likes) {
            this.total_likes = total_likes;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

    }
}
