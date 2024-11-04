package service;

import model.FamilyTree;

import java.io.*;

public class FileOperationsImpl<P> implements FileOperations<P> {
    @Override
    public void saveToFile(FamilyTree<P> familyTree, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(familyTree);
        }
    }

    @Override
    public FamilyTree<P> loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (FamilyTree<P>) ois.readObject();
        }
    }
}
