ALTER TABLE student ADD CONSTRAINT age_constraint CHECK (age > 15);
ALTER  TABLE student ALTER COLUMN name SET NOT NULL;
ALTER TABLE student ADD CONSTRAINT name_unique UNIQUE (name);
ALTER TABLE faculty ADD CONSTRAINT UQ_name_color UNIQUE(name, color);
ALTER TABLE student ALTER age SET DEFAULT 20;
