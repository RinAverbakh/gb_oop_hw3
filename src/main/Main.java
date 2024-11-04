package main;

import model.Person;

import java.io.IOException;

import model.FamilyTree;
import service.FileOperations;
import service.FileOperationsImpl;

public class Main{
    public static void main(String[] args) {
        FamilyTree<Person> familyTree = new FamilyTree<>();

        // Создаем людей
        Person john = new Person("John", 1950);
        Person mary = new Person("Mary", 1955);
        Person susan = new Person("Susan", 1980);
        Person archi = new Person("Archibald", 1981);
        Person rin = new Person("Rin", 1930);

        // Устанавливаем родительские связи
        susan.setMother(mary);
        susan.setFather(john);
        archi.setMother(mary);
        archi.setFather(john);
        john.addChild(susan);
        mary.addChild(susan);
        john.addChild(archi);
        mary.addChild(archi);
        rin.addChild(john);
        john.setMother(rin);

        // Добавляем людей в древо
        familyTree.addPerson(john);
        familyTree.addPerson(mary);
        familyTree.addPerson(susan);
        familyTree.addPerson(rin);
        familyTree.addPerson(archi);

        // // Пример получения всех детей Джона
        // List<Person> johnsChildren = familyTree.getChildren(john);
        // for (Person child : johnsChildren) {
        // System.out.println("John's child: " + child.getName());
        // }

        // Создаем объект для работы с файлами
        FileOperations<Person> fileOps = new FileOperationsImpl<>();

        //Сортируем по имени
        System.out.println("Сортировка по имени: ");
        familyTree.sortByName();
        for(Person person:familyTree){
            System.out.println(person.getName()+"-"+
            person.getBirthYear());
        }

        //Сортируем по дате рождения
        System.out.println("\nСортировка по дате рождения: ");
        familyTree.sortByBirthYear();
        for(Person person:familyTree){
            System.out.println(person.getName()+"-"+
            person.getBirthYear());
        }

        // Сохраняем генеалогическое древо в файл
        try {
            fileOps.saveToFile(familyTree, "familyTree.dat");
            System.out.println("Family tree saved to file.");
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        // Загружаем генеалогическое древо из файла
        FamilyTree<Person> loadedFamilyTree = null;
        try {
            loadedFamilyTree = fileOps.loadFromFile("familyTree.dat");
            System.out.println("Family tree loaded from file.");
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
        
        CommandManager cm = new CommandManager(familyTree);
        cm.start();
    }
}
