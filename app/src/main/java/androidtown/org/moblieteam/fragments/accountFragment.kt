package androidtown.org.moblieteam.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidtown.org.moblieteam.MyPage
import androidtown.org.moblieteam.ProfileChange
import androidtown.org.moblieteam.R
import androidtown.org.moblieteam.databinding.FragmentAccountBinding
import androidtown.org.moblieteam.setting.SettingActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

/**
 * A simple [Fragment] subclass.
 * Use the [accountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class accountFragment : Fragment() {
    private lateinit var binding:FragmentAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_account, container, false)

        binding.logoutBtn.setOnClickListener {
            val intent = Intent(context, SettingActivity::class.java)
            startActivity(intent)
        }

        binding.mypage.setOnClickListener {
            val intent = Intent(context, MyPage::class.java)
            startActivity(intent)
        }

        binding.profile.setOnClickListener {
            val intent = Intent(context, ProfileChange::class.java)
            startActivity(intent)
        }

        binding.recipeTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_accountFragment_to_homeFragment)

        }

        binding.tipTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_accountFragment_to_searchFragment)

        }

        binding.talkTap.setOnClickListener {

            it.findNavController().navigate(R.id.action_accountFragment_to_postFragment)

        }

        binding.heartTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_accountFragment_to_heartFragment)

        }
        return binding.root
    }


}