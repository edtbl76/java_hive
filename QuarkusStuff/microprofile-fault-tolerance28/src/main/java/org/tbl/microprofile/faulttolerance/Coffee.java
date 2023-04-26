package org.tbl.microprofile.faulttolerance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Coffee {

    private Integer id;
    private String name;
    private String countryOfOrigin;
    private Integer price;

}
