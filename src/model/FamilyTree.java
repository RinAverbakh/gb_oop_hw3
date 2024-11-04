package model;

import java.io.Serializable;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;

public class FamilyTree<P> implements Serializable, Iterable<P>{
    private List<P> objects;
    private static final long serialVersionUID = 1L;

    public FamilyTree() {
        this.objects = new ArrayList<>();
    }

    public void addPerson(P person) {
        this.objects.add(person);
    }

    // public List<Person> getChildren(Person parent) {
    //     return parent.getChildren();
    // }

    // public P findPersonByName(String name) {
    //     for (P person : objects) {
    //         if (person.getName().equals(name)) {
    //             return person;
    //         }
    //     }
    //     return null;
    // }

    public List<P> getPeople() {
        return objects;
    }

    @Override
    public Iterator<P> iterator() {
        return objects.iterator();
    }

    public void sortByName(){
        Collections.sort(objects, (p1, p2) -> p1.toString().compareTo(p2.toString()));
    }
    
    public void sortByBirthYear(){
        if(objects.get(0)instanceof Person){
            Collections.sort(objects,(p1,p2) -> Integer.compare(((Person)p1).getBirthYear(),((Person) p2).getBirthYear()));
        }
    }
}
