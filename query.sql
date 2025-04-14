CREATE DATABASE teclms;

USE teclms;

-- User table (base table for all user types)
CREATE TABLE `user` (
                        `User_Id` varchar(10) NOT NULL,
                        `First_Name` varchar(20) NOT NULL,
                        `Last_Name` varchar(20) NOT NULL,
                        `DOB` date NOT NULL,
                        `Telephone` varchar(15) NOT NULL,
                        `Address` varchar(50) NOT NULL,
                        `Email` varchar(70) NOT NULL,
                        `Password` varchar(150) NOT NULL,
                        `Age` int NOT NULL,
                        PRIMARY KEY (`User_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Admin table (extends user)
CREATE TABLE `admin` (
                         `Admin_Id` varchar(10) NOT NULL,
                         PRIMARY KEY (`Admin_Id`),
                         CONSTRAINT `fk_admin_user` FOREIGN KEY (`Admin_Id`) REFERENCES `user` (`User_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Lecturer table (extends user)
CREATE TABLE `lecturer` (
                            `Lecturer_Id` varchar(10) NOT NULL,
                            `Department` enum('ICT','BST','ET') NOT NULL,
                            `Enrollment_Date` date NOT NULL,
                            `Position` varchar(50) NOT NULL,
                            PRIMARY KEY (`Lecturer_Id`),
                            CONSTRAINT `fk_lecturer_user` FOREIGN KEY (`Lecturer_Id`) REFERENCES `user` (`User_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Student table (extends user)
CREATE TABLE `student` (
                           `Student_Id` varchar(10) NOT NULL,
                           `Level` varchar(20) NOT NULL,
                           `Department` enum('ICT','BST','ET') NOT NULL,
                           `Academic_Status` varchar(20) NOT NULL,
                           PRIMARY KEY (`Student_Id`),
                           CONSTRAINT `fk_student_user` FOREIGN KEY (`Student_Id`) REFERENCES `user` (`User_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Technical Officer table (extends user)
CREATE TABLE `technical_officer` (
                                     `Technical_Id` varchar(10) NOT NULL,
                                     `Enrollment_Date` date NOT NULL,
                                     PRIMARY KEY (`Technical_Id`),
                                     CONSTRAINT `fk_technicalOfficer_user` FOREIGN KEY (`Technical_Id`) REFERENCES `user` (`User_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Attendance table
CREATE TABLE `attendance` (
                              `AttendanceRecord_Id` varchar(10) NOT NULL,
                              `Session_No` int NOT NULL,
                              `Medical_Status` enum('MC','None') NOT NULL, -- Fixed: Added 'None' option
                              `Date` date NOT NULL,
                              `Type` enum('Absent','Present') NOT NULL,
                              PRIMARY KEY (`AttendanceRecord_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Course table
CREATE TABLE `course` (
                          `Course_Id` varchar(10) NOT NULL,
                          `Course_Name` varchar(50) NOT NULL,
                          `Credit_Status` enum('Credit','Non-Credit') NOT NULL,
                          `Credits` int NOT NULL,
                          `Type` enum('GPA','Non-GPA') NOT NULL,
                          PRIMARY KEY (`Course_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Mark table
CREATE TABLE `mark` (
                        `MarkRecord_Id` varchar(10) NOT NULL,
                        `Quiz_01` float NOT NULL,
                        `Quiz_02` float NOT NULL,
                        `Quiz_03` float NOT NULL,
                        `Assignment` float NOT NULL,
                        `Mid_Theory` float NOT NULL,
                        `Mid_Practical` float NOT NULL,
                        `Final_Theory` float NOT NULL,
                        `Final_Practical` float NOT NULL,
                        `Grade` char(4) NOT NULL,
                        PRIMARY KEY (`MarkRecord_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Medical record table
CREATE TABLE `medical` (
                           `MedicalRecord_Id` varchar(10) NOT NULL,
                           `Approval_Status` enum('Approved','Unapproved') NOT NULL,
                           `Submission_Date` date NOT NULL,
                           PRIMARY KEY (`MedicalRecord_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Notice table
CREATE TABLE `notice` (
                          `Notice_Id` varchar(10) NOT NULL,
                          `Title` varchar(20) NOT NULL,
                          `Description` varchar(100) NOT NULL,
                          `Date_Posted` date NOT NULL,
                          PRIMARY KEY (`Notice_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Student-Course junction table
CREATE TABLE `stu_course` (
                              `Student_Id` varchar(10) NOT NULL,
                              `Course_Id` varchar(10) NOT NULL,
                              PRIMARY KEY (`Student_Id`,`Course_Id`),
                              KEY `Course_Id_idx` (`Course_Id`),
                              CONSTRAINT `fk_stucourse_course` FOREIGN KEY (`Course_Id`) REFERENCES `course` (`Course_Id`),
                              CONSTRAINT `fk_stucourse_student` FOREIGN KEY (`Student_Id`) REFERENCES `student` (`Student_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Technical Officer-Attendance junction table
CREATE TABLE `techofficer_attendance` (
                                          `Technical_Id` varchar(10) NOT NULL,
                                          `AttendanceRecord_Id` varchar(10) NOT NULL,
                                          PRIMARY KEY (`Technical_Id`,`AttendanceRecord_Id`),
                                          KEY `AttendanceRecord_Id_idx` (`AttendanceRecord_Id`),
                                          CONSTRAINT `fk_techofficerattendance_attendance` FOREIGN KEY (`AttendanceRecord_Id`) REFERENCES `attendance` (`AttendanceRecord_Id`),
                                          CONSTRAINT `fk_techofficerattendance_technical` FOREIGN KEY (`Technical_Id`) REFERENCES `technical_officer` (`Technical_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Technical Officer-Medical junction table
CREATE TABLE `techofficer_medical` (
                                       `Technical_Id` varchar(10) NOT NULL,
                                       `MedicalRecord_Id` varchar(10) NOT NULL,
                                       PRIMARY KEY (`Technical_Id`,`MedicalRecord_Id`),
                                       KEY `MedicalRecord_Id_idx` (`MedicalRecord_Id`),
                                       CONSTRAINT `fk_techofficermedical_medical` FOREIGN KEY (`MedicalRecord_Id`) REFERENCES `medical` (`MedicalRecord_Id`),
                                       CONSTRAINT `fk_techofficermedical_technical` FOREIGN KEY (`Technical_Id`) REFERENCES `technical_officer` (`Technical_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Time Table
CREATE TABLE `time_table` (
                              `TimeTable_Id` varchar(10) NOT NULL,
                              `Date_Posted` date NOT NULL,
                              `Department` enum('ICT','BST','ET') NOT NULL,
                              PRIMARY KEY (`TimeTable_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Insert users with bcrypt hashed passwords (cost factor 12)
INSERT INTO `user` VALUES
-- Admin (password: SecureAdmin@123)
('AD001', 'Admin', 'User', '1980-01-15', '0770000001', '123 Admin St, Colombo', 'admin@tec.edu', '$2a$12$/1ibXyQ95Zhg6PuXWD5IrOIkgjPdP9/NXdMniuhZBL2aGX0XJmO26', 43),

-- Lecturers
-- Lecturer 1 (password: ICTLecturer#2023)
('LEC01', 'John', 'Smith', '1985-05-15', '0771000001', '456 Faculty Rd, Kandy', 'john.smith@tec.edu', '$2a$12$LDx96ZD3dRSZtOqKX4Fvw.DEpxHkA3uzpZwkmZkAOG9xCYVRvdXpC', 38),
-- Lecturer 2 (password: BSTProfessor$456)
('LEC02', 'Emma', 'Johnson', '1990-08-22', '0771000002', '789 Campus Ave, Galle', 'emma.j@tec.edu', '$2a$12$egojYLfXx8XJJyUM9p6q/.aNLaUtnY4wJQK6i7ppoShl.Loe.lodC', 33),

-- Students
-- Student 1 (password: MichaelST001!)
('ST001', 'Michael', 'Williams', '2000-03-10', '0772000001', '101 Dorm St, Colombo', 'michael.w@tec.edu', '$2a$12$jay41x8noy0xKjPuOxS1T.4mVH7vROc2r1Kc29R5ckzwpNDpSs/FC', 23),
-- Student 2 (password: SarahBST2023)
('ST002', 'Sarah', 'Brown', '2001-11-30', '0772000002', '202 Student Rd, Kandy', 'sarah.b@tec.edu', '$2a$12$3M.XoLtAKmnDuvsrrfgfIugrOH63crTpA/N9wquciS1cc9FfCd59G', 22),
-- Student 3 (password: DavidET$456)
('ST003', 'David', 'Jones', '2002-07-18', '0772000003', '303 Campus Ave, Galle', 'david.j@tec.edu', '$2a$12$XPPIkIx7DS1gy/SnqRVM..I1jrm1n/G2WoGr5dTEpSTUHsPGlBwpy', 21),
-- Student 4 (password: LisaICT@789)
('ST004', 'Lisa', 'Garcia', '2000-04-25', '0772000004', '404 Uni Lane, Colombo', 'lisa.g@tec.edu', '$2a$12$Gi6L1Yy9VcGKHbAPAXAqpefXQ/orjMDHwSkp5ZLnYsJX/2aQ8yQT2', 23),
-- Student 5 (password: RobertTEC2023)
('ST005', 'Robert', 'Miller', '2001-09-12', '0772000005', '505 College Blvd, Kandy', 'robert.m@tec.edu', '$2a$12$rsSN5TpMAEpg3DK76mMyEe3MQg7ciiKmVT7aOlU0d.xYi7oRa9v6m', 22),

-- Technical Officers
-- Tech Officer 1 (password: AmandaTech@123)
('TEC01', 'Amanda', 'Davis', '1993-12-05', '0773000001', '606 Tech St, Colombo', 'amanda.d@tec.edu', '$2a$12$dFgMEewQu9tn8TtsIiceuuNN/aspOgSasHFW9NLloq2zqulq6cE/G', 30),
-- Tech Officer 2 (password: JamesSupport$456)
('TEC02', 'James', 'Rodriguez', '1992-02-28', '0773000002', '707 Support Rd, Kandy', 'james.r@tec.edu', '$2a$12$7B.XyBNQpIttI/h05Nu.HeaKCB9EmGmzpbq8lUf4OSdyrTtB8uBCm', 31);

-- Insert role-specific data
INSERT INTO `admin` VALUES ('AD001');

INSERT INTO `lecturer` VALUES
                           ('LEC01', 'ICT', '2015-06-20', 'Senior Lecturer'),
                           ('LEC02', 'BST', '2018-03-15', 'Lecturer');

INSERT INTO `student` VALUES
                          ('ST001', 'Level 4', 'ICT', 'Active'),
                          ('ST002', 'Level 3', 'BST', 'Active'),
                          ('ST003', 'Level 4', 'ET', 'Probation'),
                          ('ST004', 'Level 2', 'ICT', 'Active'),
                          ('ST005', 'Level 3', 'ET', 'Active');

INSERT INTO `technical_officer` VALUES
                                    ('TEC01', '2019-05-10'),
                                    ('TEC02', '2020-02-18');

-- Insert some courses
INSERT INTO `course` VALUES
                         ('C001', 'Database Systems', 'Credit', 3, 'GPA'),
                         ('C002', 'Business Statistics', 'Credit', 2, 'GPA'),
                         ('C003', 'Electrical Circuits', 'Credit', 4, 'GPA'),
                         ('C004', 'Professional Ethics', 'Non-Credit', 1, 'Non-GPA');

-- Enroll students in courses
INSERT INTO `stu_course` VALUES
                             ('ST001', 'C001'), ('ST001', 'C004'),
                             ('ST002', 'C002'), ('ST002', 'C004'),
                             ('ST003', 'C003'), ('ST003', 'C004'),
                             ('ST004', 'C001'), ('ST004', 'C002'),
                             ('ST005', 'C003'), ('ST005', 'C004');

