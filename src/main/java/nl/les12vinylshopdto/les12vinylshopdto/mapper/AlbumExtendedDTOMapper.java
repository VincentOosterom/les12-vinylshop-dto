package nl.les12vinylshopdto.les12vinylshopdto.mapper;

import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumExtendedResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.entities.AlbumEntity;
import org.springframework.stereotype.Component;

@Component
public class AlbumExtendedDTOMapper extends AlbumDTOMapper {

    private final StockDTOMapper stockDTOMapper;

    public AlbumExtendedDTOMapper(PublisherDTOMapper publisherDtoMapper , StockDTOMapper stockDTOMapper, GenreDTOMapper genreDTOMapper) {
        super(publisherDtoMapper,genreDTOMapper);
        this.stockDTOMapper = stockDTOMapper;
    }

    @Override
    public AlbumExtendedResponseDTO mapToDto(AlbumEntity model) {
        AlbumExtendedResponseDTO result = new AlbumExtendedResponseDTO();
        super.mapToDto(model, result);
        result.setStock(stockDTOMapper.mapToDto(model.getStockItems()));
        return result;
    }
}
