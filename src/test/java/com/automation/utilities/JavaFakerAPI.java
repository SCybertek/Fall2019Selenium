package com.automation.utilities;

import com.github.javafaker.Faker;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate;

public class JavaFakerAPI {

    Faker faker = new Faker();

    public String getUserName(){
        String username = faker.name().username();
        return username;
    }

    public String getPassword(){
        String password = faker.internet().password();
        return password;
    }
}
