package com.marmistrz.openmeteo.database

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(database = AppDatabase::class)
class Location : BaseModel {
    @PrimaryKey var name: String = ""
    @Column var row: Int = 0
    @Column var column: Int = 0

    constructor() {}
    constructor(name: String, row: Int, column: Int) {
        this.name = name
        this.row = row
        this.column = column
    }
}
