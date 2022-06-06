package androidtown.org.moblieteam.board

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidtown.org.moblieteam.R
import androidtown.org.moblieteam.utils.FBAuth

class BoardListLVAdapter(val boardList : MutableList<BoardModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return boardList.size
    }

    override fun getItem(p0: Int): Any {
        return boardList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        var view = p1
        view = LayoutInflater.from(p2?.context).inflate(R.layout.board_list_item, p2, false)

        val itemLinearLayoutView = view?.findViewById<LinearLayout>(R.id.itemView)
        if(boardList[p0].uid.equals(FBAuth.getUid())){
            itemLinearLayoutView?.setBackgroundColor(Color.parseColor("#ffa500"))
        }

        val title = view?.findViewById<TextView>(R.id.titleArea)
        title!!.text = boardList[p0].title

        val content = view?.findViewById<TextView>(R.id.contentArea)
        content!!.text = boardList[p0].content

        val time = view?.findViewById<TextView>(R.id.timeArea)
        time!!.text = boardList[p0].time

        return view!!
    }

}
