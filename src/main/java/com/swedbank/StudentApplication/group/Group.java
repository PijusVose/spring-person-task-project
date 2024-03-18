package com.swedbank.StudentApplication.group;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swedbank.StudentApplication.person.Person;
import com.swedbank.StudentApplication.task.Task;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "\"group\"")
public class Group implements Serializable {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @SequenceGenerator(name = "group_generator", sequenceName = "group_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_generator")
    private long id;

    private @NonNull String name;

    private String details;

    @ManyToMany(mappedBy = "groups")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Person> persons;

    //HOME WORK
    //@OneToMany(mappedBy="group", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    //@JsonIgnore
    //@ToString.Exclude
    //@EqualsAndHashCode.Exclude
    //private Set<Task> tasks;

    public Group(@NonNull String name, String details, Set<Person> persons) {
        super();
        this.name = name;
        this.details = details;
        this.persons = persons;
    }
}
