package androidtown.org.moblieteam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidtown.org.moblieteam.comment.CommentModel
import androidtown.org.moblieteam.contentsList.ContentModel
import androidtown.org.moblieteam.contentsList.ContentsListActivity
import androidtown.org.moblieteam.databinding.ActivityJoinBinding
import androidtown.org.moblieteam.utils.FBAuth
import androidtown.org.moblieteam.utils.FBRef
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Join : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding : ActivityJoinBinding

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)

        database = Firebase.database.reference

        binding.joinBtn.setOnClickListener {

            var isGoToJoin = true

            val email = binding.emailArea.text.toString()
            val password1 = binding.passwordArea1.text.toString()
            val password2 = binding.passwordArea2.text.toString()
            val nickname = binding.nickname.text.toString()
            val introduce = binding.introduce.text.toString()



            // 저기 값이 비어있는지 확인
            if(email.isEmpty()) {
                Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if(password1.isEmpty()) {
                Toast.makeText(this, "Password1을 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if(password2.isEmpty()) {
                Toast.makeText(this, "Password2을 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            // 비밀번호 2개가 같은지 확인
            if(!password1.equals(password2)) {
                Toast.makeText(this, "비밀번호를 똑같이 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if(isGoToJoin) {

                auth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()


                        FBRef.userRef
                            .push()
                            .setValue(
                                UserModel(
                                    email, nickname, introduce
                                )
                            )

                        val intent = Intent(this, MyPage::class.java)
                        intent.putExtra("email", email)


                        val intent2 = Intent(this, MainActivity::class.java)
                        intent2.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent2)


                    } else {
                        Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                    }
                }
            }



        }


    }
}