package br.klapps.stickers.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

import br.klapps.stickers.R;
import br.klapps.stickers.model.StickerListModel;
import br.klapps.stickers.utils.ItemClickLister;
import br.klapps.stickers.utils.StickersMock;

public class StickerListAdapter extends RecyclerView.Adapter<StickerListAdapter.ViewHolder> {

    private final List<StickerListModel> list =  StickersMock.getStickerList();
    private final ItemClickLister<StickerListModel> clickLister;

    public StickerListAdapter(ItemClickLister<StickerListModel> clickLister) {
        this.clickLister = clickLister;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sticker_list, parent, false);
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

        private final TextView tvTitle;
        private final MaterialButton btnDownload, btnAdd;
        private final RecyclerView rvStickers;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            rvStickers = itemView.findViewById(R.id.rv_stickers);
            btnDownload = itemView.findViewById(R.id.btn_download);
            btnAdd = itemView.findViewById(R.id.btn_add);

        }

        private void bindView(StickerListModel stickerListModel) {
            tvTitle.setText(stickerListModel.getTitle());
            rvStickers.setAdapter(new StickerAdapter(stickerListModel.getStickers()));

            btnDownload.setOnClickListener(v -> {
                if(clickLister!=null){
                    clickLister.onDownloadClick(stickerListModel);
                }
            });
            btnAdd.setOnClickListener(v -> {
                if(clickLister!=null){
                    clickLister.onAddedToWhatsappClick(stickerListModel);
                }
            });
        }
    }
}
