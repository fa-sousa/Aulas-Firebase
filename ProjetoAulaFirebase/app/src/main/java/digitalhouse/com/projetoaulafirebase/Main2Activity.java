package digitalhouse.com.projetoaulafirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private RecyclerView artists;

    private ArtistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        artists = findViewById(R.id.artists);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        artists.setLayoutManager(layout);
        adapter = new ArtistAdapter(this);
        artists.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layout.getOrientation());
        artists.addItemDecoration(dividerItemDecoration);
        DatabaseReference artistsRef = database.getReference("artists");

        artistsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> snapshotIterable = dataSnapshot.getChildren();
                for (DataSnapshot artist : snapshotIterable) {
                    adapter.addArtist(artist.getValue(Artist.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Main2Activity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }
}
