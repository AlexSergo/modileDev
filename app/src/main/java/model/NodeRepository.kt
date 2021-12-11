package model

import androidx.lifecycle.LiveData
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class NodeRepository(private val nodeDao: INodeDao) {

    suspend fun insertAsync(node: Node){
            nodeDao.insertNode(node)
    }

    suspend fun deleteAsync(node: Node){
            nodeDao.deleteNode(node)
    }

    suspend fun getAllNodes(): List<Node> {
        return nodeDao.getAllNodes()
    }

   suspend fun getNodeById(id: Int): Node? {
        return nodeDao.getNodeById(id)
    }

    suspend fun updateNodeByIdAsync(id: Int, nodes: List<Node>) {
        nodeDao.updateNodeById(id, nodes)
    }

    suspend fun updateChildNodeByIdAsync(id: Int, hasParent: Boolean) {
        nodeDao.updateChildNodeById(id, hasParent)
    }

    suspend fun updateNode(id: Int, nodes: List<Node>) {
        nodeDao.updateNode(id, nodes)
    }
}
