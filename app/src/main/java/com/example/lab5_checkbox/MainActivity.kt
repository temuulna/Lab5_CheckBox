package com.example.lab5_checkbox


import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var cbObj1: CheckBox
    lateinit var cbObj2: CheckBox
    lateinit var cbObj3: CheckBox
    lateinit var cbObj4: CheckBox
    lateinit var btnObj: Button
    lateinit var tvObj: TextView
    var flag = false   // false = 결과보기 상태, true = 재시작하기 상태

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cbObj1 = findViewById(R.id.checkBox1)
        cbObj2 = findViewById(R.id.checkBox2)
        cbObj3 = findViewById(R.id.checkBox3)
        cbObj4 = findViewById(R.id.checkBox4)
        btnObj = findViewById(R.id.button)
        tvObj = findViewById(R.id.textViewResult)

        // 체크박스 체크 시 선택한 물건 이름을 토스트로 출력
        val listener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                when (buttonView.id) {
                    R.id.checkBox1 -> Toast.makeText(applicationContext, "소방차를 선택했습니다.", Toast.LENGTH_LONG).show()
                    R.id.checkBox2 -> Toast.makeText(applicationContext, "거품기를 선택했습니다.", Toast.LENGTH_LONG).show()
                    R.id.checkBox3 -> Toast.makeText(applicationContext, "청진기를 선택했습니다.", Toast.LENGTH_LONG).show()
                    R.id.checkBox4 -> Toast.makeText(applicationContext, "장갑을 선택했습니다.", Toast.LENGTH_LONG).show()
                }
            }
        }
        cbObj1.setOnCheckedChangeListener(listener)
        cbObj2.setOnCheckedChangeListener(listener)
        cbObj3.setOnCheckedChangeListener(listener)
        cbObj4.setOnCheckedChangeListener(listener)

        // 결과보기 / 재시작하기 버튼
        btnObj.setOnClickListener {
            if (!flag) {
                // 정답: 소방차(1) + 장갑(4) 만 선택
                if (cbObj1.isChecked && cbObj4.isChecked && !cbObj2.isChecked && !cbObj3.isChecked)
                    tvObj.text = "맞았습니다"
                else
                    tvObj.text = "틀렸습니다"
                btnObj.text = "재시작하기"
                flag = true
            } else {
                cbObj1.isChecked = false
                cbObj2.isChecked = false
                cbObj3.isChecked = false
                cbObj4.isChecked = false
                btnObj.text = "결과보기"
                tvObj.text = "소방관의 용품을 선택해주세요"
                flag = false
            }
        }
    }
}