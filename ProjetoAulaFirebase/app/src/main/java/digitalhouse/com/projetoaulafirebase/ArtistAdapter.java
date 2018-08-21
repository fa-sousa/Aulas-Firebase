package digitalhouse.com.projetoaulafirebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistHolder> {

    private final Context context;

    private List<Artist> artists = new ArrayList<>();

    ArtistAdapter(Context context) {
        this.context = context;
    }

    public void addArtist(Artist artist) {
        this.artists.add(artist);
        notifyItemInserted(artists.size() - 1);
    }

    @NonNull
    @Override
    public ArtistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.artist_item, parent, false);

        return new ArtistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistHolder holder, int position) {
        Artist artist = artists.get(position);

        holder.name.setText(artist.getName());
        holder.nationality.setText(artist.getNationality());
        holder.influencedBy.setText(artist.getInfluencedBy());
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    class ArtistHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView nationality;
        TextView influencedBy;

        ArtistHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            nationality = itemView.findViewById(R.id.nationality);
            influencedBy = itemView.findViewById(R.id.influencedBy);
        }
    }
}