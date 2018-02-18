package co.borucki.SiennBlogPostIt.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "author")
public class BlogAuthor {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private long id;
    @Size(max = 50, min = 5, message = "Minimal length 5 signs, and maximal 50 signs")
    @NotNull
    @Column(name = "mail", unique = true)
    private String mail;
    @Size(min = 3, max = 20, message = "Minimal length 3 signs, maximal 20 signs")
    @NotNull
    @Column(name = "name")
    private String name;
    @Size(min = 3, max = 30, message = "Minimal length 3 signs, maximal 20 signs")
    @Column(name = "surname")
    private String surname;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
