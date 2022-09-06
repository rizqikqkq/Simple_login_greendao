package com.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyGenerator {
    public static void main(String[] args) {
        Schema schema = new Schema(
                1,
                "com.rnpp.greendao_example.db"); // Your app package name and the (.db) is the folder where the DAO files will be generated into.
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        addUserEnt(schema);
        addUserInfoEnt(schema);
        addUserLoginHistoryEnt(schema);
    }

    private static void addDefaultEnt(Entity parm){
        parm.addStringProperty("data_sync_status").notNull();
        parm.addStringProperty("data_sync_date").notNull();
        parm.addStringProperty("update_at").notNull();
        parm.addStringProperty("create_at").notNull();
        parm.addStringProperty("create_by").notNull();
        parm.addStringProperty("update_by").notNull();
    }

    // This is use to describe the colums of your table

    private static Entity addUserEnt(final Schema schema) {
        Entity params = schema.addEntity("ms_user");
        addDefaultEnt(params);
        params.addIdProperty().primaryKey().autoincrement();
        params.addStringProperty("uid").notNull();
        params.addStringProperty("email").notNull();
        params.addStringProperty("password").notNull();
        params.addStringProperty("name_first").notNull();
        params.addStringProperty("name_last").notNull();
        return params;
    }private static Entity addUserInfoEnt(final Schema schema) {
        Entity params = schema.addEntity("ms_userInfo");
        addDefaultEnt(params);
        params.addIdProperty().primaryKey().autoincrement();
        params.addStringProperty("uid").notNull();
        params.addStringProperty("email").notNull();
        params.addStringProperty("name_first").notNull();
        params.addStringProperty("name_last").notNull();
        params.addStringProperty("name_middle").notNull();
        params.addStringProperty("birth_date").notNull();
        params.addStringProperty("gender").notNull();
        return params;
    }

    private static Entity addUserLoginHistoryEnt(final Schema schema) {
        Entity params = schema.addEntity("ms_userLoginHistory");
        addDefaultEnt(params);
        params.addIdProperty().primaryKey().autoincrement();
        params.addStringProperty("email").notNull();
        params.addStringProperty("action_id").notNull();
        params.addStringProperty("uid").notNull();
        params.addStringProperty("mac").notNull();
        return params;
    }
}
