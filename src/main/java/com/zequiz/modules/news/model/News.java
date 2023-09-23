package com.zequiz.modules.news.model;
import com.zequiz.shared.model.DateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "NEWS")
@Data
public class News extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;


    public News() {
    }

    public News(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
