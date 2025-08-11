1. User Service 
Tables: User, Role, Address

2. Candidate Service (Already Exists)
Tables: Candidate

3. Job Service
Purpose: Manage job postings, job questions (skills), and job applications (candidate self-rating).

Tables:

Job: id, title, description, posted_by (user_id), created_at, etc.

JobQuestion: id, job_id, question_text, skill_type (Java, MySQL, etc.), is_mandatory

JobApplication: id, candidate_id, job_id, status (applied, shortlisted, interviewed, rejected, hired), self_rating (json or mapped in child table), resume_url, created_at, etc.

JobApplicationSkillRating: id, job_application_id, skill_name, self_rating, interviewer_rating

4. Interview Service (You Need This)
Purpose: Manage interviews, schedule them, assign developers/interviewers, and store interview results.

Tables:

Interview: id, job_application_id, interviewer_id (user_id), scheduled_time, status (scheduled, completed, canceled), feedback, etc.

InterviewQuestion: id, interview_id, question, candidate_answer, interviewer_rating, skill_type
