import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;

public class TestPhoneContacts {
    @org.junit.jupiter.api.Test
    public void testCreateGroup() {
        // given:
        //создадим группы для теста
        String[] groups = {"Группа 1", "Группа 2", "Группа 3", "Группа 4", "Группа 5"};

        PhoneContacts phoneContacts = new PhoneContacts();

        // when:
        //заполним телефонную книгу группами
        for (String group : groups) {
            phoneContacts.addGroup(group);
        }

        // then:
        for (String group : groups) {
            Assertions.assertTrue(phoneContacts.getContacts().containsKey(group));
        }
        //ну как же без ручной проверки:
        System.out.println(phoneContacts);
    }

    @org.junit.jupiter.api.Test
    public void testAddContact() {
        // given:
        //создадим группы и контакты для теста
        String[] groups = {"Группа 1", "Группа 2", "Группа 3", "Группа 4", "Группа 5"};
        Contact[] contacts = new Contact[10];
        for (int i = 0; i < 10; i++) {
            contacts[i] = (new Contact(("Контакт " + i), ("999999999" + i)));
        }

        PhoneContacts phoneContacts = new PhoneContacts();

        // when:
        //заполним телефонную книгу группами и контактами
        for (String group : groups) {
            for (Contact contact : contacts) {
                phoneContacts.addContact(group, contact);
            }
        }

        // then:
        //проведем проверку
        int i = 0; //нарна так делать не стоит, но мне показалось что такой вариант самый простой, проходимся по мапе и по нашим массивам
        for (Map.Entry<String, List<Contact>> entry : phoneContacts.getContacts().entrySet()) {
            //по сути такая проверка была выше, на добавление групп, но пусть будет для практики
            Assertions.assertEquals(entry.getKey(), groups[i++]);
            //вывод для проверки самого себя
            System.out.println(entry.getKey());
            int j = 0;
            for (Contact contact : entry.getValue()) {
                //пробежимся по контактам в группе
                Assertions.assertEquals(contacts[j++], contact);
                //вывод для проверки самого себя
                System.out.println(contact);
            }
        }
    }
}