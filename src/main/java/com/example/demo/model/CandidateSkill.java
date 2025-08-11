package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "candidate_skills")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @Column(name = "skill_name", nullable = false)
    private String skillName;

    @Column(name = "rating")
    private int rating;

    public Long getId() {
        return id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public String getSkillName() {
        return skillName;
    }

    public int getRating() {
        return rating;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
