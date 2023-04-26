package org.tbl.sns.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@NoArgsConstructor
@RegisterForReflection
public class Quark {

    @Getter @Setter
    private String flavor;

    @Getter @Setter
    private String spin;
}
