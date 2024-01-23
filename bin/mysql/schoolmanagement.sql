-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 19, 2024 lúc 04:57 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `schoolmanagement`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `class`
--

CREATE TABLE `class` (
  `id` int(10) NOT NULL,
  `classname` varchar(255) NOT NULL,
  `department` varchar(255) NOT NULL,
  `academicyear` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `class`
--

INSERT INTO `class` (`id`, `classname`, `department`, `academicyear`) VALUES
(1, '21TTH1', 'Mathematics - Computer Science', '2021'),
(3, '21HOH1', 'Chemistry', '2021'),
(4, '22TTH1', 'Mathematics - Computer Science', '2022'),
(5, '20CTT1', 'Technology', '2020'),
(6, '23DTV1', 'Electronics - Telecommunication', '2023');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `exam`
--

CREATE TABLE `exam` (
  `id` int(10) NOT NULL,
  `examname` varchar(255) NOT NULL,
  `term` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `time` varchar(255) NOT NULL,
  `classname` varchar(255) NOT NULL,
  `department` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `exam`
--

INSERT INTO `exam` (`id`, `examname`, `term`, `date`, `time`, `classname`, `department`, `subject`) VALUES
(1, 'End of the subject', '1st Term', '15-01-2024', '13h30 - 15h', '21TTH1', 'Mathematics - Computer Science', 'Discrete Mathematics'),
(3, 'End of Subject', '1st Term', '31-01-2024', '7h45 - 8h45', '21TTH1', 'Mathematics - Computer Science', 'Algebra A2');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `mark`
--

CREATE TABLE `mark` (
  `id` int(10) NOT NULL,
  `studentnumber` varchar(255) NOT NULL,
  `studentname` varchar(255) NOT NULL,
  `classname` varchar(255) NOT NULL,
  `department` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `term` varchar(255) NOT NULL,
  `mark` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `mark`
--

INSERT INTO `mark` (`id`, `studentnumber`, `studentname`, `classname`, `department`, `subject`, `term`, `mark`) VALUES
(1, '21110289', 'Nguyen Nhat Han', '21TTH1', 'Mathematics - Computer Science', 'Discrete Mathematics', '1st Term', '10'),
(2, '21110299', 'Nguyen Le Hoang', '21TTH1', 'Mathematics - Computer Science', 'Introduction to Programming', '1st Term', '8.5'),
(3, '21110299', 'Nguyen Le Hoang', '21TTH1', 'Mathematics - Computer Science', 'Network Design', '1st Mid-term', '9');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `schedule`
--

CREATE TABLE `schedule` (
  `id` int(10) NOT NULL,
  `teachername` varchar(255) NOT NULL,
  `term` varchar(255) NOT NULL,
  `datestart` varchar(255) NOT NULL,
  `periodstart` varchar(255) NOT NULL,
  `periodend` varchar(255) NOT NULL,
  `classname` varchar(255) NOT NULL,
  `department` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `schedule`
--

INSERT INTO `schedule` (`id`, `teachername`, `term`, `datestart`, `periodstart`, `periodend`, `classname`, `department`, `subject`) VALUES
(1, 'Vo Duc Cam Hai', '1st Term', '01-01-2024', '1', '4', '21TTH1', 'Mathematics - Computer Science', 'Network Design'),
(2, 'Teacher A', '2nd Term', '01-01-2024', '1', '5', '21HOH1', 'Chemistry', 'General Chemistry');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `staff`
--

CREATE TABLE `staff` (
  `id` int(10) NOT NULL,
  `staffname` varchar(255) NOT NULL,
  `staffnumber` varchar(255) NOT NULL,
  `dateofbirth` varchar(255) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `department` varchar(255) NOT NULL,
  `position` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `staff`
--

INSERT INTO `staff` (`id`, `staffname`, `staffnumber`, `dateofbirth`, `gender`, `email`, `phone`, `address`, `department`, `position`) VALUES
(1, 'Nguyen Van A', '123', '01-01-1999', 'Male', 'nguyenvana@gmail.com', '123456', 'HCM City', 'Financial - Planning', 'Manager'),
(3, 'staff', '201', '01-01-2024', 'Male', 'staff@hcmus.edu.vn', '23232131', 'Thu Duc City', 'Science - Technology', 'Leader');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `student`
--

CREATE TABLE `student` (
  `id` int(10) NOT NULL,
  `studentname` varchar(255) NOT NULL,
  `studentnumber` int(8) NOT NULL,
  `dateofbirth` varchar(255) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `classname` varchar(255) NOT NULL,
  `department` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `student`
--

INSERT INTO `student` (`id`, `studentname`, `studentnumber`, `dateofbirth`, `gender`, `email`, `phone`, `address`, `classname`, `department`) VALUES
(1, 'Nguyen Le Hoang', 21110299, '29-09-2003', 'Male', '21110299@student.hcmus.edu.vn', 123456789, 'KTX khu B, DHQG-HCM', '21TTH1', 'Mathematics - Computer Science'),
(2, 'Nguyen Nhat Han', 21110289, '24-05-2003', 'Male', 'nhathan@gmail.com', 123456789, 'KTX khu B, DHQG-HCM', '21TTH1', 'Mathematics - Computer Science'),
(3, 'student', 101, '01-01-2024', 'Male', 'student@gmail.com', 22222222, 'HCM City', '21TTH1', 'Mathematics - Computer Science');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `subject`
--

CREATE TABLE `subject` (
  `id` int(10) NOT NULL,
  `subjectcode` varchar(255) NOT NULL,
  `subjectname` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `subject`
--

INSERT INTO `subject` (`id`, `subjectcode`, `subjectname`) VALUES
(2, 'MTH10001', 'Network Design'),
(5, 'MTH1002', 'Web Design'),
(6, 'MTH100003', 'Discrete Mathematics'),
(7, 'MTH100004', 'Algebra A2'),
(8, 'HOH100001', 'General Chemistry'),
(9, 'CSC100001', 'Introduction to Programming');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `teacher`
--

CREATE TABLE `teacher` (
  `id` int(10) NOT NULL,
  `teachername` varchar(255) NOT NULL,
  `teachernumber` varchar(255) NOT NULL,
  `dateofbirth` varchar(255) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `department` varchar(255) NOT NULL,
  `qualification` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `teacher`
--

INSERT INTO `teacher` (`id`, `teachername`, `teachernumber`, `dateofbirth`, `gender`, `email`, `phone`, `address`, `department`, `qualification`) VALUES
(1, 'Vo Duc Cam Hai', '301', '01-01-1992', 'Male', 'vdchai@hcmus.edu.vn', '11111111', 'Tp HCM', 'Mathematics - Computer Science', 'Master'),
(2, 'Peter', '302', '01-01-2024', 'Male', 'peter@gmail.com', '78452', 'HCM City', 'Chemistry', 'Master'),
(3, 'teacher', '303', '01-01-2024', 'Female', 'teacher@gmail.com', '223224225', 'Vinh Phuc', 'Chemistry', 'PhD');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `number` varchar(255) NOT NULL,
  `phone` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `usertype` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `name`, `number`, `phone`, `email`, `username`, `password`, `usertype`) VALUES
(1, 'Hoang', '21110299', 3456789, 'hoang@gmail.com', 'lehoang', '123', 'Admin'),
(3, 'Peter', '302', 78452, 'peter@gmail.com', 'peter', '123', 'Teacher'),
(4, 'Salah', '203', 2222, 'salah@gmail.com', 'salah', '123456', 'Staff'),
(5, 'Quy', '102', 3456, 'quy@gmail.com', 'quy', '123456', 'Student'),
(6, 'Nguyen Van A', '202', 123456, 'ngvana@hcmus.edu.vn', 'ngvana', '123456', 'Staff'),
(7, 'Vo Duc Cam Hai', '301', 11111111, 'vdchai@hcmus.edu.vn', 'vdchai', '123456', 'Teacher'),
(8, 'student', '101', 22222222, 'student@gmail.com', 'student', '123', 'Student'),
(9, 'staff', '201', 23232131, 'staff@hcmus.edu.vn', 'staff', '123', 'Staff');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `exam`
--
ALTER TABLE `exam`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `mark`
--
ALTER TABLE `mark`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `class`
--
ALTER TABLE `class`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `exam`
--
ALTER TABLE `exam`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `mark`
--
ALTER TABLE `mark`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `schedule`
--
ALTER TABLE `schedule`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `staff`
--
ALTER TABLE `staff`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `student`
--
ALTER TABLE `student`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `subject`
--
ALTER TABLE `subject`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `teacher`
--
ALTER TABLE `teacher`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
