package br.klapps.stickers.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.klapps.stickers.R;
import br.klapps.stickers.model.StickerModel;

public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.ViewHolder> {

    private final List<StickerModel> list;

    public StickerAdapter(List<StickerModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sticker, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgSticker;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSticker = itemView.findViewById(R.id.img_sticker);
        }

        private void bindView(StickerModel stickerModel) {
            Picasso.get().load(stickerModel.getImageUrl()).into(imgSticker);
        }
    }
}
