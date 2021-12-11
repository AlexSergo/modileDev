package viewAdapters

import android.graphics.Color
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.myapplication6.databinding.FragmentParentRelationshipsBinding

import model.Node
import view.NodeType

interface IRelationshipClickListener{
    fun onRelationshipClick(parent: Node, child: Node)
}

class RelationshipsRecyclerViewAdapter(
    private val values: List<Node>,
    private val currentNode: Node,
    private val nodeType: NodeType,
    private var relationshipClickListener: IRelationshipClickListener
) : RecyclerView.Adapter<RelationshipsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentParentRelationshipsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        when(nodeType){
            NodeType.Parent -> showRelationships(holder.relationship, child = item, parent = currentNode)
            NodeType.Child -> showRelationships(holder.relationship, child = currentNode, parent = item)
        }
    }

    private fun showRelationships(relationship: TextView, child: Node, parent: Node){
        var isRelationship = false
        if (parent.nodes.contains(child)) {
            relationship.setBackgroundColor(Color.GREEN)
            isRelationship = true
        }

        relationship.setOnClickListener {
            setRelationship(relationship, child, parent, isRelationship)
            isRelationship = !isRelationship
        }
        relationship.text = "id:" + parent.id + " value:" + parent.value + " --- " +
                "id:" + child.id + " value:" + child.value
    }

    private fun setRelationship(relationship: TextView, child: Node, parent: Node,  isRelationship: Boolean){
        if (!isRelationship) {
            relationship.setBackgroundColor(Color.GREEN)
            parent.nodes.add(child)
            child.hasParent = true
            relationshipClickListener.onRelationshipClick(parent, child)
        }
        else {
            relationship.setBackgroundColor(Color.WHITE)
            parent.nodes.remove(child)
            child.hasParent = false
            relationshipClickListener.onRelationshipClick(parent, child)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentParentRelationshipsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val relationship: TextView = binding.itemRelationship
    }

}
