package com.swedbank.StudentApplication.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swedbank.StudentApplication.group.Group;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "person")
public class Person implements Serializable {

    /**
     * The pid.
     */
    @Id
    @NotNull
    private long pid;

    /**
     * The name.
     */
    @NotNull
    @NotBlank
    @Column(name = "first_name")
    private String name;

    /**
     * The middle name.
     */
    @Column(name = "middle_name")
    private String middleName;

    /**
     * The surname.
     */
    @NotNull
    @NotBlank
    @Column(name = "last_name")
    private String surname;

    /**
     * The email.
     */
    private String email;

    /**
     * The phone.
     */
    private String phone;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinTable(name = "person_group", joinColumns = @JoinColumn(name = "pid", referencedColumnName = "pid"), inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<Group> groups;

}