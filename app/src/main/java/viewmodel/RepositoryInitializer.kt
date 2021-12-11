package viewmodel

import android.content.Context
import model.INodeDao
import model.NodeRepository
import model.NodeRoomDatabase

object RepositoryInitializer{

    private var nodeDao: INodeDao? = null
    private lateinit var nodeRepository: NodeRepository

    fun getRepository(context: Context): NodeRepository{
        if (nodeDao == null) {
            nodeDao = NodeRoomDatabase.getInstance(context)?.nodeDao()
            nodeRepository = NodeRepository(nodeDao!!)
        }
        return nodeRepository
    }
}