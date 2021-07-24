package com.morse.common.local.db.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

const val EntityTableName = "DEMO"

@Parcelize
@Entity(tableName = EntityTableName)
data class Entity(
    @PrimaryKey
    val id: Int,
) : Parcelable