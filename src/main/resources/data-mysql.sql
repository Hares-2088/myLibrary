-- Authors
INSERT INTO authors (author_id, first_name, last_name, biography, city, province, country)
VALUES
    ('a1', 'John', 'Doe', 'Biography of John Doe', 'New York', 'New York', 'USA'),
    ('a2', 'Jane', 'Smith', 'Biography of Jane Smith', 'London', 'Greater London', 'UK'),
    ('a3', 'Michael', 'Johnson', 'Biography of Michael Johnson', 'Los Angeles', 'California', 'USA'),
    ('a4', 'Emily', 'Brown', 'Biography of Emily Brown', 'Sydney', 'New South Wales', 'Australia'),
    ('a5', 'David', 'Wilson', 'Biography of David Wilson', 'Toronto', 'Ontario', 'Canada'),
    ('a6', 'Emma', 'Jones', 'Biography of Emma Jones', 'Paris', 'Île-de-France', 'France'),
    ('a7', 'William', 'Taylor', 'Biography of William Taylor', 'Berlin', 'Berlin', 'Germany'),
    ('a8', 'Olivia', 'Martinez', 'Biography of Olivia Martinez', 'Madrid', 'Community of Madrid', 'Spain'),
    ('a9', 'Noah', 'Garcia', 'Biography of Noah Garcia', 'Rome', 'Lazio', 'Italy'),
    ('a10', 'Sophia', 'Lee', 'Biography of Sophia Lee', 'Beijing', 'Beijing', 'China');

-- Books
INSERT INTO books (book_id, author_id, title, publication_year, genre, description, available_copies)
VALUES
    ('b1', 'a1', 'Sample Book 1', '2023-01-01', 'Fiction', 'This is a sample description for Sample Book 1.', '5'),
    ('b2', 'a2', 'Sample Book 2', '2020-05-15', 'Non-Fiction', 'This is a sample description for Sample Book 2.', '10'),
    ('b3', 'a3', 'Sample Book 3', '2022-09-30', 'Science Fiction', 'This is a sample description for Sample Book 3.', '8'),
    ('b4', 'a4', 'Sample Book 4', '2021-03-20', 'Fantasy', 'This is a sample description for Sample Book 4.', '3'),
    ('b5', 'a5', 'Sample Book 5', '2019-07-10', 'Mystery', 'This is a sample description for Sample Book 5.', '12'),
    ('b6', 'a6', 'Sample Book 6', '2020-11-05', 'Thriller', 'This is a sample description for Sample Book 6.', '7'),
    ('b7', 'a7', 'Sample Book 7', '2024-02-15', 'Romance', 'This is a sample description for Sample Book 7.', '9'),
    ('b8', 'a8', 'Sample Book 8', '2022-06-25', 'Biography', 'This is a sample description for Sample Book 8.', '6'),
    ('b9', 'a9', 'Sample Book 9', '2023-10-18', 'History', 'This is a sample description for Sample Book 9.', '4'),
    ('b10', 'a10', 'Sample Book 10', '2020-12-30', 'Self-Help', 'This is a sample description for Sample Book 10.', '11');

-- Members
INSERT INTO members (member_id, book_id, reservation_id, first_name, last_name, email, benefits, member_type, street, city, province, country)
VALUES
    ('m1', 'b1', 'r1', 'John', 'Doe', 'john.doe@example.com', 'Free shipping, Member discounts', 'STUDENT', '123 Main St', 'Anytown', 'Anyprovince', 'Anycountry'),
    ('m2', 'b2', 'r2', 'Jane', 'Smith', 'jane.smith@example.com', 'Early access to new releases', 'STUDENT', '456 Elm St', 'Othertown', 'Otherprovince', 'Othercountry'),
    ('m3', 'b3', 'r3', 'Michael', 'Johnson', 'michael.johnson@example.com', 'Free shipping', 'FACULTY', '789 Oak St', 'Another Town', 'Anotherprovince', 'Anothercountry'),
    ('m4', 'b4', 'r4', 'Emily', 'Brown', 'emily.brown@example.com', 'Member discounts', 'STUDENT', '101 Pine St', 'Yet Another Town', 'Yet Another Province', 'Yet Another Country'),
    ('m5', 'b5', 'r5', 'David', 'Wilson', 'david.wilson@example.com', 'Early access to new releases', 'STUDENT', '111 Cedar St', 'Some City', 'Some Province', 'Some Country'),
    ('m6', 'b6', 'r6', 'Emma', 'Jones', 'emma.jones@example.com', 'Free shipping', 'FACULTY', '222 Birch St', 'Another City', 'Another Province', 'Another Country'),
    ('m7', 'b7', 'r7', 'William', 'Taylor', 'william.taylor@example.com', 'Member discounts', 'STUDENT', '333 Maple St', 'Some Other City', 'Some Other Province', 'Some Other Country'),
    ('m8', 'b8', 'r8', 'Olivia', 'Martinez', 'olivia.martinez@example.com', 'Early access to new releases', 'STUDENT', '444 Walnut St', 'Yet Another City', 'Yet Another Province', 'Yet Another Country'),
    ('m9', 'b9', 'r9', 'Noah', 'Garcia', 'noah.garcia@example.com', 'Free shipping, Member discounts', 'STUDENT', '555 Oak St', 'Last City', 'Last Province', 'Last Country'),
    ('m10', 'b10', 'r10', 'Sophia', 'Lee', 'sophia.lee@example.com', 'Early access to new releases', 'STUDENT', '666 Elm St', 'Final City', 'Final Province', 'Final Country');

-- Reservations
INSERT INTO reservations (reservation_id, reservation_date, book_id, member_id)
VALUES
    ('r1', '2024-02-28', 'b1', 'm1'),
    ('r2', '2024-03-05', 'b2', 'm2'),
    ('r3', '2024-03-12', 'b3', 'm3'),
    ('r4', '2024-03-19', 'b4', 'm4'),
    ('r5', '2024-03-26', 'b5', 'm5'),
    ('r6', '2024-04-02', 'b6', 'm6'),
    ('r7', '2024-04-09', 'b7', 'm7'),
    ('r8', '2024-04-16', 'b8', 'm8'),
    ('r9', '2024-04-23', 'b9', 'm9'),
    ('r10', '2024-04-30', 'b10', 'm10');

-- Loans
INSERT INTO loans (loan_id, member_id, book_id, reservation_id, returned, loan_date, due_date)
VALUES
    ('l1', 'm1', 'b1', 'r1', false, '2024-02-28', '2024-03-13'),
    ('l2', 'm2', 'b2', 'r2', false, '2024-03-05', '2024-03-19'),
    ('l3', 'm3', 'b3', 'r3', false, '2024-03-12', '2024-03-26'),
    ('l4', 'm4', 'b4', 'r4', false, '2024-03-19', '2024-04-02'),
    ('l5', 'm5', 'b5', 'r5', false, '2024-03-26', '2024-04-09'),
    ('l6', 'm6', 'b6', 'r6', false, '2024-04-02', '2024-04-16'),
    ('l7', 'm7', 'b7', 'r7', false, '2024-04-09', '2024-04-23'),
    ('l8', 'm8', 'b8', 'r8', false, '2024-04-16', '2024-04-30'),
    ('l9', 'm9', 'b9', 'r9', false, '2024-04-23', '2024-05-07'),
    ('l10', 'm10', 'b10', 'r10', false, '2024-04-30', '2024-05-14');
