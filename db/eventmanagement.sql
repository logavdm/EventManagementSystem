
-- Database: `eventmanagement`
--


CREATE TABLE `department` (
  `dep_id` int(11) NOT NULL,
  `department_name` varchar(255) NOT NULL,
  `department_description` varchar(255) NOT NULL,
  `department_shortname` varchar(255) NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



INSERT INTO `department` (`dep_id`, `department_name`, `department_description`, `department_shortname`, `updated_at`, `created_at`) VALUES
(1, 'computer science', 'demo', 'CS', '2023-06-20 15:34:11', '2023-06-20 15:34:11');


CREATE TABLE `events` (
  `event_id` int(11) NOT NULL,
  `event_name` varchar(255) NOT NULL,
  `venue` varchar(255) NOT NULL,
  `date_from` datetime NOT NULL,
  `date_to` datetime NOT NULL,
  `time_from` varchar(255) NOT NULL,
  `time_to` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `dept_id` int(11) NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



INSERT INTO `events` (`event_id`, `event_name`, `venue`, `date_from`, `date_to`, `time_from`, `time_to`, `description`, `dept_id`, `updated_at`, `created_at`) VALUES
(1, 'demo', 'demo_venue', '2023-06-07 00:00:00', '2023-06-07 00:00:00', '12.20', '13.40', 'description', 1, '2023-06-20 14:58:34', '2023-06-20 14:58:34');


CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `users` (`id`, `username`, `password`, `role`, `enabled`, `updated_at`, `created_at`) VALUES
(5, 'demo2', 'demo3', 'USER', 1, '2023-06-21 17:30:23', '2023-06-21 17:16:41'),
(6, 'demo3', 'demo3', 'USER', 1, '2023-06-21 17:32:08', '2023-06-21 17:32:08');


ALTER TABLE `department`
  ADD PRIMARY KEY (`dep_id`);


ALTER TABLE `events`
  ADD PRIMARY KEY (`event_id`);


ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);


ALTER TABLE `department`
  MODIFY `dep_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;


ALTER TABLE `events`
  MODIFY `event_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;


ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;