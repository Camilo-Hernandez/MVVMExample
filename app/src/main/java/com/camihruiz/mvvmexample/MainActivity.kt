package com.camihruiz.mvvmexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.camihruiz.mvvmexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	
	private lateinit var mainBinding: ActivityMainBinding
	private lateinit var mainViewModel: MainViewModel
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		mainBinding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(mainBinding.root)
		
		// instancia del viewModel se hace con el provider
		mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
		
		// Cómo observar Opción 1:
		
		mainViewModel.totalDone.observe(this) { result ->
			mainBinding.totalTextView.text = result.toString()
		}
		
		mainViewModel.msgDone.observe(this) { result ->
			Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
		}
		
		// Cómo observar Opción 2:
		
		val totalObserver = Observer<Int> { result ->
			mainBinding.totalTextView.text = result.toString()
		}
		
		mainViewModel.totalDone.observe(this, totalObserver)
		
		with(mainBinding){
			calculateButton.setOnClickListener {
				// Automaticamente se envían los datos al ViewModel
				mainViewModel.realizarSuma(
					number1InputText.text.toString(),
					number2InputText.text.toString()
				)
			}
		}
	}
	

}