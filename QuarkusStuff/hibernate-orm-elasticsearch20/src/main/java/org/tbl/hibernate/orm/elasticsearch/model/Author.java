package org.tbl.hibernate.orm.elasticsearch.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.EqualsAndHashCode;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static org.hibernate.search.engine.backend.types.Sortable.YES;

@Entity
@Indexed
@EqualsAndHashCode(callSuper = false)
public class Author extends PanacheEntity {

    @FullTextField(analyzer = "name")
    @KeywordField(name = "firstName_sort", sortable = YES, normalizer = "sort")
    public String firstName;

    @FullTextField(analyzer = "name")
    @KeywordField(name = "lastName_sort", sortable = YES, normalizer = "sort")
    public String lastName;

    @OneToMany(mappedBy = "author", cascade = ALL, orphanRemoval = true, fetch = EAGER)
    @IndexedEmbedded
    public List<Book> books;

}
