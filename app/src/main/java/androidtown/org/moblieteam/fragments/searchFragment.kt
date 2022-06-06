package androidtown.org.moblieteam.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidtown.org.moblieteam.R
import androidtown.org.moblieteam.StoreActivity
import androidtown.org.moblieteam.contentsList.ContentsListActivity
import androidtown.org.moblieteam.databinding.FragmentSearchBinding
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

/**
 * A simple [Fragment] subclass.
 * Use the [searchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class searchFragment : Fragment() {

    private lateinit var binding : FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search, container, false)

        binding.korea.setOnClickListener {
            val intent = Intent(context, ContentsListActivity::class.java)
            intent.putExtra("category","category1")
            startActivity(intent)
        }

        binding.china.setOnClickListener {
            val intent = Intent(context, ContentsListActivity::class.java)
            intent.putExtra("category","category2")
            startActivity(intent)
        }

        binding.japan.setOnClickListener {
            val intent = Intent(context, ContentsListActivity::class.java)
            intent.putExtra("category","category3")
            startActivity(intent)
        }

        binding.usa.setOnClickListener {
            val intent = Intent(context, ContentsListActivity::class.java)
            intent.putExtra("category","category4")
            startActivity(intent)
        }

        binding.store.setOnClickListener {
            val intent = Intent(context, StoreActivity::class.java)
            startActivity(intent)
        }

        binding.recipeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_searchFragment_to_homeFragment)
        }

        binding.talkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_searchFragment_to_postFragment)

        }

        binding.bookmarkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_searchFragment_to_accountFragment)

        }
        binding.heartTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_searchFragment_to_heartFragment)

        }
        return binding.root
    }


}