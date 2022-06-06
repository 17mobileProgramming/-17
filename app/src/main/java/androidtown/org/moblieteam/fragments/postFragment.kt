package androidtown.org.moblieteam.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidtown.org.moblieteam.R
import androidtown.org.moblieteam.board.BoardInsideActivity
import androidtown.org.moblieteam.board.BoardListLVAdapter
import androidtown.org.moblieteam.board.BoardModel
import androidtown.org.moblieteam.board.BoardWriteActivity
import androidtown.org.moblieteam.contentsList.ContentModel
import androidtown.org.moblieteam.contentsList.ContentRVAdapter
import androidtown.org.moblieteam.databinding.FragmentPostBinding
import androidtown.org.moblieteam.utils.FBRef
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

/**
 * A simple [Fragment] subclass.
 * Use the [postFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class postFragment : Fragment() {

    private lateinit var binding:FragmentPostBinding

    private val boardDataList = mutableListOf<BoardModel>()
    private val boardKeyList = mutableListOf<String>()

    private lateinit var boardRVAdapter: BoardListLVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_post, container, false)

        boardRVAdapter = BoardListLVAdapter(boardDataList)
        binding.boardListView.adapter = boardRVAdapter

        //first
        binding.boardListView.setOnItemClickListener { parent, view, position, id ->
            /*val intent = Intent(context, BoardInsideActivity::class.java)
            intent.putExtra("title", boardDataList[position].title)
            intent.putExtra("content", boardDataList[position].content)
            intent.putExtra("time", boardDataList[position].time)
            startActivity(intent)
        */

            //second
            val intent = Intent(context, BoardInsideActivity::class.java)
            intent.putExtra("key", boardKeyList[position])
            startActivity(intent)

        }

        binding.writeBtn.setOnClickListener{
            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)
        }

       binding.recipeTap.setOnClickListener {
           it.findNavController().navigate(R.id.action_postFragment_to_homeFragment)
       }

        binding.tipTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_postFragment_to_searchFragment)

        }

        binding.bookmarkTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_postFragment_to_accountFragment)

        }

        binding.heartTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_postFragment_to_heartFragment)

        }

        getFBBoardData()

        return binding.root
    }


    private fun getFBBoardData(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                boardDataList.clear()

                for (dataModel in snapshot.children){
                    val item = dataModel.getValue(BoardModel::class.java)
                    boardDataList.add(item!!)
                    boardKeyList.add(dataModel.key.toString())
                }

                boardKeyList.reverse()
                boardDataList.reverse()
                boardRVAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        FBRef.boardRef.addValueEventListener(postListener)
    }

}