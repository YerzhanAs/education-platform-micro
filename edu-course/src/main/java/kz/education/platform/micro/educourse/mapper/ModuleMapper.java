package kz.education.platform.micro.educourse.mapper;

import kz.education.platform.micro.educourse.models.dto.ModuleDTO;
import kz.education.platform.micro.eduentity.entity.Module;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ModuleMapper {

    private final ModelMapper modelMapper;

    public ModuleDTO convertToDTO(Module module) {
        return modelMapper.map(module, ModuleDTO.class);
    }

    public Module convertToModule(ModuleDTO moduleDTO) {
        return modelMapper.map(moduleDTO, Module.class);
    }
}
