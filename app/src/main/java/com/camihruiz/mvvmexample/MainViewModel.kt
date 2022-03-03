package com.camihruiz.mvvmexample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
	// Declaración del LiveData Opción 1:
	
	// Declaración del LiveData mutable y el completo
	// La convención es poner Done al objeto que se le asigna el valor actual del mutable y se envía
	private val total : MutableLiveData<Int> = MutableLiveData()
	val totalDone : LiveData<Int> = total
	
	private val msg : MutableLiveData<String> = MutableLiveData()
	val msgDone : LiveData<String> = msg
	
	// Declaración del LiveData Opción 2, la que está en el AndroidDeveloper Site:
	/*val total: MutableLiveData<Int> by lazy {
		MutableLiveData<Int>()
	}	*/
	
	// Procesamiento de datos
	fun realizarSuma(num1: String , num2: String) {
		if (num1.isEmpty() || num2.isEmpty()){
			msg.value = "Debe digitar los 2 números"
		} else {
			total.value = num1.toInt() + num2.toInt() // total es livedata mutable
			Log.d("Suma" , total.toString()) // imprimir en el Logcat
			val suma = num1.toInt() + num2.toInt()
			if (suma % 2 == 0) msg.value = "El total es par"
			else msg.value = "El total es impar"
		}
	}
}