package com.grading.system.service.impl;

import com.grading.system.domain.Subject;
import com.grading.system.repository.SubjectRepository;
import com.grading.system.service.SubjectService;
import com.grading.system.service.dto.SubjectDTO;
import com.grading.system.service.mapper.SubjectMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Subject}.
 */
@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private final Logger log = LoggerFactory.getLogger(SubjectServiceImpl.class);

    private final SubjectRepository subjectRepository;

    private final SubjectMapper subjectMapper;

    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    @Override
    public SubjectDTO save(SubjectDTO subjectDTO) {
        log.debug("Request to save Subject : {}", subjectDTO);
        Subject subject = subjectMapper.toEntity(subjectDTO);
        subject = subjectRepository.save(subject);
        return subjectMapper.toDto(subject);
    }

    @Override
    public SubjectDTO update(SubjectDTO subjectDTO) {
        log.debug("Request to update Subject : {}", subjectDTO);
        Subject subject = subjectMapper.toEntity(subjectDTO);
        subject = subjectRepository.save(subject);
        return subjectMapper.toDto(subject);
    }

    @Override
    public Optional<SubjectDTO> partialUpdate(SubjectDTO subjectDTO) {
        log.debug("Request to partially update Subject : {}", subjectDTO);

        return subjectRepository
            .findById(subjectDTO.getId())
            .map(existingSubject -> {
                subjectMapper.partialUpdate(existingSubject, subjectDTO);

                return existingSubject;
            })
            .map(subjectRepository::save)
            .map(subjectMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> findAll() {
        log.debug("Request to get all Subjects");
        return subjectRepository.findAll().stream().map(subjectMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SubjectDTO> findOne(Long id) {
        log.debug("Request to get Subject : {}", id);
        return subjectRepository.findById(id).map(subjectMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Subject : {}", id);
        subjectRepository.deleteById(id);
    }
}
