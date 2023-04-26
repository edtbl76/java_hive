package org.tbl.hibernate.reactive.vertx;

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

@Data
@Entity
@Table(name = "known_fruits")
@NamedQuery(name = Fruit.FIND_ALL, query = "SELECT f from Fruit f ORDER BY f.name")
@RequiredArgsConstructor
@NoArgsConstructor
public class Fruit {

    public static final String FIND_ALL = "Fruits.findAll";

    @Id
    @SequenceGenerator(name = "fruitsSequence", sequenceName = "known_Fruits_id_seq",
            allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "fruitsSequence")
    private Integer id;

    @Column(length = 40, unique = true)
    @NonNull
    private String name;

}
