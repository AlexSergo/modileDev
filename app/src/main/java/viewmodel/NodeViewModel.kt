package viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import model.Node
import model.NodeRepository
import model.NodeRoomDatabase

class NodeViewModel(private val nodeRepository: NodeRepository): ViewModel(){
   // val allNodes: MutableLiveData<List<Node>> by lazy { MutableLiveData<List<Node>>() }

    fun init(){
/*        viewModelScope.launch(Dispatchers.IO) {
            allNodes.postValue(nodeRepository.getAllNodes())
        }*/
    }

    fun insertNode(node:Node) = viewModelScope.launch(Dispatchers.IO) {
            nodeRepository.insertAsync(node)
        }

    fun deleteNode(node: Node) = viewModelScope.launch{
            nodeRepository.deleteAsync(node)
        }

    fun getAllNodes(): List<Node>{
        return runBlocking {
            nodeRepository.getAllNodes()
        }
    }

    fun getNodeById(id:Int): Node? {
        return runBlocking {
            nodeRepository.getNodeById(id)
        }
    }

    fun updateNodeById(id: Int, nodes: List<Node>) = viewModelScope.launch {
        nodeRepository.updateNodeByIdAsync(id, nodes)
    }

    fun updateChildNodeById(id: Int, hasParent: Boolean) = viewModelScope.launch {
        nodeRepository.updateChildNodeByIdAsync(id, hasParent)
    }

    fun updateNode(id: Int, nodes: List<Node>) = viewModelScope.launch {
        nodeRepository.updateNode(id, nodes)
    }
}