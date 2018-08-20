package digitalhouse.com.projetoaulafirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {

    //copiar estas 3 linhas abaixo, não esquecendo de importar a biblioteca auth no gradle
    //é uma constante que recebe 1987 que é um numero qualquer
    private static final int RC_SIGN_IN = 1987;

    private final FirebaseAuth firebase = FirebaseAuth.getInstance();

    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) //esse default é uma string que ja vem da importacao (eu nao preciso criar)
                .requestEmail()
                .build(); //quando dou este build ele criar para nós o googleSignIn abaixo

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        //este signinbutton é o meu id do botao do google do meu xml
        //este findviewbyid me diz que vai extender de view, ou seja, a minha view onde tem o botao
        //é um padrão call back
        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(googleSignInClient.getSignInIntent(), RC_SIGN_IN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       //requestCode é aquele numero hash que eu coloquei no inicio do projeto
        //esta parte de botoes é de botoes para logar... se nao for facebook é twitter e etc
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount result = task.getResult(ApiException.class); //o google pode retornar uma excessao, pesquisar o que é excessao

                firebaseAuthWithGoogle(result);

            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    //este googleauthprovider ele fala na linguagem que o firebase entende
    //se for no facebook vai ter o facebookauthprovider ou twitteerauthprovider
    private void firebaseAuthWithGoogle(GoogleSignInAccount googleSignInAccount) {
        AuthCredential credential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);

        firebase.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = firebase.getCurrentUser();
                    Log.d("DEBUG", user.getEmail());
                }
            }
        });
    }

}
