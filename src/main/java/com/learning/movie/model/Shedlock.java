package com.learning.movie.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "shedlock")
public class Shedlock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lock_until")
    private Date lockUntil;

    @Column(name = "locked_at")
    private Date lockedAt;

    @Column(name = "locked_by")
    private String lockedBy;

}
