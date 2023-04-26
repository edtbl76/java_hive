package org.tbl.hibernate.orm.panache;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Cacheable
@NoArgsConstructor
@AllArgsConstructor
public class Fruit extends PanacheEntity {

    @Column(length = 40, unique = true)
    public String name;
}
