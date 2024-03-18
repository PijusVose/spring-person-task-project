package com.swedbank.StudentApplication.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swedbank.StudentApplication.group.Group;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@NoArgsConstructor
@Table(name = "task")
@Data
public class Task {


    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @SequenceGenerator(name = "task_generator", sequenceName = "task_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_generator")
    private long id;


    @NotNull
    @NotBlank
    @Column(name = "short_desc")
    private String shortDesc;


    private String details;


    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Europe/Vilnius")
    @Column(name = "start_date")
    private Date startDate;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Europe/Vilnius")
    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = true)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Group group;

    /**
     * Instantiates a new task.
     *
     * @param shortDesc the short desc
     * @param details   the details
     * @param startDate the start date
     * @param endDate   the end date
     */
    public Task(String shortDesc, String details, Date startDate, Date endDate) {
        super();
        this.shortDesc = shortDesc;
        this.details = details;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
