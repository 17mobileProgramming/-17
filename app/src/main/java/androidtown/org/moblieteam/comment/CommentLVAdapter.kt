package androidtown.org.moblieteam.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidtown.org.moblieteam.R

class CommentLVAdapter(val commentList : MutableList<CommentModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return commentList.size
    }

    override fun getItem(p0: Int): Any {
        return commentList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view = p1
        if (view == null) {
            view = LayoutInflater.from(p2?.context).inflate(R.layout.board_list_item, p2, false)
        }

        val title = view?.findViewById<TextView>(R.id.titleArea)
        title!!.text = commentList[p0].commentTitle

        val time = view?.findViewById<TextView>(R.id.timeArea)
        time!!.text = commentList[p0].commentCreatedTime

        return view!!
    }
}