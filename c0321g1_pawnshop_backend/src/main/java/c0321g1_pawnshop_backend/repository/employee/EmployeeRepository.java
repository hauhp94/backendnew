package c0321g1_pawnshop_backend.repository.employee;

import c0321g1_pawnshop_backend.entity.employee.Employee;
<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //    create by HauHP
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `c0321g1_pawnshop2`.`employee` ( `address`, `birth_date`, `email`," +
            " `employee_code`, `flag`, `id_card`, `image`, `name`, `phone`, `salary`, `account_id`, `gender_id`)" +
            " VALUES ( ?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12);", nativeQuery = true)
    void saveEmployee(String address, String birthDate, String email, String employeeCode, Long flag, String idCard,
                      String image, String name, String phone, String salary, Long accountId, Long genderId);

    //    create by HauHP
    @Query(value = "select * from employee where employee.employee_id = ?1", nativeQuery = true)
    Optional<Employee> findEmployeeById(Long id);

    //    create by HauHP
    @Transactional
    @Modifying
    @Query(value = "UPDATE `c0321g1_pawnshop2`.`employee` SET `address` = ?1, `birth_date` = ?2," +
            " `email` = ?3, `employee_code` = ?4, `flag` = ?5, `id_card` = ?6," +
            " `image` = ?7, `name` = ?8, `phone` = ?9, `salary` = ?10," +
            " `account_id` = ?11, `gender_id` = ?12 WHERE (`employee_id` = ?13);\n", nativeQuery = true)
    void editEmployee(String address, String birthDate, String email, String employeeCode, Long flag, String idCard,
                      String image, String name, String phone, String salary, Long accountId, Long genderId, Long employeeId);

    // ly huynh
    @Query(value = "select * from " +
            "employee where flag = 0 ", nativeQuery = true)
    Page<Employee> findAllEmployee(Pageable pageable);
    @Modifying
    @Transactional
    @Query(value = "update employee " +
            "set flag=1 where employee_id =:employeeId", nativeQuery = true)
    void deleteEmployee(@Param("employeeId") Long employeeId);

//    @Query(value = "select * from " +
//            "employee where flag =1 and employee_id =:employeeId", nativeQuery = true)
//    Optional<Employee> findEmployeeById(@Param("employeeId") Long employeeId);

    @Query(value = "select * from " +
            "employee where flag=0 and name like :keyName and phone like :keyPhone and address like :keyAddress", nativeQuery = true)
    Page<Employee> searchEmployeeByNamePhoneAddress(Pageable pageable,@Param("keyName") String keyName,@Param("keyPhone") String keyPhone,
                                                    @Param("keyAddress") String keyAddress);

    @Query(value ="select distinct address from employee "
            , nativeQuery = true)
    List<String> findAllListEmployee();

    //Creator: Nhung
    @Query(value = "SELECT employee.* FROM employee " +
            "join account on employee.account_id = account.account_id WHERE employee.flag = 0", nativeQuery = true)
    List<Employee> listEmployee();
=======
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
>>>>>>> b4282ffe7b00faa377054aa2730be124489f086d
}
