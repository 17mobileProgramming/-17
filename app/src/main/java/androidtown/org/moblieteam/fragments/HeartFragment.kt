package androidtown.org.moblieteam.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidtown.org.moblieteam.MyPage
import androidtown.org.moblieteam.R
import androidtown.org.moblieteam.contentsList.BookmarkRVAdapter
import androidtown.org.moblieteam.contentsList.ContentModel
import androidtown.org.moblieteam.databinding.FragmentAccountBinding
import androidtown.org.moblieteam.databinding.FragmentHeartBinding
import androidtown.org.moblieteam.setting.SettingActivity
import androidtown.org.moblieteam.utils.FBAuth
import androidtown.org.moblieteam.utils.FBRef
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

/**
 * A simple [Fragment] subclass.
 * Use the [accountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HeartFragment : Fragment() {
    private lateinit var binding:FragmentHeartBinding

    val bookmarkIdList = mutableListOf<String>()
    val items = ArrayList<ContentModel>()
    val itemKeyList = ArrayList<String>()

    lateinit var rvAdapter: BookmarkRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_heart, container, false)

        // 2
        getBookmarkData()
        // 3
        rvAdapter = BookmarkRVAdapter(requireContext(), items, itemKeyList, bookmarkIdList)

        val rv : RecyclerView = binding.bookmarkRV
        rv.adapter = rvAdapter

        rv.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.recipeTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_heartFragment_to_homeFragment)

        }

        binding.tipTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_heartFragment_to_searchFragment)

        }

        binding.talkTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_heartFragment_to_postFragment)

        }
        binding.bookmarkTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_heartFragment_to_accountFragment)

        }
        return binding.root
    }

    private fun getCategoryData() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for(dataModel in dataSnapshot.children){
                   val item = dataModel.getValue(ContentModel::class.java)

                    if(bookmarkIdList.contains(dataModel.key.toString())){
                        items.add(item!!)
                        itemKeyList.add(dataModel.key.toString())
                    }
                }
                rvAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.d("ContentsListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.category1.addValueEventListener(postListener)
        FBRef.category2.addValueEventListener(postListener)
        FBRef.category3.addValueEventListener(postListener)
        FBRef.category4.addValueEventListener(postListener)
    }

    private fun getBookmarkData() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for(dataModel in dataSnapshot.children){
                    bookmarkIdList.add(dataModel.key.toString())
                }
                getCategoryData()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.d("ContentsListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)
    }

}