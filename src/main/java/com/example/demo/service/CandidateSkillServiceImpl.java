package com.example.demo.service;

import com.example.demo.model.CandidateSkill;

import com.example.demo.repository.CandidateSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateSkillServiceImpl implements CandidateSkillService {

    @Autowired
    private CandidateSkillRepository repository;

    @Override
    public CandidateSkill saveSkill(Long userId,CandidateSkill skill) {
        return repository.save(skill);
    }

    @Override
    public List<CandidateSkill> getSkillsByCandidateId(Long candidateId) {
        return repository.findByCandidateId(candidateId);
    }

    @Override
    public CandidateSkill getSkillById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with id " + id));
    }

    @Override
    public CandidateSkill updateSkill(Long id, CandidateSkill updatedSkill) {
        CandidateSkill skill = getSkillById(id);
        skill.setSkillName(updatedSkill.getSkillName());
        skill.setRating(updatedSkill.getRating());
        return repository.save(skill);
    }

    @Override
    public void deleteSkill(Long id) {
        repository.deleteById(id);
    }
}
