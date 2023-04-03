-- Hospital Adresses
INSERT INTO HospitalsAdresses (street_name, number, city, zip_code)
VALUES ('Str. Fericirii', 10, 'Bucuresti', 010101),
    ('Str. Dorobantilor', 23, 'Cluj-Napoca', 400000),
    ('Str. Libertatii', 5, 'Iasi', 700000),
    ('Str. Revolutiei', 12, 'Timisoara', 300000);

-- Hospitals
INSERT INTO Hospitals (name, address_id, phone_number)
VALUES
    ('Spitalul Universitar Bucuresti', 1, 0123456789),
    ('Spitalul Clinic de Urgenta Cluj-Napoca', 2, 0234567890),
    ('Spitalul de Boli Infectioase Iasi', 3, 0345678901),
    ('Spitalul Judetean Timisoara', 4, 0456789012);

-- HospitalDepartments
INSERT INTO HospitalDepartments (name, hospital_id)
VALUES
    ('Cardiologie', 1),
    ('Chirurgie', 1),
    ('Neurologie', 2),
    ('Ortopedie', 3),
    ('Pediatrie', 4);

-- Doctors
INSERT INTO Doctors (username, cnp, first_name, last_name, phone, work_mail, specialization, department_id)
VALUES
    ('mariapopescu', '1234567890123', 'Maria', 'Popescu', 0123456789, 'mariapopescu@spitalulbucuresti.ro', 'Cardiologie', 1),
    ('ionionescu', '2345678901234', 'Ion', 'Ionescu', 0234567890, 'ionionescu@spitalulcluj.ro', 'Neurologie', 3),
    ('andreigeorgescu', '3456789012345', 'Andrei', 'Georgescu', 0345678901, 'andreigeorgescu@spitaluliasi.ro', 'Ortopedie', 4),
    ('dianapop', '4567890123456', 'Diana', 'Pop', 0456789012, 'dianapop@spitalultimisoara.ro', 'Pediatrie', 5);

-- People Addresess
INSERT INTO PeopleAddresses (street_name, number, building, floor, apartment, city, zip_code)
VALUES
    ('Strada Lunii', '10', 'A', '1', '2', 'Bucuresti', 123123),
    ('Strada Soarelui', '5', 'B', '2', '3', 'Cluj-Napoca', 456456),
    ('Strada Florilor', '12', 'C', '3', '4', 'Iasi', 789789),
    ('Bulevardul Unirii', '20', 'D', '4', '5', 'Timisoara', 012012);

-- People
INSERT INTO People (cnp, first_name, last_name, birth_date, age, gender, phone, email, address_id)
VALUES
(1960723123456, 'Maria', 'Popescu', '1996-07-23', 25, 'Feminin', '0721123456', 'maria.popescu@gmail.com', 1),
(1850409123456, 'Ion', 'Ionescu', '1985-04-09', 36, 'Masculin', '0755123456', 'ion.ionescu@yahoo.com', 2),
(1970527123456, 'Ana', 'Dumitru', '1997-05-27', 24, 'Feminin', '0729123456', 'ana.dumitru@hotmail.com', 3),
(2000116123456, 'Mihai', 'Georgescu', '2000-11-16', 21, 'Masculin', '0767123456', 'mihai.georgescu@gmail.com', 4);

-- Devices
INSERT INTO Devices (device_name, type, mac)
VALUES
    ('device-01', 'Pulse Oximetry', '11:22:33:44:55:66'),
    ('device-02', 'Pulse Oximetry', '33:44:55:66:77:88'),
    ('device-03', 'Pulse Oximetry', '55:66:77:88:99:00'),
    ('device-04', 'Pulse Oximetry', '99:00:11:22:33:44');

-- Diagnoses
INSERT INTO Diagnoses (diagnosis, diagnosis_date)
VALUES
    ('Hipertensiune arterială', '2022-01-05'),
    ('Infarct miocardic', '2022-02-15'),
    ('Diabet zaharat', '2022-03-21'),
    ('Pneumonie', '2022-04-10');

-- Treatments
INSERT INTO Treatments (treatment, start_date, end_date, diagnosis_id)
VALUES
    ('Tratament antihpertensiv', '2022-01-06', '2022-04-06', 1),
    ('Tratament post-infarct miocardic', '2022-02-16', '2022-05-16', 2),
    ('Regim alimentar pentru diabetici', '2022-03-22', '2022-04-22', 3),
    ('Tratament antibiotic', '2022-04-11', '2022-04-20', 4);

-- Medications
INSERT INTO Medications (name, dosage, frequency, treatment_id)
VALUES
    ('Enalapril', '10mg', '1 comprimat pe zi', 1),
    ('Aspirina', '100mg', '1 comprimat pe zi', 2),
    ('Insulină', '10 UI', '3 ori pe zi', 3),
    ('Amoxicilină', '500mg', '2 comprimate pe zi', 4);

INSERT INTO Patients (person_id, doctor_id, device_id, monitoring_start_date, diagnosis_id, treatment_id)
VALUES
    (1, 'ionionescu', 1, '2022-01-01', 1, 1),
    (2, 'ionionescu', 2, '2022-02-01', 2, 2),
    (3, 'dianapop', 3, '2022-03-01', 3, 3);

INSERT INTO PatientsAllergies (patient_id, allergen)
VALUES
    (1, 'Polen'),
    (2, 'Acarieni'),
    (3, 'Lactoza');
