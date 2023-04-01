CREATE DATABASE IF NOT EXISTS HealthSystemDB;
USE HealthSystemDB;

CREATE TABLE IF NOT EXISTS HospitalsAdresses
(
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    street_name NVARCHAR(50),
    number INTEGER,
    city NVARCHAR(30),
    zip_code INTEGER
);

CREATE TABLE IF NOT EXISTS Hospitals
(
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(50) NOT NULL,
    address_id INTEGER,
    phone_number INTEGER,
    CONSTRAINT fk_address_id FOREIGN KEY (address_id)
    REFERENCES HospitalsAdresses(id)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS HospitalDepartments
(
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(20),
    hospital_id INTEGER NOT NULL,
    CONSTRAINT fk_hospital_id FOREIGN KEY (hospital_id)
    REFERENCES Hospitals(id)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Doctors
(
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cnp INTEGER(13) NOT NULL,
    first_name NVARCHAR(30) NOT NULL,
    last_name NVARCHAR(30) NOT NULL,
    phone INTEGER,
    work_mail VARCHAR(30) NOT NULL,
    specialization VARCHAR(10) NOT NULL,
    department_id INTEGER,

    CONSTRAINT uk_cnp UNIQUE (cnp),
    CONSTRAINT uk_mail UNIQUE (work_mail),
    CONSTRAINT fk_department_id FOREIGN KEY (department_id)
    REFERENCES HospitalDepartments(id)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS PeopleAddresses
(
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    street_name NVARCHAR(50),
    number VARCHAR(10),
    building VARCHAR(10),
    floor VARCHAR(10),
    apartment VARCHAR(10),
    city NVARCHAR(30),
    zip_code INTEGER
);

CREATE TABLE IF NOT EXISTS People
(
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cnp INTEGER(13) NOT NULL,
    first_name NVARCHAR(30),
    last_name NVARCHAR(30),
    birth_date DATE,
    age INTEGER,
    gender NVARCHAR(10),
    phone INTEGER,
    email NVARCHAR(30),
    address_id INTEGER,

    CONSTRAINT fk_person_address FOREIGN KEY (address_id)
    REFERENCES PeopleAddresses(id)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Devices
(
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    device_name VARCHAR(20),
    type VARCHAR(20),
    mac VARCHAR(12),

    CONSTRAINT uk_device UNIQUE(device_name, mac)
);

CREATE TABLE IF NOT EXISTS Diagnoses
(
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    diagnosis NVARCHAR(50),
    diagnosis_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS Treatments
(
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    treatment NVARCHAR(50),
    start_date DATE NOT NULL,
    end_date DATE,
    diagnosis_id INTEGER,

    CONSTRAINT fk_diagnosis_id FOREIGN KEY(diagnosis_id)
    REFERENCES Diagnoses(id)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Medications
(
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    dosage VARCHAR(20),
    frequency VARCHAR(20),
    treatment_id INTEGER NOT NULL,

    CONSTRAINT fk_treatment_id FOREIGN KEY(treatment_id)
    REFERENCES Treatments(id)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Patients
(
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    person_id INTEGER NOT NULL,
    doctor_id INTEGER NOT NULL,
    device_id INTEGER NOT NULL,
    monitoring_start_date DATE,
    diagnosis_id INTEGER NOT NULL,
    treatment_id INTEGER NOT NULL,

    CONSTRAINT uk_person_device UNIQUE(person_id, device_id),
    CONSTRAINT fk_person_id FOREIGN KEY(person_id)
    REFERENCES People(id),
    CONSTRAINT fk_device_id FOREIGN KEY(device_id)
    REFERENCES Devices(id),
    CONSTRAINT fk_patient_diagnosis_id FOREIGN KEY(diagnosis_id)
    REFERENCES Diagnoses(id),
    CONSTRAINT fk_patient_treatment_id FOREIGN KEY(treatment_id)
    REFERENCES Treatments(id)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS PatientsAllergies
(
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    patient_id INTEGER NOT NULL,
    allergen NVARCHAR(50),

    CONSTRAINT fk_patient_id FOREIGN KEY(patient_id)
    REFERENCES Patients(id)
    ON DELETE CASCADE
);
