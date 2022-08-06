package br.klapps.stickers.model;

public class StickerModel {

    private String imageUrl;

    public StickerModel() {
    }

    public StickerModel(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
