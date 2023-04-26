package org.tbl.hibernate.orm.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.EqualsAndHashCode;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Indexed
@EqualsAndHashCode(callSuper = false)
public class Book extends PanacheEntity {

    @FullTextField(analyzer = "english")
    public String title;

    @ManyToOne
    @JsonIgnore
    public Author author;
}
