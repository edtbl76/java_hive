package org.tbl.hibernate.reactive.resteasy;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "known_fruits")
@NamedQuery(name = "Fruits.findAll", query = "SELECT f FROM Fruit f ORDER BY f.name")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Fruit {

    @Id
    @SequenceGenerator(name = "fruitsSequence", sequenceName = "known_fruits_id_seq", allocationSize = 1,
            initialValue = 10)
    @GeneratedValue(generator = "fruitsSequence")
    private Integer id;

    @Column(length = 40, unique = true)
    @NonNull
    private String name;


}
