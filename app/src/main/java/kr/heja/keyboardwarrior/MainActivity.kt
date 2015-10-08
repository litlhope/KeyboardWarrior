package kr.heja.keyboardwarrior

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnKeyListener {
	val TAG = "MainActivity"

	var tvQuestion:TextView? = null
	var tvAnswer:TextView? = null
	var etAnswer:EditText? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		init()
	}

	fun init() {
		tvQuestion = findViewById(R.id.tvQuestion) as TextView
		tvAnswer = findViewById(R.id.tvAnswer) as TextView
		etAnswer = findViewById(R.id.etAnswer) as EditText

		tvQuestion?.text = resources.getString(R.string.question_sample)
		etAnswer?.setOnKeyListener(this)
	}

	fun checkAnswer() {
		tvAnswer?.text = etAnswer?.text
		etAnswer?.setText("")
	}

	override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
		if (KeyEvent.ACTION_DOWN == event?.action) {
			if (KeyEvent.KEYCODE_ENTER == keyCode) {
				checkAnswer()
			}
		}
		return false;
	}
}
