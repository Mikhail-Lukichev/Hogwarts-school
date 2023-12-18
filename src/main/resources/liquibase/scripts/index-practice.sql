-- liquibase formatted sql

-- changeset mlukichev:1
CREATE INDEX student_name_index ON student (name)

-- changeset mlukichev:2
create index faculty_name_color_index on faculty (name, color)