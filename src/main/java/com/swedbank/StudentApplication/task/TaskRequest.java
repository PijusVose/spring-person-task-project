package com.swedbank.StudentApplication.task;

import lombok.Data;

import java.util.Date;


@Data
public class TaskRequest
{
    private String shortDesc;
    private String details;
    private Date startDate;
    private Date endDate;
}
