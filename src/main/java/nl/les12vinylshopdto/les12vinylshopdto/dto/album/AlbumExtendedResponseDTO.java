package nl.les12vinylshopdto.les12vinylshopdto.dto.album;

import nl.les12vinylshopdto.les12vinylshopdto.dto.stock.StockResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class AlbumExtendedResponseDTO extends AlbumResponseDTO  {
    private List<StockResponseDTO> stock = new ArrayList<>();

    public List<StockResponseDTO> getStock() {
        return stock;
    }

    public void setStock(List<StockResponseDTO> stock) {
        this.stock = stock;
    }


}
