package com.axway.academy.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "title")
    String title;

    public Project() {
    }

    public Project(String title) {
        this.title = title;
    }

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "projects")
    private Set<Student> employees = new HashSet<Student>();

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
