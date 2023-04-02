import java.util.*;


public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long imperfectPeople = persons.stream()
                .filter(people -> people.getAge() < 18)
                .count();

        System.out.println(imperfectPeople);

        List<String> listRecruits = persons.stream()
                .filter(people -> people.getSex() == Sex.MAN)
                .filter(man -> man.getAge() >= 18 && man.getAge() <= 27)
                .limit(10) //ограничим выаод в консоль
                .map(Person::getFamily)
                .toList();

        System.out.println(listRecruits);

        List<String> personsAbleToWork = persons.stream()
                .filter(Main::workPeople)
                .sorted(Comparator.naturalOrder())
                .map(Person::getFamily)
                .distinct()
                .toList();

        System.out.println(personsAbleToWork);
    }

    public static boolean workPeople(Person person) {
        if (person.getEducation() == Education.HIGHER) {

            if (person.getSex() == Sex.MAN && person.getAge() >= 18 && person.getAge() <= 65) {
                return true;
            } else return person.getSex() == Sex.WOMAN && person.getAge() >= 18 && person.getAge() <= 60;
        }
        return false;
    }
}
