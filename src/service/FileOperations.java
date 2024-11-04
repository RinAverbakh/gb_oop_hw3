package service;

import model.FamilyTree;

import java.io.*;

public interface FileOperations<P> {
    void saveToFile(FamilyTree<P> familyTree, String fileName) throws IOException;

    FamilyTree<P> loadFromFile(String fileName) throws IOException, ClassNotFoundException;
}
