package nl.les12vinylshopdto.les12vinylshopdto.controllers;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import nl.les12vinylshopdto.les12vinylshopdto.dto.stock.StockRequestDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.stock.StockResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.helpers.UrlHelper;
import nl.les12vinylshopdto.les12vinylshopdto.services.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums/{albumId}/stock")
public class StockController {

    private final StockService stockService;
    private final UrlHelper urlHelper;


    public StockController(StockService stockService, UrlHelper urlHelper) {
        this.stockService = stockService;
        this.urlHelper = urlHelper;
    }

    @PostMapping()
    public ResponseEntity<StockResponseDTO> updateAlbumData(@PathVariable Long albumId, @RequestBody @Valid StockRequestDTO stockDTO) {
        StockResponseDTO responseDTO  = stockService.createStock(albumId, stockDTO);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId( responseDTO.getId())).body(responseDTO);
    }

    @GetMapping()
    public ResponseEntity<List<StockResponseDTO>> getStock(@PathVariable Long albumId) {
        List<StockResponseDTO> coverModels = stockService.findStock(albumId);
        return ResponseEntity.ok(coverModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockResponseDTO> getStock(@PathVariable Long albumId, @PathVariable Long id) {
        StockResponseDTO coverModel = stockService.findStock(albumId, id);
        return ResponseEntity.ok(coverModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockResponseDTO> updateStock(@PathVariable Long albumId, @PathVariable Long id, @RequestBody  @Valid StockRequestDTO coverDTO) {
        StockResponseDTO responseDTO  = stockService.updateStock(albumId, id, coverDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long albumId, @PathVariable Long id) {
        stockService.deleteStock(albumId, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}