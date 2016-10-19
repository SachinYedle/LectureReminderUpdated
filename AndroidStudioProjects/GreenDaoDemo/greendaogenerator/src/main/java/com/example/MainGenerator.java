package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;


public class MainGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1,"com.example.admin1.greendaodemo.db");
        Entity entity = schema.addEntity("student");
        entity.addIdProperty().primaryKey().autoincrement();
        entity.addStringProperty("rollno").notNull();
        entity.addStringProperty("name").notNull();

        new DaoGenerator().generateAll(schema,"./app/src/main/java");
    }
}
