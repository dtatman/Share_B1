package com.example.share_b1

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Khóa để lưu và truy xuất dữ liệu từ SharedPreferences
    private val PREFS_NAME = "my_prefs"
    private val KEY_DATA = "stored_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khai báo các thành phần từ giao diện
        val editTextData = findViewById<EditText>(R.id.editTextData)
        val btnSaveData = findViewById<Button>(R.id.btnSaveData)
        val btnGetData = findViewById<Button>(R.id.btnGetData)

        // Lưu dữ liệu vào SharedPreferences khi nhấn nút "Lưu dữ liệu"
        btnSaveData.setOnClickListener {
            val inputData = editTextData.text.toString()
            if (inputData.isNotEmpty()) {
                saveDataToPreferences(inputData)
                Toast.makeText(this, "Dữ liệu đã được lưu", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show()
            }
        }

        // Lấy dữ liệu từ SharedPreferences khi nhấn nút "Lấy dữ liệu"
        btnGetData.setOnClickListener {
            val storedData = getDataFromPreferences()
            if (storedData != null) {
                Toast.makeText(this, "Dữ liệu đã lưu: $storedData", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Không có dữ liệu đã lưu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Hàm lưu dữ liệu vào SharedPreferences
    private fun saveDataToPreferences(data: String) {
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_DATA, data)
        editor.apply()
    }

    // Hàm lấy dữ liệu từ SharedPreferences
    private fun getDataFromPreferences(): String? {
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_DATA, null)
    }
}
