package model;

import model.exceptions.InvalidContentTypeException;

import java.io.IOException;

public interface Loadable {
    void load(String ld) throws IOException, InvalidContentTypeException;
}
