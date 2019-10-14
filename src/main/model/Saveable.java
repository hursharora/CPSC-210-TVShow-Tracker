package model;

import model.exceptions.InvalidContentTypeException;

import java.io.IOException;
import java.util.List;

public interface Saveable {
    public void save(String type) throws IOException, InvalidContentTypeException;
}
