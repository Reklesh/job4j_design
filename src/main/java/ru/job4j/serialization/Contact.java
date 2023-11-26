package ru.job4j.serialization;

import java.io.*;
import java.nio.file.Files;

public record Contact(int zipCode, String phone) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Contact{"
               + "zipCode=" + zipCode
               + ", phone='" + phone + '\''
               + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        System.out.println(contact);
        File tempFile = Files.createTempFile(null, null).toFile();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempFile));
             ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempFile))) {
            oos.writeObject(contact);
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }
    }
}