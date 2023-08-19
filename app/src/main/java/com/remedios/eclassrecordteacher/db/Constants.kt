package com.remedios.eclassrecordteacher.db

object Constants{
    const val DB_NAME = "MY_RECORDS_DB"
    const val DB_VERSION = 1
    const val TABLE_NAME = "MY_RECORDS_TABLE"

    const val C_ID = "ID"
    const val C_NAME = "NAME"
    const val C_IMAGE = "IMAGE"
    const val C_SCHOOLNAME = "SCHOOL_NAME"
    const val C_DISTRICTNAME = "DISTRICT_NAME"
    const val C_DIVISIONNAME = "DIVISION_NAME"

    const val C_ADDED_TIMESTAMP = "ADDED_TIME_STAMP"
    const val C_UPDATED_TIMESTAMP = "UPDATED_TIMESTAMP"

    const val CREATE_TABLE = (
            "CREATE_TABLE" + TABLE_NAME + "("
            + C_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_NAME + "TEXT,"
            + C_IMAGE + "TEXT,"
            + C_SCHOOLNAME + "TEXT,"
            + C_DISTRICTNAME + "TEXT,"
            + C_DIVISIONNAME + "TEXT,"
            + C_ADDED_TIMESTAMP + "TEXT,"
            + C_UPDATED_TIMESTAMP + "TEXT, "
            + ")"
            )
}