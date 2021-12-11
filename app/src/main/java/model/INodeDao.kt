package model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface INodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNode(node:Node)
    @Query("SELECT * FROM node")
    suspend fun getAllNodes(): List<Node>
    @Query("SELECT * FROM node WHERE id == :id")
    suspend fun getNodeById(id: Int): Node
    @Delete
    fun deleteNode(node:Node)
    @Query("UPDATE node SET nodes = :nodes WHERE id = :id")
    suspend fun updateNodeById(id: Int, nodes: List<Node>)
    @Query("UPDATE node SET hasParent = :hasParent WHERE id = :id")
    suspend fun updateChildNodeById(id: Int, hasParent: Boolean)
    @Query("UPDATE node SET nodes = :nodes WHERE id = :id")
    suspend fun updateNode(id: Int, nodes: List<Node>)
}