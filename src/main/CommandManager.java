package main;

import model.FamilyTree;
import model.Person;
import service.FileOperations;
import service.FileOperationsImpl;

import java.io.IOException;
import java.util.Scanner;

public class CommandManager {
    private FamilyTree<Person> familyTree;
    private Scanner scanner;
    private FileOperations<Person> fileOps;

    public CommandManager(FamilyTree<Person> familyTree) {
        this.familyTree = familyTree;
        this.scanner = new Scanner(System.in);
        this.fileOps = new FileOperationsImpl<>();
    }

    public void start() {
        while (true) {
            System.out.println("Введите команду (add, list, sortByName, sortByBirthYear, save, load, exit):");
            String command = scanner.nextLine();

            switch (command) {

                case "add":
                    addPerson();
                    break;

                case "list":
                    listPeople();
                    break;

                case "sortByName":
                    familyTree.sortByName();
                    listPeople();
                    break;

                case "sortByBirthYear":
                    familyTree.sortByBirthYear();
                    listPeople();
                    break;

                case "save":
                    save();
                    break;

                case "load":
                    load();
                    break;

                case "exit":
                    return;
                default:
                    System.out.println("Неизвестная команда");
            }
        }
    }

    private void addPerson() {
        System.out.println("Введите имя:");
        String name = scanner.nextLine();

        System.out.println("Введите год рождения:");
        int birthYear = Integer.parseInt(scanner.nextLine());

        Person person = new Person(name, birthYear);
        familyTree.addPerson(person);
        System.out.println("Человек добавлен в дерево.");
    }

    private void listPeople() {
        for (Person person : familyTree) {
            System.out.println(person.getName() + ", родился(ась) в " +
                    person.getBirthYear());
        }
    }

    private void save(){
        try {
            fileOps.saveToFile(familyTree, "familyTree.dat");
            System.out.println("Family tree saved to file.");
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load(){
        // Загружаем генеалогическое древо из файла
        FamilyTree<Person> loadedFamilyTree = null;
        try {
            loadedFamilyTree = fileOps.loadFromFile("familyTree.dat");
            System.out.println("Дерево сохраанено в файл");
        } 
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Проверяем, что древо загрузилось правильно
        if (loadedFamilyTree != null) {
            for (Person person : loadedFamilyTree) {
                System.out.println("Loaded person: " +
                        person.getName() + ", born in " + person.getBirthYear());
            }
        }    
    }
}
