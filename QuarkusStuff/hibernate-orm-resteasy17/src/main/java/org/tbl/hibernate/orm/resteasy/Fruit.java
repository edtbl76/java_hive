package org.tbl.hibernate.orm.resteasy;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "known_fruits")
@NamedQuery(name = "Fruits.findAll", query = "SELECT f from Fruit f ORDER BY f.name",
        hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Fruit {

    @Id
    @SequenceGenerator(name = "fruitsSequence", sequenceName = "known_fruits_id_seq",
            allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "fruitsSequence3")
    private Integer id;

    @NonNull
    @Column(length = 40, unique = true)
    private String name;
}
