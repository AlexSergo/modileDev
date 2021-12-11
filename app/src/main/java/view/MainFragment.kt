package view

import android.content.Intent
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication6.databinding.MainFragmentBinding
import viewmodel.MainViewModel
import model.Node
import utils.ID
import utils.KEY_VALUE
import utils.RESULT_OK
import viewAdapters.INodeClickListener
import viewAdapters.NodeListAdapter
import viewmodel.NodeViewModel
import viewmodel.NodeViewModelFactory
import viewmodel.RepositoryInitializer

class MainFragment() : Fragment(){

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NodeListAdapter
    private var nodes: MutableList<Pair<Node, Color>> = mutableListOf()

    private var nodeViewModel: NodeViewModel? = null
    private var viewModel: MainViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater)
        recyclerView = binding.recyclerView
        adapter = NodeListAdapter(object : INodeClickListener {
            override fun showRelationshipActivity(id: Int) {
                val intent = Intent (this@MainFragment.context, RelationshipsActivity::class.java)
                intent.putExtra(ID, id)
                startActivityForResult(intent, RESULT_OK)
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter

        val viewModelFactory = NodeViewModelFactory(RepositoryInitializer.getRepository(requireContext()))
        nodeViewModel = ViewModelProvider(this, viewModelFactory)
            .get(NodeViewModel::class.java)

        nodeViewModel?.init()
        initMapOfNodes()

        return binding.root
    }

    private fun initMapOfNodes(){
        var listNodes = nodeViewModel?.getAllNodes()
        listNodes?.let {
            nodes.clear()
            it.forEach { node->
                nodes.add(Pair(node, Color.valueOf(Color.WHITE)))
            }
            setNodeColors(it)
            adapter.setNodes(nodes)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null)
            return
        initMapOfNodes()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    fun addNewNode(intent: Intent) {
        val node = Node(
            id = nodes.size + 1,
            value = intent.getStringExtra(KEY_VALUE).toString().toInt(),
            nodes = mutableListOf(),
            hasParent = false)

        nodeViewModel?.insertNode(node)
        nodes.add(Pair(node, Color.valueOf(Color.WHITE)))
        adapter.setNodes(nodes)
    }

    private fun setNodeColors(list: List<Node>) {
        var excludedNodes = mutableListOf<Node>()

        var roots = getRoots()
        for (root in roots) {
            setColors(root.nodes, 0, excludedNodes)
        }
    }

    private fun getRoots(): List<Node>{
        var roots: MutableList<Node> = mutableListOf()
        for (node in nodes){
            if (!node.first.hasParent && node.first.nodes.size != 0){
                nodes[nodes.indexOf(node)] = Pair(node.first, Color.valueOf(Color.CYAN))
                roots.add(node.first)
            }
        }
        return roots
    }

    private fun setColors(list: List<Node>, i: Int, excludedNodes: MutableList<Node>){
        var colors = arrayOf<Int>(Color.YELLOW,Color.RED, Color.GREEN, Color.BLUE, Color.GRAY)
        for (n in list){
                if (!excludedNodes.contains(n)) {
                    for (it in nodes){
                        if (it.first == n) {
                            if (n.hasParent || n.nodes.size > 0)
                                nodes[nodes.indexOf(it)] = Pair(n, Color.valueOf(colors[i]))
                            else
                                nodes[nodes.indexOf(it)] =
                                    Pair(it.first, Color.valueOf(Color.WHITE))
                            excludedNodes.add(n)
                            break
                        }
                    }
                setColors(n.nodes, i + 1, excludedNodes)
            }
        }
    }
}