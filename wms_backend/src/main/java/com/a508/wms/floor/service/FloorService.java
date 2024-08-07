package com.a508.wms.floor.service;

import com.a508.wms.floor.domain.Floor;
import com.a508.wms.floor.dto.FloorResponseDto;
import com.a508.wms.floor.exception.FloorException;
import com.a508.wms.floor.mapper.FloorMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@Slf4j
@RequiredArgsConstructor
public class FloorService {

    private final FloorModuleService floorModuleService;

    /**
     * location이 가지고 있는 층 전부 조회
     *
     * @param locationId: 로케이션 iD
     * @return FloorDto List
     */
    public List<FloorResponseDto> findAllByLocationId(Long locationId) throws FloorException {
        log.info("[Service] find all Floors by locationId: {}", locationId);
        try {
            List<Floor> floors = floorModuleService.findAllByLocationId(locationId);
            return floors.stream()
                .map(FloorMapper::toFloorResponseDto)
                .collect(Collectors.toList());
        } catch (FloorException.NotFoundException e) {
            throw new FloorException.NotFoundException(locationId);
        } catch (FloorException.DeletedException e) {
            throw new FloorException.DeletedException(locationId);
        } catch (FloorException.NotFoundDefaultFloorException e) {
            throw new FloorException.NotFoundDefaultFloorException(locationId);
        } catch (FloorException.InvalidExportType e) {
            throw new FloorException.InvalidExportType(locationId);
        }
    }

    /**
     * 층 단일 조회
     *
     * @param id: 층 id
     * @return FloorDto
     */
    public FloorResponseDto findById(@PathVariable Long id) throws FloorException {
        log.info("[Service] find Floor by id: {}", id);
        try {
            Floor floor = floorModuleService.findById(id);
            return FloorMapper.toFloorResponseDto(floor);
        } catch (FloorException.DeletedException e) {
            throw new FloorException.DeletedException(id);
        } catch (NullPointerException e) {
            throw new FloorException.NotFoundException(id);
        } catch (FloorException.NotFoundDefaultFloorException e) {
            throw new FloorException.NotFoundDefaultFloorException(id);
        } catch (FloorException.InvalidExportType e) {
            throw new FloorException.InvalidExportType(id);
        }
    }
}
