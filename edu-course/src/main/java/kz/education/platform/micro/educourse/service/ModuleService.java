package kz.education.platform.micro.educourse.service;

import kz.education.platform.micro.educourse.mapper.ModuleMapper;

import kz.education.platform.micro.educourse.models.dto.ModuleDTO;
import kz.education.platform.micro.educourse.repository.ModuleRepository;
import kz.education.platform.micro.educourse.utils.exception.NotFoundException;
import kz.education.platform.micro.eduentity.entity.Module;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ModuleService {

    private final ModuleRepository moduleRepository;

    private final ModuleMapper moduleMapper;


    public List<ModuleDTO> findAll() {
        List<Module> modules = moduleRepository.findAll();

        if (modules.isEmpty()) {
            throw new NotFoundException("No modules found");
        }

        return modules.stream().map(moduleMapper::convertToDTO).collect(Collectors.toList());
    }

    public ModuleDTO findById(Long id) {
        return moduleMapper.convertToDTO(moduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Module not found with id " + id)));
    }


    @Transactional
    public void saveModule(ModuleDTO moduleDTO){
       moduleDTO.setDateCreated(LocalDateTime.now());
        moduleRepository.save(moduleMapper.convertToModule(moduleDTO));
    }

    @Transactional
    public void deleteModule(Long id) {
        findById(id);
        moduleRepository.deleteById(id);
    }


    @Transactional
    public void updateModule(Long lessonId, ModuleDTO moduleDTO) {
        Module module = moduleRepository.findById(lessonId).orElseThrow(() ->new NotFoundException("Module not found with id " + lessonId));

        Module updatedModule = moduleMapper.convertToModule(moduleDTO);

        updatedModule.setId(module.getId());

        moduleRepository.save(updatedModule);
    }
}
