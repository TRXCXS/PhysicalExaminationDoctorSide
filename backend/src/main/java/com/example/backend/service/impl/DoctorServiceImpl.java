package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.dto.DoctorLoginFormDTO;
import com.example.backend.entity.Doctor;
import com.example.backend.mapper.DoctorMapper;
import com.example.backend.service.DoctorService;
import com.example.backend.utils.Result;
import com.example.backend.utils.SHA256;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl extends MPJBaseServiceImpl<DoctorMapper, Doctor> implements DoctorService {
    private final DoctorMapper doctorMapper;

    /**
     * 医生登录接口对应的服务类实现方法
     *
     * @param doctorLoginFormDTO：医生登录表单DTO
     * @return Result封装查询结果，登录成功返回医生对象，否则返回null
     * @throws Exception
     */
    @Override
    public Result getDoctorByDocCodeAndPassword(@NotNull DoctorLoginFormDTO doctorLoginFormDTO) throws Exception {
        Result result = getDoctorByDocCode(doctorLoginFormDTO.getDocCode());

        if (result.getIsSuccess() == Boolean.FALSE) {
            // 没有找到医生的情况
            return Result.fail("用户名或密码错误！");
        }

        Doctor doctor = (Doctor) result.getData();
        String encryptedPassword = SHA256.encrypt(doctorLoginFormDTO.getPassword());

        boolean isMatch = doctor.getPassword().equals(encryptedPassword);
        if (!isMatch) {
            // 找到用户但密码错误的情况
            return Result.fail("用户名或密码错误！");
        }

        return result;
    }

    /**
     * 判断医生是否存在
     *
     * @param docCode：医生的标识属性
     * @return Result封装查询结果，登录成功返回医生对象，否则返回null
     */
    @Override
    public Result getDoctorByDocCode(String docCode) {
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("docCode", docCode);
        Doctor doctor = doctorMapper.selectOne(queryWrapper);

        if (doctor == null) {
            return Result.fail("未找到指定docCode的医生！");
        }

        return Result.success(doctor);
    }

    /**
     * 保存医生对象的方法
     *
     * @param doctor：医生对象
     * @return Result封装保存结果
     * @throws Exception
     */
    @Override
    @Transactional
    public Result saveDoctor(@NotNull Doctor doctor) throws Exception {
        /**
         * 传入的Doctor对象，主键自增的字段不应该有值。
         * ①如果有的话，按照Doctor对象的值进行插入。————错误情况
         * ②没有的话，才进行自增。————正确情况
         */

        // 密码明文加密
        doctor.setPassword(SHA256.encrypt(doctor.getPassword()));
        int state = doctorMapper.insert(doctor);

        if (state == 0) {
            return Result.fail("插入失败！");
        } else {
            return Result.success();
        }
    }
}
