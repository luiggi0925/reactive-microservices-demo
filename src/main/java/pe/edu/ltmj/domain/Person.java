package pe.edu.ltmj.domain;

import java.util.Date;

public class Person {

    private Long id;
    private String name;
    private Date birthday;

    public Person() {
        
    }

    public Person(Person another) {
        this.id = another.id;
        this.name = another.name;
        this.birthday = another.birthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
