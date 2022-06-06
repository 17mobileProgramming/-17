package androidtown.org.moblieteam.contentsList

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidtown.org.moblieteam.R
import androidtown.org.moblieteam.utils.FBAuth
import androidtown.org.moblieteam.utils.FBRef
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ContentRVAdapter(val context : Context,
                       val items:ArrayList<ContentModel>,
                       val keyList : ArrayList<String>,
                       val bookmarkIdList : MutableList<String>)
    : RecyclerView.Adapter<ContentRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ContentRVAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position], keyList[position])
    }
    //아이템의 갯수가 몇개인지
    override fun getItemCount(): Int {
        return items.size
    }
    //content_rv_item.xml에 데이터를 넣어주는 역할
    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        fun bindItems(item : ContentModel, key : String){

            itemView.setOnClickListener {
                Toast.makeText(context, item.title, Toast.LENGTH_LONG).show()
                val intent = Intent(context, ContentShowActivity::class.java)
                intent.putExtra("url", item.webUrl)
                itemView.context.startActivity(intent)
            }

            val contentTitle = itemView.findViewById<TextView>(R.id.textArea)

            val imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)

            val bookmarkArea = itemView.findViewById<ImageView>(R.id.bookMarkArea)

            if(bookmarkIdList.contains(key)) {
                bookmarkArea.setImageResource(R.drawable.heart_red)
            } else {
                bookmarkArea.setImageResource(R.drawable.heart_white)
            }

            bookmarkArea.setOnClickListener {


                if(bookmarkIdList.contains(key)) {
                    FBRef.bookmarkRef
                        .child(FBAuth.getUid())
                        .child(key)
                        .removeValue()
                    Toast.makeText(context, "좋아요 제거됨", Toast.LENGTH_LONG).show()

                } else {
                    FBRef.bookmarkRef
                        .child(FBAuth.getUid())
                        .child(key)
                        .setValue(BookmarkModel(true))
                    Toast.makeText(context, "좋아요 추가됨", Toast.LENGTH_LONG).show()
                }
            }

            contentTitle.text = item.title


            Glide.with(context)
                .load(item.imageUrl)
                .into(imageViewArea)

        }
    }
}