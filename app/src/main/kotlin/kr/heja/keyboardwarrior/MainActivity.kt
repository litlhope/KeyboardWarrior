package kr.heja.keyboardwarrior

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.widget.EditText
import android.widget.TextView
import name.fraser.neil.plaintext.diff_match_patch
import java.util.*

class MainActivity : AppCompatActivity(), View.OnKeyListener {
	val TAG = "MainActivity"

	var tvQuestion: TextView? = null
	var tvAnswer: TextView? = null
	var wvAnswer: WebView? = null
	var etAnswer: EditText? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		init()
	}

	fun init() {
		tvQuestion = findViewById(R.id.tvQuestion) as TextView
		tvAnswer = findViewById(R.id.tvAnswer) as TextView
		wvAnswer = findViewById(R.id.wvAnswer) as WebView
		etAnswer = findViewById(R.id.etAnswer) as EditText

		tvQuestion?.text = resources.getString(R.string.question_sample)
		etAnswer?.setOnKeyListener(this)
	}

	fun checkAnswer() {
		tvAnswer?.text = etAnswer?.text
		etAnswer?.setText("")

		var strQuestion = tvQuestion?.text.toString()
		var strAnswer = tvAnswer?.text.toString()

		var dmp = diff_match_patch()
		var diffList: LinkedList<diff_match_patch.Diff> = dmp.diff_main(strQuestion, strAnswer)

		var html = dmp.diff_prettyHtml(diffList)

		Log.d(TAG, html)
		wvAnswer?.loadData(html, "text/html; charset=UTF-8", "UTF-8")

		for (diff: diff_match_patch.Diff in diffList) {
			Log.d(TAG, diff.toString())
			diff.operation
		}
//		tvAnswer?.text = Html.fromHtml(html)
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