package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.dto.carDTO.model_markDTO.MarkDto;
import org.kamenchuk.dto.carDTO.model_markDTO.ModelCreateDto;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.NotValidMethodParameters;
import org.kamenchuk.models.Mark;
import org.kamenchuk.models.Model;
import org.kamenchuk.repository.ModelRepository;
import org.kamenchuk.service.MarkService;
import org.kamenchuk.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final MarkService markService;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository,
                            MarkService markService) {
        this.modelRepository = modelRepository;
        this.markService = markService;
    }

    @Override
    @Transactional
    public Model saveIfNotExists(ModelCreateDto modelDto) throws NotValidMethodParameters, CreationException {
        return Optional.ofNullable(modelDto)
                .map(m -> setModelForCreate(m.getModel(), m.getMark()))
                .map(modelRepository::save)
                .orElseThrow(() -> {
                    log.info("Model is not created");
                    throw new CreationException("Model is not created");
                });

    }

    private Model setModelForCreate(String modelName, String markName) throws NotValidMethodParameters, CreationException {
        Model model = Model.builder()
                .model(modelName)
                .build();
        if ((modelName == null || modelName.isEmpty()) || (markName == null || markName.isEmpty())) {
            throw new NotValidMethodParameters("Model and(or) mark names are empty");
        } else {
            if (!modelRepository.existsModelByModelAndMark_Mark(modelName, markName)) {
                Mark mark = markService.existsMarkByMark(markName) ?
                        markService.findMarkByMark(markName) : markService.save(new MarkDto(markName));
                model.setMark(mark);
                modelRepository.save(model);
            } else {
                //Optional.get() обработано в if
                model = modelRepository.findModelByModelAndMark_Mark(modelName, markName).get();
            }
        }
        return model;
    }
}
