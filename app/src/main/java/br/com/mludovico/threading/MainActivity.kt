package br.com.mludovico.threading

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            launchAstroTask()
        }
    }

    private fun showData(list: List<AstroPeople>?) {
        text_view.text = ""
        list?.forEach { person ->
            text_view.append("${person.name} - ${person.craft} \n\n")
        }
    }

    private fun setProgressVisibility(visible: Boolean) {
        progress_bar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun launchAstroTask() {
        val task = TaskAstros()
        task.execute()
    }

    inner class  TaskAstros(): AsyncTask<Void, Int, List<AstroPeople>>() {

        private val repository = AstrosRepository()

        override fun onPreExecute() {
            super.onPreExecute()
            setProgressVisibility(true)
        }

        override fun doInBackground(vararg params: Void?): List<AstroPeople> {
            return repository.loadData()
        }

        override fun onPostExecute(result: List<AstroPeople>?) {
            super.onPostExecute(result)
            setProgressVisibility(false)
            showData(result)
        }
    }
}