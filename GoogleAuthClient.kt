package com.nareshtech.notetakingapp

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await

class GoogleAuthClient(
    private val context: Context
) {
    private val tag = "GoogleAuthClient"
    private val credentialManager = CredentialManager.create(context)
    private val firebaseAuth = FirebaseAuth.getInstance()

    suspend fun signIn(): Boolean{
        if(isSignedIn()){
            return true
        }

        try{
            val result = buildCredentialRequest()
            return handleSignIn(result)
        }catch (e: Exception){
            e.printStackTrace()
            if(e is CancellationException) throw  e
            println(tag+"signIn error:${e.message}")
        }
        return false
    }

    private suspend fun handleSignIn(response: GetCredentialResponse): Boolean {
        val credential = response.credential

        if(credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ){
            try{
                val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

                println(tag+"name: ${tokenCredential.displayName}")
                println(tag+"email: ${tokenCredential.id}")
                println(tag+"image: ${tokenCredential.profilePictureUri}")

                val authCredential = GoogleAuthProvider.getCredential(
                    tokenCredential.idToken , null
                )

                val authResult = firebaseAuth.signInWithCredential(authCredential).await()

                return authResult.user!=null

            }catch (e: Exception){
                return false
            }
        }
        return false
    }

    private suspend fun buildCredentialRequest(): GetCredentialResponse{
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(
                        "284023556581-q2tv2rcv3jalppqctmodpps9emn4gf7f.apps.googleusercontent.com"
                    )
                    .setAutoSelectEnabled(false)
                    .build()
            ).build()

        return credentialManager
            .getCredential(request = request,context = context)
    }

    suspend fun signOut(){
        credentialManager.clearCredentialState(
            ClearCredentialStateRequest()
        )

        firebaseAuth.signOut()
    }

    fun isSignedIn(): Boolean{
        if(firebaseAuth.currentUser!=null){
            println(tag+"Already Signed In")
            return true
        }
        return false
    }
}