package com.example.demo.repository;

import com.example.demo.model.CandidateSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Long> {
    List<CandidateSkill> findByCandidateId(Long candidateId);
}
