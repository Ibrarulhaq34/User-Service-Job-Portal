package com.example.demo.service;

import com.example.demo.model.CandidateSkill;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CandidateSkillService {
    CandidateSkill saveSkill(Long userId,CandidateSkill skill);
    List<CandidateSkill> getSkillsByCandidateId(Long candidateId);
    CandidateSkill getSkillById(Long id);
    CandidateSkill updateSkill(Long id, CandidateSkill updatedSkill);
    void deleteSkill(Long id);
}
