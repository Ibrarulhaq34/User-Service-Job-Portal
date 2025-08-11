package com.example.demo.service;

import com.example.demo.model.Candidate;

import java.util.List;

public interface CandidateService {
    Candidate createCandidate(Candidate candidate);
    Candidate getCandidateById(Long id);
    List<Candidate> getAllCandidates();
}
