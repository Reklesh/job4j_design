package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "object")
public class NestedObject {
    @XmlAttribute
    private String param;

    public NestedObject() {
    }

    public NestedObject(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "NestedObject{"
                + "param='" + param + '\''
                + '}';
    }
}
