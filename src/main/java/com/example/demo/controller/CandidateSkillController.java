package com.example.demo.controller;

import com.example.demo.model.CandidateSkill;
import com.example.demo.service.CandidateSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate-skills")
public class CandidateSkillController {

    @Autowired
    private CandidateSkillService skillService;

    @PostMapping("/candidates/{candidateId}/skills")
    public ResponseEntity<?> createSkill(
            @PathVariable Long candidateId,
            @RequestBody CandidateSkill skill
    ) {
        try {
            CandidateSkill savedSkill = skillService.saveSkill(candidateId, skill);
            return ResponseEntity.ok(savedSkill);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add skill: " + e.getMessage());
        }
    }



    @GetMapping("/candidate/{candidateId}")
    public List<CandidateSkill> getSkillsByCandidate(@PathVariable Long candidateId) {
        return skillService.getSkillsByCandidateId(candidateId);
    }

    @GetMapping("/{id}")
    public CandidateSkill getSkillById(@PathVariable Long id) {
        return skillService.getSkillById(id);
    }

    @PutMapping("/{id}")
    public CandidateSkill updateSkill(@PathVariable Long id, @RequestBody CandidateSkill updatedSkill) {
        return skillService.updateSkill(id, updatedSkill);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
    }
}

