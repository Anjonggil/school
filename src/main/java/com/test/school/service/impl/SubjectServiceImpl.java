package com.test.school.service.impl;

import com.test.school.common.error.ApiException;
import com.test.school.common.error.ApiExceptionEntity;
import com.test.school.domain.Subject;
import com.test.school.domain.dto.SubjectDto;
import com.test.school.repository.SubjectRepository;
import com.test.school.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Override
    public List<SubjectDto> getSubjects(){
        List<Subject> subjectList = subjectRepository.findByAll();

        return subjectList.stream().map(Subject::toDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Long setSubject(SubjectDto subjectDto) {
        validateDuplicateSubject(subjectDto.getName());
        Subject subject = subjectDto.toEntity();

        subjectRepository.save(subject);
        return subject.getId();
    }

    //과목 중복 체크
    private void validateDuplicateSubject(String name) {
        Subject findSubject = subjectRepository.findSubjectByName(name);
        throw new ApiException(new ApiExceptionEntity("ALREADY_EXIST_SUBJECT", "이미 존재하는 과목입니다." +"["+findSubject.getName()+"]" ));
    }

    @Transactional
    @Override
    public Boolean deleteSubject(Long subjectId) {
        Subject subject = subjectRepository.findSubjectById(subjectId);

        if (subject == null){
            return false;
        }

        subjectRepository.delete(subject);
        return true;
    }
}