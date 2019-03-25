package com.makarov.fa.resourses;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AreaResource implements Resource {

    private Long id;

    private String name;
}
