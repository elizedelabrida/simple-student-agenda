package com.elize.simple_student_agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.elize.simple_student_agenda.model.PhoneType;

public class StudentAgendaMigrations {
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Student ADD COLUMN surname TEXT");
        }
    };
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Student ADD COLUMN dateCreated INTEGER");
        }
    };
    // Test migration to validate column drop process
    private static final Migration TEST_MIGRATION_1_2_REMOVE_COLUMN = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // SQLite does not support column drop
            // To revert above update, we must create the new table
            database.execSQL("CREATE TABLE IF NOT EXISTS `new_Student` " +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`name` TEXT, " +
                    "`phone` TEXT, " +
                    "`mail` TEXT)");
            // Copy data from previous table to the new one
            database.execSQL("INSERT INTO new_Student (id, name, phone, mail) " +
                    "SELECT id, name, phone, mail FROM Student");
            // Remove previous table
            database.execSQL("DROP TABLE Student");
            // Rename new table to match previous table name
            database.execSQL("ALTER TABLE new_Student RENAME TO Student");
        }
    };
    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Student ADD COLUMN mobileNumber TEXT");
        }
    };
    private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `new_Student` " +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`name` TEXT, " +
                    "`surname` TEXT, " +
                    "`mail` TEXT, " +
                    "`dateCreated` INTEGER)");
            database.execSQL("INSERT INTO new_Student (id, name, surname, mail, dateCreated) " +
                    "SELECT id, name, surname, mail, dateCreated FROM Student");


            database.execSQL("CREATE TABLE IF NOT EXISTS `Phone` " +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`number` TEXT, `type` TEXT, `student_id` INTEGER NOT NULL, " +
                    "FOREIGN KEY(`student_id`) REFERENCES `Student`(`id`) " +
                    "ON UPDATE CASCADE ON DELETE CASCADE )");
            database.execSQL("INSERT INTO Phone (number, student_id) " +
                    "SELECT phone, id FROM Student");

            database.execSQL("UPDATE Phone SET type = ?", new PhoneType[]{PhoneType.LANDLINE});

            database.execSQL("DROP TABLE Student");
            database.execSQL("ALTER TABLE new_Student RENAME TO Student");
        }
    };
    public static final Migration[] ALL_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4,
            MIGRATION_4_5};
}
