package com.example.employee.service;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.dto.EmployeeDtoRequest;
import com.example.employee.dto.ResponseDto;
import com.example.employee.entity.EmployeeEntity;
import com.example.employee.entity.ResponseMessage;
import com.example.employee.entity.ResponseStatus;
import com.example.employee.exception.CommonException;
import com.example.employee.repo.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;


    private EmployeeDto conv(EmployeeEntity employeeEntity) {
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employeeEntity, employeeDto);
        employeeDto.setGender(employeeEntity.getGender().name());

        return employeeDto;
    }

    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<ResponseDto> update(EmployeeDtoRequest employeeDtoRequest) throws CommonException {
        try {
            Optional<EmployeeEntity> employeeEntity = employeeRepo.findById(employeeDtoRequest.getId());
            if (employeeEntity.isPresent()) {
                EmployeeEntity employee = employeeEntity.get();
                BeanUtils.copyProperties(employeeDtoRequest, employee);
//                employee.setId(employeeDtoRequest.getId());
//                employee.setGender(EmployeeEntity.Gender.valueOf(employeeDtoRequest.getGender()));
//                employee.setCode(employeeDtoRequest.getCode());
                conv(employeeRepo.save(employee));

                return new ResponseEntity<>(
                        new ResponseDto<>(
                                ResponseStatus.OK,
                                ResponseMessage.ENTITY_RETRIEVED_SUCCESS,
                                employeeDtoRequest),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        new ResponseDto<>(
                                ResponseStatus.NOT_FOUND,
                                ResponseMessage.UPDATE_FAILED,
                                true),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            log.error("Exception occurred while update.", ex);
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    public ResponseEntity<ResponseDto> save(EmployeeDtoRequest employeeDtoRequest) throws CommonException {
        try {
            var employeeInfo=employeeRepo.findIsUnique(employeeDtoRequest.getCode(),employeeDtoRequest.getMobileNo(),employeeDtoRequest.getEmail(),employeeDtoRequest.getNid());
            if(employeeInfo.isPresent()){
                return new ResponseEntity<>(
                        new ResponseDto<>(
                                ResponseStatus.NOT_FOUND,
                                ResponseMessage.valueOf("please try unique employee information"),
                                false),
                        HttpStatus.BAD_REQUEST);
            }else {
                EmployeeEntity emp = new EmployeeEntity();
            BeanUtils.copyProperties(employeeDtoRequest, emp, "gender");

//            emp.setGender(EmployeeEntity.Gender.valueOf(empDto.getGender()));

                conv(employeeRepo.save(emp));

                return new ResponseEntity<>(
                        new ResponseDto<>(
                                ResponseStatus.OK,
                                ResponseMessage.ENTITY_RETRIEVED_SUCCESS,
                                true),
                        HttpStatus.OK);
            }
        } catch (Exception ex) {
            log.error("Exception occurred while save.", ex);
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    public ResponseEntity<ResponseDto> findAll(Pageable pageable, String sText) throws CommonException {
        try {
            Page<EmployeeEntity> entities = employeeRepo.findAllCustom(pageable, sText);

            List<EmployeeDto> sss = new ArrayList(pageable.getPageSize());
            for (EmployeeEntity pp : entities.getContent()) {
                sss.add(conv(pp));
            }

            Page<EmployeeDto> employeeDtos = new PageImpl(sss, pageable, entities.getTotalElements());


            return new ResponseEntity<>(
                    new ResponseDto<>(
                            ResponseStatus.OK,
                            ResponseMessage.ENTITY_RETRIEVED_SUCCESS,
                            employeeDtos),
                    HttpStatus.OK);

        } catch (Exception ex) {
            log.error("Exception occurred while findAll.", ex);
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    public ResponseEntity<ResponseDto> findById(Long id) throws CommonException {
        try {
            Optional<EmployeeEntity> EmployeeEntity = employeeRepo.findById(id);
            if (EmployeeEntity.isPresent()) {
                EmployeeEntity employee = EmployeeEntity.get();
                EmployeeDto employeeDto = new EmployeeDto();
                BeanUtils.copyProperties(employee, employeeDto);
                employeeDto.setCode(employee.getCode());
                employeeDto.setGender(employee.getGender().name());


                return new ResponseEntity<>(
                        new ResponseDto<>(
                                ResponseStatus.OK,
                                ResponseMessage.ENTITY_RETRIEVED_SUCCESS,
                                employeeDto),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        new ResponseDto<>(
                                ResponseStatus.NOT_FOUND,
                                ResponseMessage.NOT_FOUND,
                                false),
                        HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("Exception occurred while findById.", e);
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseEntity<ResponseDto> deleteById(Long id) throws CommonException {
        try {
            employeeRepo.deleteById(id);

            return new ResponseEntity<>(
                    new ResponseDto<>(
                            ResponseStatus.OK,
                            ResponseMessage.ENTITY_RETRIEVED_SUCCESS,
                            true),
                    HttpStatus.OK);

        } catch (Exception ex) {
            log.error("Exception occurred while deleteById.", ex);
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
