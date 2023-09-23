package com.zequiz.modules.news.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zequiz.shared.model.DateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CHALLENGES")
@Data
public class Challenge extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    @Column(columnDefinition="TEXT")
    private String question;

    @NotBlank
    @Column(columnDefinition="TEXT")
    private String answer;

    @JsonBackReference(value = "category")
    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName = "id", nullable=false)
    private Category category;

    private Integer level;

    public Challenge() {
    }

    public Challenge(String question, String answer, Category category, Integer level) {
        this.question = question;
        this.answer = answer;
        this.category = category;
        this.level = level;
    }
}
