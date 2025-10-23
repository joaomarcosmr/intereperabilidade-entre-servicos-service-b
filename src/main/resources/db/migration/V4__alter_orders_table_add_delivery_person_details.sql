-- Remove the old delivery_person_id column if it exists
ALTER TABLE orders DROP COLUMN IF EXISTS delivery_person_id;

-- Add delivery_person_name column if it doesn't exist
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_name = 'orders'
        AND column_name = 'delivery_person_name'
    ) THEN
        ALTER TABLE orders ADD COLUMN delivery_person_name VARCHAR(255);
    END IF;
END $$;

-- Add delivery_person_phone column if it doesn't exist
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_name = 'orders'
        AND column_name = 'delivery_person_phone'
    ) THEN
        ALTER TABLE orders ADD COLUMN delivery_person_phone VARCHAR(255);
    END IF;
END $$;

-- If columns exist but are the wrong type, alter them
ALTER TABLE orders
    ALTER COLUMN delivery_person_name TYPE VARCHAR(255) USING delivery_person_name::VARCHAR(255);

ALTER TABLE orders
    ALTER COLUMN delivery_person_phone TYPE VARCHAR(255) USING delivery_person_phone::VARCHAR(255);
