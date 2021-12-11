package model

import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "node")
data class Node(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true) @NonNull val id: Int,
    @SerializedName("value")
    val value: Int,
    @SerializedName("nodes")
    val nodes: MutableList<Node>,
    @SerializedName("parent")
    var hasParent: Boolean) {

    fun canBeChildFor(parent: Node): Boolean{
        return (parent.id != this.id && !this.hasChild(parent)
                && (!this.hasParent || parent.hasChildInFirstGeneration(this)))
    }

    private fun hasChildInFirstGeneration(node: Node): Boolean {
        this.nodes.forEach {
            if (it == node)
                return true
        }
        return false
    }

    fun hasChild(node: Node):Boolean {
        return search(node, nodes)
    }

    private fun search(searchNode: Node, nodes: List<Node>): Boolean{
        for (n in nodes){
                if (n.id == searchNode.id || search(searchNode, n.nodes))
                    return true
        }
        return false
    }
}