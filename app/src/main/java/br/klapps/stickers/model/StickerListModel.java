package br.klapps.stickers.model;

import java.util.List;

public class StickerListModel {

    private String title;
    private String identifier;
    private String tryIcon = "https://raw.githubusercontent.com/WhatsApp/stickers/main/Android/app/src/main/assets/1/tray_Cuppy.png";
    private boolean animated;
    private List<StickerModel> stickerModels;

    public StickerListModel(boolean animated) {
        this.animated = animated;
    }

    public StickerListModel(String title, String identifier, List<StickerModel> stickerModels) {
        this.title = title;
        this.identifier = identifier;
        this.stickerModels = stickerModels;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTryIcon() {
        return tryIcon;
    }

    public void setTryIcon(String tryIcon) {
        this.tryIcon = tryIcon;
    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public List<StickerModel> getStickerModels() {
        return stickerModels;
    }

    public void setStickerModels(List<StickerModel> stickerModels) {
        this.stickerModels = stickerModels;
    }

    public List<StickerModel> getStickers() {
        return stickerModels;
    }

    public void setStickers(List<StickerModel> stickerModels) {
        this.stickerModels = stickerModels;
    }
}
