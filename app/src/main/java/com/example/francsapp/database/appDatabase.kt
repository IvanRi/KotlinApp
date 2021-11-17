package com.example.francsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.francsapp.models.FavoriteItem
import com.example.francsapp.models.OrderItem

@Database(entities = [OrderItem::class, FavoriteItem::class], version = 2)

abstract class appDatabase : RoomDatabase() {

    abstract fun itemsDao(): itemDao
    abstract fun favoriteItemDao(): FavoriteItemDao

    companion object {
        var INSTANCE: appDatabase? = null

        fun getAppDataBase(context: Context): appDatabase? {
            if (INSTANCE == null) {
                synchronized(appDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        appDatabase::class.java,
                        "francsAppDB"
                    ).addMigrations(MIGRATION_1_2).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE favorites (producId INTEGER NOT NULL," +
                        "PRIMARY KEY(producId))")
            }
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}