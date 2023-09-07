package org.kamenchuk.service.impl;

import org.kamenchuk.dto.carDTO.model_markDTO.ModelCreateDto;
import org.kamenchuk.exceptions.NotValidMethodParameters;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.models.Mark;
import org.kamenchuk.models.Model;
import org.kamenchuk.repository.ModelRepository;
import org.kamenchuk.service.MarkService;
import org.kamenchuk.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
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
    @Transactional(readOnly = true)
    public boolean existsModelByModelAndMark_Mark(String model, String mark) {
        return modelRepository.existsModelByModelAndMark_Mark(model,mark);
    }

    @Override
    @Transactional(readOnly = true)
    public Model findModelByModelAndMark_Mark(String model, String mark) throws ResourceNotFoundException{
        return modelRepository.findModelByModelAndMark_Mark(model,mark).orElseThrow(() -> {
            throw new ResourceNotFoundException(String.format("Model %s of this mark does not exist", model));
        });
    }

    @Override
    @Transactional
    public Model saveIfNotExists(ModelCreateDto modelDto) throws NotValidMethodParameters {
        return modelRepository.save(setModelForCreate(modelDto.getModel(),modelDto.getMark()));
    }

    private Model setModelForCreate(String modelName, String markName) throws NotValidMethodParameters {
        Model model = Model.builder()
                .model(modelName)
                .build();
        if ((modelName == null || modelName.isEmpty()) || (markName == null || markName.isEmpty())) {
            throw new NotValidMethodParameters("Model and(or) mark names are empty");
        } else {
            if (!modelRepository.existsModelByModelAndMark_Mark(modelName, markName)) {
                Mark mark = markService.existsMarkByMark(markName) ?
                        markService.findMarkByMark(markName) : markService.save(markName);
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
