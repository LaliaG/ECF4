-- Populates the Candidate table
INSERT INTO user (candidatename, email, resume, password) VALUES
('John Doe', 'john.doe@example.com', 'path/to/resume1.pdf', 'Password@123'),

('Jane Smith', 'jane.smith@example.com', 'path/to/resume2.pdf', 'Password@123');

-- Populates the JobOffer table
INSERT INTO job_offer (title, description, employeeHr, createdAt, status) VALUES

('Java Developer', 'Experienced Java Developer', 'HR Person 1', NOW(), 'PUBLISHED'),
('DevOps Engineer', 'DevOps Engineer for Large Company', 'HR Person 2', NOW(), 'PUBLISHED');

--People the table Interview
INSERT INTO interview (date, feedback, nameRecruiter, candidate_id, job_offer_id) VALUES
(NOW(), 'POSITIVE', 'Recruiter 1', 1, 1),
(NOW(), 'NEGATIVE', 'Recruiter 2', 2, 2);
