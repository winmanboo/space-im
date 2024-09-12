package com.winmanboo.space.im.offline.core.mapper;

import com.winmanboo.space.im.offline.api.dto.OfflineMessageDTO;
import com.winmanboo.space.im.offline.core.entity.OfflineMessage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

import java.util.List;

/**
 * @author winmanboo
 * @date 2024/8/29 09:50
 */
@Mapper(componentModel = ComponentModel.SPRING)
public interface OfflineMessageMapper {

    List<OfflineMessageDTO> toDtoList(List<OfflineMessage> offlineMessages);
}
