package com.donnatto.ccigenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Office {

    @Id()
    @GeneratedValue()
    private String id;

    private String city;
    private String oldnumber;
    private String newnumber;

}
