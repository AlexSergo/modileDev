package viewAdapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication6.R
import com.example.myapplication6.databinding.NodeItemBinding
import model.Node
import java.util.*

interface INodeClickListener{
    fun showRelationshipActivity(id: Int)
}

class NodeListAdapter (private val nodeClickListener: INodeClickListener)
    : RecyclerView.Adapter<NodeListAdapter.NodeViewHolder>() {

    private var nodes: MutableList<Pair<Node, Color>> = mutableListOf()

    fun setNodes(nodes: List<Pair<Node, Color>>){
        this.nodes.clear()
        this.nodes.addAll(nodes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NodeItemBinding.inflate(inflater, parent, false)
        return NodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NodeViewHolder, position: Int) {
        var currentNode = nodes[position].first
        var nodeColor = nodes[position].second
        holder.nodeTextView.text = "id: " + currentNode.id + " value: " + currentNode.value
        holder.nodeTextView.setBackgroundColor(nodeColor.toArgb())

        holder.nodeTextView.setOnClickListener{
            nodeClickListener.showRelationshipActivity(currentNode.id)
        }
    }

    override fun getItemCount(): Int {
        return nodes.size
    }

    inner class NodeViewHolder(binding: NodeItemBinding) : RecyclerView.ViewHolder(binding.root){
        var nodeTextView: TextView = binding.nodeTextView
    }
}