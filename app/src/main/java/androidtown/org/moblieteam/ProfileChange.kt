package androidtown.org.moblieteam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidtown.org.moblieteam.fragments.accountFragment

class ProfileChange : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_change)

        val nickname = findViewById<EditText>(R.id.emailArea)
        val intro = findViewById<EditText>(R.id.introduce)

        val nickname1 = nickname.text
        val intro1 = intro.text

        val btn = findViewById<Button>(R.id.joinBtn)

        btn.setOnClickListener {
            val intent = Intent(this, MyPage::class.java)
            intent.putExtra("nick", nickname1)
            intent.putExtra("intro", intro1)
            startActivity(intent)
        }
    }
}