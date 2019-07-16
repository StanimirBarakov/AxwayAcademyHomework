package com.axway.academy;

import com.axway.academy.entities.Project;
import com.axway.academy.entities.Student;
import com.axway.academy.manager.StudentManager;

import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main(String[] args) {

        StudentManager manager = new StudentManager();

        String [] names = manager.listStudentsWithProjectsMoreThan(1);

        for (int i = 0; i < names.length ; i++) {
            System.out.println(names[i]);
        }

    }

}
