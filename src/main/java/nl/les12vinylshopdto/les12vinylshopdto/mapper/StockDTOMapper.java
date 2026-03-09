package nl.les12vinylshopdto.les12vinylshopdto.mapper;

import nl.les12vinylshopdto.les12vinylshopdto.dto.stock.StockRequestDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.stock.StockResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.entities.StockEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockDTOMapper implements DTOMapper <StockResponseDTO, StockRequestDTO, StockEntity>{

    @Override
    public StockResponseDTO mapToDto(StockEntity entity) {
        return new StockResponseDTO(
                entity.getId(),
                entity.getCondition(),
                entity.getPrice()
        );
    }

    @Override
    public List<StockResponseDTO> mapToDto(List<StockEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public StockEntity mapToEntity(StockRequestDTO dto) {
        StockEntity stock = new StockEntity();
        stock.setPrice(dto.price());
        stock.setCondition(dto.condition());
        return stock;
    }
}
