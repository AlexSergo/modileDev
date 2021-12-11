package viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import model.NodeRepository
import java.lang.IllegalArgumentException

class NodeViewModelFactory(private val nodeRepository: NodeRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NodeViewModel::class.java))
            return NodeViewModel(nodeRepository)  as T
        throw IllegalArgumentException("ViewModel not found!")
    }
}