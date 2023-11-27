package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    @XmlAttribute
    private boolean param1;
    @XmlAttribute
    private double param2;
    @XmlAttribute
    private String param3;
    private NestedObject object;
    @XmlElementWrapper(name = "array")
    @XmlElement(name = "number")
    private int[] array;

    public Person() {
    }

    public Person(boolean param1, double param2, String param3, NestedObject object, int[] array) {
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.object = object;
        this.array = array;
    }

    @Override
    public String toString() {
        return "Person{"
                + "param1=" + param1
                + ", param2=" + param2
                + ", param3=" + param3
                + ", object=" + object
                + ", array=" + Arrays.toString(array)
                + '}';
    }

    public static void main(String[] args) throws IOException, JAXBException {
        final Person person = new Person(true, 100.5, "ABC", new NestedObject("AB"),
                new int[]{1, 2, 3});
        System.out.println(person);
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Person result = (Person) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}