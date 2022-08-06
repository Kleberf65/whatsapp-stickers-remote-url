package br.klapps.stickers.utils;

public interface ItemClickLister<T>{
    void onDownloadClick(T t);
    void onAddedToWhatsappClick(T t);
}
