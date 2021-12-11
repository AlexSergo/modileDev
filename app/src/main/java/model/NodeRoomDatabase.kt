package model

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.*

@Database(entities = [Node::class], version = 2, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class NodeRoomDatabase : RoomDatabase() {
    abstract fun nodeDao(): INodeDao

    companion object {
        @Volatile
        var database: NodeRoomDatabase? = null

        fun getInstance(context: Context): NodeRoomDatabase? {
            if (database == null) {
                synchronized(this) {
                        var db = Room.databaseBuilder(
                            context.applicationContext,
                            NodeRoomDatabase::class.java, "node_database"
                        ).fallbackToDestructiveMigration()
                            .build()
                    database = db
                    return db
                }
            }
            return database
        }
    }
}
