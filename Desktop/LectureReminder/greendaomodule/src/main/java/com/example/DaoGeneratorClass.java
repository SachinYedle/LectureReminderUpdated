package com.example;
import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class DaoGeneratorClass {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1,"com.example.sachin.lecturereminder.dbModel");

        Entity classData = schema.addEntity("classData");

        classData.addIdProperty().primaryKey().autoincrement();
        classData.addStringProperty("name").notNull();
        classData.addStringProperty("topic").notNull();
        classData.addStringProperty("professor").notNull();
        classData.addDateProperty("dateTime").notNull();
        classData.addStringProperty("location").notNull();

        Entity userData = schema.addEntity("userData");
        userData.addIdProperty().primaryKey().autoincrement();
        userData.addStringProperty("name").notNull();
        userData.addStringProperty("className").notNull();
        userData.addStringProperty("email").notNull();
        userData.addStringProperty("mobile").notNull();
        userData.addStringProperty("bloodGroup").notNull();


        Property userDataIdclass = classData.addLongProperty("userDataId").notNull().getProperty();
        ToMany userDataToclasses = userData.addToMany(classData, userDataIdclass);
        userDataToclasses.setName("classes");

        new DaoGenerator().generateAll(schema,"./app/src/main/java");
    }
}
