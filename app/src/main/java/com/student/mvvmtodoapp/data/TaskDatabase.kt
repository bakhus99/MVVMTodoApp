package com.student.mvvmtodoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.student.mvvmtodoapp.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val dao = database.get().taskDao()
            applicationScope.launch {
                dao.insert(Task("Wash the dishes",completed = true))
                dao.insert(Task("Go walk",important = true))
                dao.insert(Task("Help Max",important = true))
                dao.insert(Task("Do laundry",completed = true))
                dao.insert(Task("Play with child"))
            }
        }
    }

}