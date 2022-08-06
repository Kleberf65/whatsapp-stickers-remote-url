package br.klapps.stickers.utils;

import java.util.ArrayList;
import java.util.List;

import br.klapps.stickers.model.StickerModel;
import br.klapps.stickers.model.StickerListModel;

public class StickersMock {

    public static List<StickerListModel> getStickerList() {
        List<StickerListModel> tempList = new ArrayList<>();

        List<StickerModel> catStickerModels = new ArrayList<>();
        catStickerModels.add(new StickerModel("https://www.zapfigurinhas.com/img/webp/qaRJyr.webp"));
        catStickerModels.add(new StickerModel("https://www.zapfigurinhas.com/img/webp/hFgqmW.webp"));
        catStickerModels.add(new StickerModel("https://www.zapfigurinhas.com/img/webp/0cRDaE.webp"));
        catStickerModels.add(new StickerModel("https://www.zapfigurinhas.com/img/webp/hkMkzm.webp"));
        catStickerModels.add(new StickerModel("https://www.zapfigurinhas.com/img/webp/LPaLdh.webp"));

        tempList.add(new StickerListModel("Gatos Engraçados", "1234432", catStickerModels));

        List<StickerModel> dogStickerModels = new ArrayList<>();
        dogStickerModels.add(new StickerModel("https://www.zapfigurinhas.com/img/webp/dz2C5A.webp"));
        dogStickerModels.add(new StickerModel("https://www.zapfigurinhas.com/img/webp/smoTXn.webp"));
        dogStickerModels.add(new StickerModel("https://www.zapfigurinhas.com/img/webp/xghh4i.webp"));
        dogStickerModels.add(new StickerModel("https://www.zapfigurinhas.com/img/webp/stHKPK.webp"));

        tempList.add(new StickerListModel("Memes de Cachorros", "1234567", dogStickerModels));

        List<StickerModel> pandaStickerModels = new ArrayList<>();
        pandaStickerModels.add(new StickerModel("https://www.zapfigurinhas.com/img/webp/yTBu7I.webp"));
        pandaStickerModels.add(new StickerModel("https://www.zapfigurinhas.com/img/webp/l7wKZS.webp"));
        pandaStickerModels.add(new StickerModel("https://www.zapfigurinhas.com/img/webp/TXMoZ6.webp"));
        pandaStickerModels.add(new StickerModel("https://www.zapfigurinhas.com/img/webp/bZSEd6.webp"));
        pandaStickerModels.add(new StickerModel("https://www.zapfigurinhas.com/img/webp/pyIXg9.webp"));
        pandaStickerModels.add(new StickerModel("https://www.zapfigurinhas.com/img/webp/zIWzV9.webp"));

        tempList.add(new StickerListModel("Pandas", "7654321", pandaStickerModels));

        return tempList;
    }
}
