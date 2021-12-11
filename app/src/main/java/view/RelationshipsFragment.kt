package view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication6.R
import model.Node
import viewAdapters.IRelationshipClickListener
import viewAdapters.RelationshipsRecyclerViewAdapter
import viewmodel.NodeViewModel
import viewmodel.NodeViewModelFactory
import viewmodel.RepositoryInitializer

class RelationshipsFragment(type: NodeType) : Fragment() {

    private var nodeId = 1
    private var nodeType: NodeType = type
    private lateinit var nodeViewModel: NodeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            nodeId = it.getInt(ARG_ID)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_relationships_list, container, false)

        val viewModelFactory = NodeViewModelFactory(RepositoryInitializer.getRepository(requireContext()))
        nodeViewModel = ViewModelProvider(this, viewModelFactory)
            .get(NodeViewModel::class.java)

        nodeViewModel.init()
        val listNodes = nodeViewModel.getAllNodes()
            listNodes.let {
                if (view is RecyclerView) {
                    val nodeList: MutableList<Node> = mutableListOf()
                    val currentNode = nodeViewModel.getNodeById(nodeId)!!

                    it.forEach{ node ->
                        when(nodeType) {
                            NodeType.Parent->
                                if (node.canBeChildFor(currentNode))
                                    nodeList.add(node)
                            NodeType.Child ->
                                if (currentNode.canBeChildFor(node))
                                    nodeList.add(node)
                        }
                    }

                    with(view) {
                        layoutManager = LinearLayoutManager(context)
                        adapter = RelationshipsRecyclerViewAdapter(nodeList, currentNode, nodeType
                            ,object : IRelationshipClickListener {
                                override fun onRelationshipClick(parent: Node, child: Node) {
                                    nodeViewModel.updateChildNodeById(child.id, child.hasParent)
                                    updateTrees(parent)
                                }
                            })
                    }
                }
            }

        return view
    }

    private fun updateTrees(changedNode: Node){
        val trees = nodeViewModel.getAllNodes().toMutableList()
        var nodeIndex = 0
        while (nodeIndex < trees.size) {
            nodeIndex = updateTree(trees, changedNode, nodeIndex)
            if (nodeIndex == -1) break
        }
    }

    private fun updateTree(tree: MutableList<Node>, changedNode: Node, startPosition: Int): Int{
        if (tree.size == 0) return -1

        for (i in startPosition until tree.size){
            if (tree[i].id == changedNode.id) {
                tree[i] = changedNode
                nodeViewModel.updateNode(tree[i].id, tree[i].nodes)
                return i+1
            }
            if (updateTree(tree[i].nodes, changedNode, 0) > 0) {
                nodeViewModel.updateNode(tree[i].id, tree[i].nodes)
                return i+1
            }
        }
        return -1
    }

    companion object {

        const val ARG_ID = ""

        @JvmStatic
        fun newInstance(id: Int, type: NodeType) =
            RelationshipsFragment(type).apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID, id)
                }
            }
    }
}