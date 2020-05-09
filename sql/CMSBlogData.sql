USE CMSBlog;

-- User

INSERT INTO `User` (UserID, Username, `Password`, Enabled, FirstName, LastName) VALUES
(1, 'admin', '$2a$10$ok.GKvKjtQ7M.Z4m6vgQEetx2AZMBktvkaoyKlbJztBsR7fIR4iPK', 1, 'John', 'Smith'),
(2, 'user', '$2a$10$ok.GKvKjtQ7M.Z4m6vgQEetx2AZMBktvkaoyKlbJztBsR7fIR4iPK', 1, 'Jane', 'Doe');

INSERT INTO Authority (Username, Authority) VALUES
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_USER'),
('user', 'ROLE_USER');

ALTER TABLE Authority
ADD CONSTRAINT fk_Authority_User
FOREIGN KEY (Username) REFERENCES `User`(Username);

-- Status

INSERT INTO `Status` (`Name`) VALUES
('Draft'),
('Publish');

-- Link

INSERT INTO Navigation (Section) VALUES
('Navbar'),
('Footer');