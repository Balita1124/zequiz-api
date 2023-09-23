package com.zequiz.modules.news.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zequiz.shared.model.DateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CATEGORIES")
@Data
public class Category extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private String code;

    @NotBlank
    private String label;

    private String description;

    private String website;

//    @OneToMany(targetEntity = Challenge.class ,  fetch = FetchType.LAZY)
//    @JoinColumn(name="category_challenge_id")
//    private List<Challenge> challengeList;

    @OneToMany(mappedBy="category")
    @JsonManagedReference(value = "challenges")
    private List<Challenge> challenges;

    public Category() {
    }

    public Category(String code, String label, String description, String website) {
        this.code = code;
        this.label = label;
        this.description = description;
        this.website = website;
    }
}
